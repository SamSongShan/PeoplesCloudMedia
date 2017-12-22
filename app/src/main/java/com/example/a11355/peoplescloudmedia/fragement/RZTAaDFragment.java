package com.example.a11355.peoplescloudmedia.fragement;


import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.activity.LoginActivity;
import com.example.a11355.peoplescloudmedia.activity.PickUpADActivity;
import com.example.a11355.peoplescloudmedia.adapter.GetAdvertisingListAdapter;
import com.example.a11355.peoplescloudmedia.base.AbsRecyclerViewAdapter;
import com.example.a11355.peoplescloudmedia.base.BaseFragment;
import com.example.a11355.peoplescloudmedia.base.OnAdapterCallbackListener;
import com.example.a11355.peoplescloudmedia.custom.DividerGridItem;
import com.example.a11355.peoplescloudmedia.custom.LoadingDialog;
import com.example.a11355.peoplescloudmedia.model.GetAdvertisingListEntity;
import com.example.a11355.peoplescloudmedia.model.GetFindNewsCollectList;
import com.example.a11355.peoplescloudmedia.util.Constant;
import com.example.a11355.peoplescloudmedia.util.DesUtil;
import com.example.a11355.peoplescloudmedia.util.LogUtils;
import com.example.a11355.peoplescloudmedia.util.OkHttpUtil;
import com.example.a11355.peoplescloudmedia.util.PreferencesUtil;
import com.example.a11355.peoplescloudmedia.util.ToastUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 人众推制作   广告
 */
public class RZTAaDFragment extends BaseFragment implements OkHttpUtil.OnDataListener, OnAdapterCallbackListener, SwipeRefreshLayout.OnRefreshListener, AbsRecyclerViewAdapter.OnItemClickListener ,View.OnClickListener{


    public RZTAaDFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.rv_store)
    RecyclerView rvStore;
    @BindView(R.id.srl_store)
    SwipeRefreshLayout srlStore;
    private LoadingDialog loadingDialog;


    private int PageIndex = 1;
    private int PageSize = 10;
    private int nextPage = 1;
    private List<GetAdvertisingListEntity.DataEntity> videosData = new ArrayList<>();

    private Gson gson = new GsonBuilder().create();
    private GetAdvertisingListAdapter getAdvertisingListAdapter;




    @Override
    protected int getViewResId() {
        return R.layout.fragment_rztarticle;
    }

    @Override
    protected void init(View v) {
        rvStore.setLayoutManager(new LinearLayoutManager(getContext()));
        getAdvertisingListAdapter = new GetAdvertisingListAdapter(getActivity(), this,this);
        rvStore.setAdapter(getAdvertisingListAdapter);
        srlStore.setOnRefreshListener(this);
        rvStore.addItemDecoration(new DividerGridItem(getContext()));
        getAdvertisingListAdapter.setOnItemClickListener(this);
        loadingDialog = LoadingDialog.newInstance("加载中...");
        loadingDialog.show(getActivity().getFragmentManager());
        getAdvertisingListAdapter.setEmptyView(R.layout.empty_tips);
        getAdvertisingListAdapter.setEmptyTips(R.id.tv_emptyTips, "暂无数据");
    }

    @Override
    protected void loadData() {
        String toJson = gson.toJson(new GetFindNewsCollectList(PreferencesUtil.getToken(getContext()),PreferencesUtil.getUserId(getContext()),nextPage + "", PageSize + ""));
        OkHttpUtil.postJson(Constant.URL.GetAdvertisingList, DesUtil.encrypt(toJson), this);
    }

    private void dismissLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
        if (srlStore != null && srlStore.isRefreshing()) {
            srlStore.setRefreshing(false);
        }
    }

    @Override
    public void onResponse(String url, String json) {
        if (!TextUtils.isEmpty(json)) {
            String decrypt = DesUtil.decrypt(json);
            switch (url) {
                case Constant.URL.GetAdvertisingList: {
                    LogUtils.e("GetAdvertisingList",  decrypt);
                    if (PageIndex == 1) {
                        videosData.clear();
                        dismissLoading();
                    }
                    removeLoadingItem();
                    GetAdvertisingListEntity getAdvertisingListEntity = gson.fromJson(decrypt, GetAdvertisingListEntity.class);
                    if (getAdvertisingListEntity.getCode() == Constant.Integers.SUC) {
                        videosData.addAll(getAdvertisingListEntity.getData());
                        if (getAdvertisingListEntity.getData().size() % PageSize == 0 && getAdvertisingListEntity.getData().size() != 0) {//可能还有下一页
                            videosData.add(new GetAdvertisingListEntity.DataEntity(1));
                            nextPage = PageIndex + 1;
                        } else {
                            addBaseLine();
                        }
                    } else if (getAdvertisingListEntity.getCode() == Constant.Integers.TOKEN_OUT_OF) { //token过期
                        dismissLoading();
                        ToastUtil.initToast(getContext(), getAdvertisingListEntity.getMessage());
                        startActivityForResult(new Intent(getContext(), LoginActivity.class), Constant.Code.LoginCode);

                    } else if (getAdvertisingListEntity.getCode() == Constant.Integers.NULL) {
                        addBaseLine();
                    }else {//其他
                        dismissLoading();
                        ToastUtil.initToast(getContext(), getAdvertisingListEntity.getMessage());
                    }


                }
                getAdvertisingListAdapter.setData(videosData);
                break;
            }
        }
    }

    @Override
    public void onFailure(String url, String error) {

    }

    private void removeLoadingItem() {
        if (videosData.size() > 0) {
            if (videosData.get(videosData.size() - 1).getType() == 1) {
                videosData.remove(videosData.size() - 1);
            }
        }
    }

    private void addBaseLine() {
        if (PageIndex != 1) {
            videosData.add(new GetAdvertisingListEntity.DataEntity(2));
        }
    }

    @Override
    public void onCallback() {
        if (nextPage == PageIndex + 1) {
            PageIndex++;
            loadData();
        }
    }


    @Override
    public void onRefresh() {
        PageIndex = 1;
        nextPage = 1;
        loadData();
    }

    @Override
    public void onItemClick(View v, int position) {
        Intent intent = new Intent(getContext(), PickUpADActivity.class);
        intent.putExtra("data", videosData.get(position));

        intent.putExtra("title","编辑广告");
        startActivityForResult(intent,Constant.Code.PickUp);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        onRefresh();
    }
}


