package com.example.a11355.peoplescloudmedia.fragement;


import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.activity.H5ActivityForNewsDetical;
import com.example.a11355.peoplescloudmedia.activity.LoginActivity;
import com.example.a11355.peoplescloudmedia.adapter.NewsCollectListAdapter;
import com.example.a11355.peoplescloudmedia.base.AbsRecyclerViewAdapter;
import com.example.a11355.peoplescloudmedia.base.BaseFragment;
import com.example.a11355.peoplescloudmedia.base.OnAdapterCallbackListener;
import com.example.a11355.peoplescloudmedia.custom.DividerGridItem;
import com.example.a11355.peoplescloudmedia.custom.LoadingDialog;
import com.example.a11355.peoplescloudmedia.model.GetFindNewsCollectList;
import com.example.a11355.peoplescloudmedia.model.GetFindNewsCollectListEntity;
import com.example.a11355.peoplescloudmedia.util.Constant;
import com.example.a11355.peoplescloudmedia.util.DesUtil;
import com.example.a11355.peoplescloudmedia.util.LogUtils;
import com.example.a11355.peoplescloudmedia.util.OkHttpUtil;
import com.example.a11355.peoplescloudmedia.util.PreferencesUtil;
import com.example.a11355.peoplescloudmedia.util.SharedPreferencesUtil;
import com.example.a11355.peoplescloudmedia.util.ToastUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static android.app.Activity.RESULT_OK;

/**
 * 文章收藏列表
 */

public class NewsCollectListFragment extends BaseFragment implements OkHttpUtil.OnDataListener, OnAdapterCallbackListener, SwipeRefreshLayout.OnRefreshListener, AbsRecyclerViewAdapter.OnItemClickListener ,View.OnClickListener{

    @BindView(R.id.rv_store)
    RecyclerView rvStore;
    @BindView(R.id.srl_store)
    SwipeRefreshLayout srlStore;
    private LoadingDialog loadingDialog;


    private int PageIndex = 1;
    private int PageSize = 10;
    private int nextPage = 1;
    private List<GetFindNewsCollectListEntity.DataBean.ArtilesBean> videosData = new ArrayList<>();

    private Gson gson = new GsonBuilder().create();
    private NewsCollectListAdapter getVideoListAdapter;
    public NewsCollectListFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getViewResId() {
        return R.layout.fragment_news_collect_list;
    }
    @Override
    protected void init(View v) {
        rvStore.setLayoutManager(new LinearLayoutManager(getContext()));
        getVideoListAdapter = new NewsCollectListAdapter(getActivity(), this,this);
        rvStore.setAdapter(getVideoListAdapter);
        srlStore.setOnRefreshListener(this);
        rvStore.addItemDecoration(new DividerGridItem(getContext()));
        getVideoListAdapter.setOnItemClickListener(this);
        loadingDialog = LoadingDialog.newInstance("加载中...");
        loadingDialog.show(getActivity().getFragmentManager());
        getVideoListAdapter.setEmptyView(R.layout.empty_tips);
        getVideoListAdapter.setEmptyTips(R.id.tv_emptyTips, "暂无数据");
    }

    @Override
    protected void loadData() {
        String toJson = gson.toJson(new GetFindNewsCollectList(PreferencesUtil.getToken(getContext()),PreferencesUtil.getUserId(getContext()),nextPage + "", PageSize + ""));
        OkHttpUtil.postJson(Constant.URL.GetFindNewsCollectList, DesUtil.encrypt(toJson), this);
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
                case Constant.URL.GetFindNewsCollectList: {
                    LogUtils.e("GetFindNewsCollectList",  decrypt);
                    if (PageIndex == 1) {
                        videosData.clear();
                        dismissLoading();
                    }
                    removeLoadingItem();
                    GetFindNewsCollectListEntity getFindNewsCollectListEntity = gson.fromJson(decrypt, GetFindNewsCollectListEntity.class);
                    if (getFindNewsCollectListEntity.getCode() == Constant.Integers.SUC) {
                        videosData.addAll(getFindNewsCollectListEntity.getData().getArtiles());
                        if (getFindNewsCollectListEntity.getData().getArtiles().size() % PageSize == 0 && getFindNewsCollectListEntity.getData().getArtiles().size() != 0) {//可能还有下一页
                            videosData.add(new GetFindNewsCollectListEntity.DataBean.ArtilesBean(1));
                            nextPage = PageIndex + 1;
                        } else {
                            addBaseLine();
                        }
                    }else if (getFindNewsCollectListEntity.getCode() == Constant.Integers.TOKEN_OUT_OF) { //token过期
                        dismissLoading();
                        ToastUtil.initToast(getContext(), getFindNewsCollectListEntity.getMessage());
                        startActivityForResult(new Intent(getContext(), LoginActivity.class), Constant.Code.LoginCode);

                    }
                    else if (getFindNewsCollectListEntity.getCode() == Constant.Integers.NULL){
                        addBaseLine();
                    }else {//其他
                        dismissLoading();
                        ToastUtil.initToast(getContext(), getFindNewsCollectListEntity.getMessage());
                    }


                }
                getVideoListAdapter.setData(videosData);
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
            videosData.add(new GetFindNewsCollectListEntity.DataBean.ArtilesBean(2));
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
        Intent intent = new Intent(getContext(), H5ActivityForNewsDetical.class);
        intent.putExtra("url", String.format(Constant.URL.NewsDetailsLink,videosData.get(position).getId()+"", SharedPreferencesUtil.getUserId(getContext())));

        intent.putExtra("data1", videosData.get(position));
        startActivityForResult(intent,Constant.Code.CollectCode);
    }

    @Override
    public void onClick(View v) {
        
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode==Constant.Code.CollectCode){
            if (resultCode==RESULT_OK){
                onRefresh();
            }
        }
    }
}


