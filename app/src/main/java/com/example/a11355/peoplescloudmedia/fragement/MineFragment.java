package com.example.a11355.peoplescloudmedia.fragement;


import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.activity.MyQRCodeActivity;
import com.example.a11355.peoplescloudmedia.adapter.MineRVAdapter;
import com.example.a11355.peoplescloudmedia.base.AbsRecyclerViewAdapter;
import com.example.a11355.peoplescloudmedia.base.BaseFragment;
import com.example.a11355.peoplescloudmedia.custom.GridDividerItemDecoration;
import com.example.a11355.peoplescloudmedia.util.Constant;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;


/**
 * 我的
 */
public class MineFragment extends BaseFragment implements View.OnClickListener, AbsRecyclerViewAdapter.OnItemClickListener {


    @BindView(R.id.rv_store)
    RecyclerView rvStore;
    @BindView(R.id.srl_store)
    SwipeRefreshLayout srlStore;
    private View view;
    private SimpleDraweeView sdvUserHead;
    private TextView tvMyQrCode;
    private ImageView imgShear;
    private ImageView imgSetting;
    private TextView tvName;
    private TextView tvSignature;
    private TextView tvFocusNum;
    private TextView tvFansNum;
    private TextView tvCollectNum;
    private List<String> strings;

    public MineFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getViewResId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void init(View v) {
        //mine头布局
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.head_mine, null, false);
        MineRVAdapter mineRVAdapter = new MineRVAdapter(getContext());
        mineRVAdapter.addHeaderView(view);
        rvStore.setLayoutManager(new LinearLayoutManager(getContext()));
        rvStore.addItemDecoration(new GridDividerItemDecoration(getContext(), 2));
        rvStore.setAdapter(mineRVAdapter);
        strings = Arrays.asList(Constant.Strings.mines);
        mineRVAdapter.setData(strings);
        mineRVAdapter.setOnItemClickListener(this);
        initHeadView(view);
    }


    public void initHeadView(View view) {
        sdvUserHead = (SimpleDraweeView) view.findViewById(R.id.sdv_userHead);
        tvMyQrCode = (TextView) view.findViewById(R.id.tv_myQrCode);
        imgShear = (ImageView) view.findViewById(R.id.img_shear);
        imgSetting = (ImageView) view.findViewById(R.id.img_setting);
        tvName = (TextView) view.findViewById(R.id.tv_name);
        tvSignature = (TextView) view.findViewById(R.id.tv_signature);
        tvFocusNum = (TextView) view.findViewById(R.id.tv_FocusNum);
        tvFansNum = (TextView) view.findViewById(R.id.tv_fansNum);
        tvCollectNum = (TextView) view.findViewById(R.id.tv_collectNum);

        sdvUserHead.setOnClickListener(this);
        tvMyQrCode.setOnClickListener(this);
        imgShear.setOnClickListener(this);
        imgSetting.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sdv_userHead: {//头像
                srlStore.setRefreshing(false);
            }
            break;
            case R.id.tv_myQrCode: {//我的二维码
                startActivity(new Intent(getContext(), MyQRCodeActivity.class));
            }
            break;
            case R.id.img_shear: {//分享

            }
            break;
            case R.id.img_setting: {//设置

            }
            break;
        }
    }

    @Override
    public void onItemClick(View v, int position) {
        switch (strings.get(position)) {
            case "我的文章": {
            }
            break;
            case "我的推广": {
            }
            break;
            case "我的名片": {
            }
            break;
            case "关于我们": {
            }
            break;

        }
    }
}
