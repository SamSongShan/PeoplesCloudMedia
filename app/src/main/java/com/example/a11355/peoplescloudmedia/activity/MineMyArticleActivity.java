package com.example.a11355.peoplescloudmedia.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.adapter.GetRZTArticleListAdapter;
import com.example.a11355.peoplescloudmedia.base.AbsRecyclerViewAdapter;
import com.example.a11355.peoplescloudmedia.base.BaseActivity;
import com.example.a11355.peoplescloudmedia.base.OnAdapterCallbackListener;
import com.example.a11355.peoplescloudmedia.custom.DividerGridItem;
import com.example.a11355.peoplescloudmedia.custom.LoadingDialog;
import com.example.a11355.peoplescloudmedia.model.GetFindNewsCollectList;
import com.example.a11355.peoplescloudmedia.model.GetRZTArticleListEntity;
import com.example.a11355.peoplescloudmedia.util.Constant;
import com.example.a11355.peoplescloudmedia.util.DesUtil;
import com.example.a11355.peoplescloudmedia.util.LogUtils;
import com.example.a11355.peoplescloudmedia.util.OkHttpUtil;
import com.example.a11355.peoplescloudmedia.util.PreferencesUtil;
import com.example.a11355.peoplescloudmedia.util.ToastUtil;
import com.example.a11355.peoplescloudmedia.util.ToolBarUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MineMyArticleActivity extends BaseActivity implements OkHttpUtil.OnDataListener, OnAdapterCallbackListener, SwipeRefreshLayout.OnRefreshListener, AbsRecyclerViewAdapter.OnItemClickListener, View.OnClickListener {

   
    @BindView(R.id.rv_store)
    RecyclerView rvStore;
    @BindView(R.id.srl_store)
    SwipeRefreshLayout srlStore;
    @BindView(R.id.toolbar_text)
    Toolbar toolbarText;
    private LoadingDialog loadingDialog;


    private int PageIndex = 1;
    private int PageSize = 10;
    private int nextPage = 1;
    private List<GetRZTArticleListEntity.DataBean> videosData = new ArrayList<>();

    private Gson gson = new GsonBuilder().create();
    private GetRZTArticleListAdapter getRZTArticleListAdapter;
    @Override
    protected int getViewResId() {
        return R.layout.activity_mine_my_article;
    }
    @Override
    protected void init() {
        ToolBarUtil.initToolBar(toolbarText, "我的文章", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        rvStore.setLayoutManager(new LinearLayoutManager(this));
        getRZTArticleListAdapter = new GetRZTArticleListAdapter(this, this,this);
        rvStore.setAdapter(getRZTArticleListAdapter);
        srlStore.setOnRefreshListener(this);
        rvStore.addItemDecoration(new DividerGridItem(this));
        getRZTArticleListAdapter.setOnItemClickListener(this);
        loadingDialog = LoadingDialog.newInstance("加载中...");
        loadingDialog.show(getFragmentManager());
        getRZTArticleListAdapter.setEmptyView(R.layout.empty_tips);
        getRZTArticleListAdapter.setEmptyTips(R.id.tv_emptyTips, "暂无数据");
    }

    @Override
    protected void loadData() {
        String toJson = gson.toJson(new GetFindNewsCollectList(PreferencesUtil.getToken(this),PreferencesUtil.getUserId(this),nextPage + "", PageSize + ""));
        OkHttpUtil.postJson(Constant.URL.GetRZTArticleList, DesUtil.encrypt(toJson), this);
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
                case Constant.URL.GetRZTArticleList: {
                    LogUtils.e("GetRZTArticleList",  decrypt);
                    if (PageIndex == 1) {
                        videosData.clear();
                        dismissLoading();
                    }
                    removeLoadingItem();
                    GetRZTArticleListEntity getFindNewsCollectListEntity = gson.fromJson(decrypt, GetRZTArticleListEntity.class);
                    if (getFindNewsCollectListEntity.getCode() == Constant.Integers.SUC) {
                        videosData.addAll(getFindNewsCollectListEntity.getData());
                        if (getFindNewsCollectListEntity.getData().size() % PageSize == 0 && getFindNewsCollectListEntity.getData().size() != 0) {//可能还有下一页
                            videosData.add(new GetRZTArticleListEntity.DataBean(1));
                            nextPage = PageIndex + 1;
                        } else {
                            addBaseLine();
                        }
                    }else if (getFindNewsCollectListEntity.getCode() == Constant.Integers.TOKEN_OUT_OF) { //token过期
                        dismissLoading();
                        ToastUtil.initToast(this, getFindNewsCollectListEntity.getMessage());
                        startActivityForResult(new Intent(this, LoginActivity.class), Constant.Code.LoginCode);

                    }
                    else if (getFindNewsCollectListEntity.getCode() == Constant.Integers.NULL){
                        addBaseLine();
                    }else {//其他
                        dismissLoading();
                        ToastUtil.initToast(this, getFindNewsCollectListEntity.getMessage());
                    }


                }
                getRZTArticleListAdapter.setData(videosData);
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
            videosData.add(new GetRZTArticleListEntity.DataBean(2));
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
        Intent intent = new Intent(this, H5ActivityForRZTPreview.class);
        intent.putExtra("data", videosData.get(position));

        intent.putExtra("type",-1);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

    }
}


