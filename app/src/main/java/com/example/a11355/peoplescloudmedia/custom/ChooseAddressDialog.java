package com.example.a11355.peoplescloudmedia.custom;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.adapter.ChooseAddressRVAdapter;
import com.example.a11355.peoplescloudmedia.base.AbsRecyclerViewAdapter;
import com.example.a11355.peoplescloudmedia.base.BaseDialog;
import com.example.a11355.peoplescloudmedia.model.AreaListEntity;
import com.example.a11355.peoplescloudmedia.util.CacheUtil;
import com.example.a11355.peoplescloudmedia.util.Constant;
import com.example.a11355.peoplescloudmedia.util.PhoneUtil;
import com.example.a11355.peoplescloudmedia.util.ToastUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class ChooseAddressDialog extends BaseDialog implements AbsRecyclerViewAdapter.OnItemClickListener,
        View.OnClickListener {

    private TextView tvTitle;
    private ImageButton ibBack;
    private RecyclerView recyclerView;
    private int layer = 1;//当前层级
    private int layerIndex1 = -1;//第一层级选中的下标
    private int layerIndex2 = -1;//第二层级选中的下标
    private List<AreaListEntity.DataEntity> dataList = new ArrayList<>();
    private ChooseAddressRVAdapter recyclerViewAdapter;
    private LoadingDialog loadingDialog;
    private TextView tZvCommit;
    private int layerIndex3;

    public static ChooseAddressDialog newInstance() {
        return new ChooseAddressDialog();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //取消标题显示
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getDialog().getWindow();
        View view = inflater.inflate(R.layout.dialog_choose_address,
                ((ViewGroup) window.findViewById(android.R.id.content)), false);
        //必须设置，否则不能全屏
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.setCancelable(false);
        //宽度撑满
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, PhoneUtil.getPhoneHeight(getActivity()) * 4 / 5);
        window.setGravity(Gravity.BOTTOM);
        //进入退出动画
        window.setWindowAnimations(R.style.animTranslateBottom);

        tvTitle = (TextView) view.findViewById(R.id.tv_chooseAddressTitle);
        ibBack = (ImageButton) view.findViewById(R.id.ib_chooseAddressBack);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_chooseAddress);
        tZvCommit = (TextView) view.findViewById(R.id.tv_commit);
        ibBack.setOnClickListener(this);
        tZvCommit.setOnClickListener(this);
        loadingDialog = LoadingDialog.newInstance("加载中...");
        loadingDialog.show(getActivity().getFragmentManager());
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                recyclerViewAdapter.setData(dataList);
                dismissLoading();
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                String areaString = CacheUtil.getUrlCache(Constant.URL.GetAreaList);
                if (!TextUtils.isEmpty(areaString)) {//读取到缓存
                    AreaListEntity area = new Gson().fromJson(areaString, AreaListEntity.class);
                    dataList.addAll(area.getData());
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                }
            }
        }).start();


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewAdapter = new ChooseAddressRVAdapter(getActivity(), dataList);
        recyclerViewAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.addItemDecoration(new DividerGridItem(getActivity()));
        return view;
    }


    @Override
    public void onItemClick(View v, int position) {
        switch (layer) {
            case 1://更新市数据
                layerIndex1 = position;
                recyclerViewAdapter.setData(dataList.get(position).getChirldData());
                recyclerView.scrollToPosition(0);
                ibBack.setVisibility(View.VISIBLE);
                break;
            case 2://更新区数据
                layerIndex2 = position;
                recyclerViewAdapter.setData(dataList.get(layerIndex1).getChirldData().get(position).getChirldData());
                recyclerView.scrollToPosition(0);
                break;
            case 3://选中返回
                layerIndex3 = position;
                StringBuffer placeId = new StringBuffer();
                StringBuffer placeName = new StringBuffer();
                placeId.append(dataList.get(layerIndex1).getAreaId());
                placeId.append("|");
                placeName.append(dataList.get(layerIndex1).getAreaName());
                placeName.append("|");
                AreaListEntity.DataEntity city = dataList.get(layerIndex1).getChirldData().get(layerIndex2);
                placeId.append(city.getAreaId());
                placeId.append("|");
                placeName.append(city.getAreaName());
                placeName.append("|");
                placeId.append(city.getChirldData().get(position).getAreaId());
                placeName.append(city.getChirldData().get(position).getAreaName());

                v.setTag(placeId.toString());
                v.setTag(R.id.tag_relation, placeName.toString());
                onItemClickListener.onItemClick(v);
                break;
        }
        layer++;
        updateTitle();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ib_chooseAddressBack) {
            switch (layer) {
                case 2://更新省数据
                    recyclerViewAdapter.setData(dataList);
                    recyclerView.scrollToPosition(layerIndex1);
                    layerIndex1 = -1;
                    ibBack.setVisibility(View.GONE);
                    break;
                case 3://更新市数据
                    recyclerViewAdapter.setData(dataList.get(layerIndex1).getChirldData());
                    recyclerView.scrollToPosition(layerIndex1);
                    layerIndex2 = -1;
                    break;
            }
            layer--;
            updateTitle();
        } else if (view.getId() == R.id.tv_commit) {
            if (layer < 4) {
                ToastUtil.initToast(getContext(), "请选择完整地址");
            } else {
                dismissLoading();
                dismissAllowingStateLoss();

            }

        }
    }


    private void updateTitle() {
        switch (layer) {
            case 1: {
                tvTitle.setText("选择城市");
            }
            break;
            case 2: {
                tvTitle.setText(dataList.get(layerIndex1).getAreaName());
            }
            break;
            case 3: {
                AreaListEntity.DataEntity province = dataList.get(layerIndex1);
                tvTitle.setText(province.getAreaName() + " " + province.getChirldData().get(layerIndex2).getAreaName());
            }
            break;
            case 4: {
                AreaListEntity.DataEntity province = dataList.get(layerIndex1);
                tvTitle.setText(province.getAreaName() + " " + province.getChirldData().get(layerIndex2).getAreaName() + " " + province.getChirldData().get(layerIndex2).getChirldData().get(layerIndex3).getAreaName());
            }
            break;
        }
    }

    private void dismissLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }

    }
}
