package com.example.a11355.peoplescloudmedia.fragement;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.activity.H5ActivityNotitle;
import com.example.a11355.peoplescloudmedia.activity.LoginActivity;
import com.example.a11355.peoplescloudmedia.adapter.GetAttentionToListAdapter;
import com.example.a11355.peoplescloudmedia.base.AbsRecyclerViewAdapter;
import com.example.a11355.peoplescloudmedia.base.BaseFragment;
import com.example.a11355.peoplescloudmedia.base.OnAdapterCallbackListener;
import com.example.a11355.peoplescloudmedia.custom.DividerGridItem;
import com.example.a11355.peoplescloudmedia.custom.LoadingDialog;
import com.example.a11355.peoplescloudmedia.model.GetFindNewsCollectList;
import com.example.a11355.peoplescloudmedia.model.GetAttentionToListEntity;
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
 * A simple {@link Fragment} subclass.
 */
public class FocusFragment extends BaseFragment implements OkHttpUtil.OnDataListener, OnAdapterCallbackListener, SwipeRefreshLayout.OnRefreshListener, AbsRecyclerViewAdapter.OnItemClickListener, View.OnClickListener {


    private int type;
    @BindView(R.id.rv_store)
    RecyclerView rvStore;
    @BindView(R.id.srl_store)
    SwipeRefreshLayout srlStore;
    private LoadingDialog loadingDialog;


    private int PageIndex = 1;
    private int PageSize = 10;
    private int nextPage = 1;
    private List<GetAttentionToListEntity.DataBean> videosData = new ArrayList<>();

    private Gson gson = new GsonBuilder().create();
    private GetAttentionToListAdapter getAttentionToListAdapter;

    public FocusFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getViewResId() {
        return R.layout.fragment_focus;
    }

    public static FocusFragment instance(int type) {

        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        FocusFragment focusFragment = new FocusFragment();
        focusFragment.setArguments(bundle);
        return focusFragment;


    }

    @Override
    protected void init(View v) {
        rvStore.setLayoutManager(new LinearLayoutManager(getContext()));
        getAttentionToListAdapter = new GetAttentionToListAdapter(getActivity(), this, this);
        rvStore.setAdapter(getAttentionToListAdapter);
        srlStore.setOnRefreshListener(this);
        rvStore.addItemDecoration(new DividerGridItem(getContext()));
        getAttentionToListAdapter.setOnItemClickListener(this);
        loadingDialog = LoadingDialog.newInstance("加载中...");
        loadingDialog.show(getActivity().getFragmentManager());
        getAttentionToListAdapter.setEmptyView(R.layout.empty_tips);
        getAttentionToListAdapter.setEmptyTips(R.id.tv_emptyTips, "暂无数据");
    }

    @Override
    protected void loadData() {
        String toJson = gson.toJson(new GetFindNewsCollectList(PreferencesUtil.getToken(getContext()), PreferencesUtil.getUserId(getContext()), nextPage + "", PageSize + ""));

        type = getArguments().getInt("type");

        if (type == 0) {   //关注
            OkHttpUtil.postJson(Constant.URL.GetAttentionToList, DesUtil.encrypt(toJson), this);
        } else {    //粉丝
            OkHttpUtil.postJson(Constant.URL.GetFansList, DesUtil.encrypt(toJson), this);
        }

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
                case Constant.URL.GetAttentionToList: { //关注
                    LogUtils.e("GetAttentionToList", decrypt);
                    if (PageIndex == 1) {
                        videosData.clear();
                        dismissLoading();
                    }
                    removeLoadingItem();
                    GetAttentionToListEntity getAttentionToListEntity = gson.fromJson(decrypt, GetAttentionToListEntity.class);
                    if (getAttentionToListEntity.getCode() == Constant.Integers.SUC) {
                        videosData.addAll(getAttentionToListEntity.getData());
                        if (getAttentionToListEntity.getData().size() % PageSize == 0 && getAttentionToListEntity.getData().size() != 0) {//可能还有下一页
                            videosData.add(new GetAttentionToListEntity.DataBean(1));
                            nextPage = PageIndex + 1;
                        } else {
                            addBaseLine();
                        }
                    } else if (getAttentionToListEntity.getCode() == Constant.Integers.TOKEN_OUT_OF) { //token过期
                        dismissLoading();
                        ToastUtil.initToast(getContext(), getAttentionToListEntity.getMessage());
                        startActivityForResult(new Intent(getContext(), LoginActivity.class), Constant.Code.LoginCode);

                    } else if (getAttentionToListEntity.getCode() == Constant.Integers.NULL) {
                        addBaseLine();
                    } else {//其他
                        dismissLoading();
                        ToastUtil.initToast(getContext(), getAttentionToListEntity.getMessage());
                    }


                }
                getAttentionToListAdapter.setData(videosData);
                break;
                case Constant.URL.GetFansList: { //粉丝
                    LogUtils.e("GetFansList", decrypt);
                    if (PageIndex == 1) {
                        videosData.clear();
                        dismissLoading();
                    }
                    removeLoadingItem();
                    GetAttentionToListEntity getAttentionToListEntity = gson.fromJson(decrypt, GetAttentionToListEntity.class);
                    if (getAttentionToListEntity.getCode() == Constant.Integers.SUC) {
                        videosData.addAll(getAttentionToListEntity.getData());
                        if (getAttentionToListEntity.getData().size() % PageSize == 0 && getAttentionToListEntity.getData().size() != 0) {//可能还有下一页
                            videosData.add(new GetAttentionToListEntity.DataBean(1));
                            nextPage = PageIndex + 1;
                        } else {
                            addBaseLine();
                        }
                    } else if (getAttentionToListEntity.getCode() == Constant.Integers.TOKEN_OUT_OF) { //token过期
                        dismissLoading();
                        ToastUtil.initToast(getContext(), getAttentionToListEntity.getMessage());
                        startActivityForResult(new Intent(getContext(), LoginActivity.class), Constant.Code.LoginCode);

                    } else if (getAttentionToListEntity.getCode() == Constant.Integers.NULL) {
                        addBaseLine();
                    } else {//其他
                        dismissLoading();
                        ToastUtil.initToast(getContext(), getAttentionToListEntity.getMessage());
                    }


                }
                getAttentionToListAdapter.setData(videosData);
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
            videosData.add(new GetAttentionToListEntity.DataBean(2));
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
        Intent intent = new Intent(getContext(), H5ActivityNotitle.class);
        intent.putExtra("url",Constant.URL.OthersHome+videosData.get(position).getUserId());
        startActivity(intent);

    }

    @Override
    public void onClick(View v) {

    }
}



