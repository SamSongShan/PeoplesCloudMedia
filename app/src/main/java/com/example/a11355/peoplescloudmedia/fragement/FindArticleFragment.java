package com.example.a11355.peoplescloudmedia.fragement;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.activity.H5Activity;
import com.example.a11355.peoplescloudmedia.adapter.GetNewsListAdapter;
import com.example.a11355.peoplescloudmedia.base.AbsRecyclerViewAdapter;
import com.example.a11355.peoplescloudmedia.base.BaseFragment;
import com.example.a11355.peoplescloudmedia.base.OnAdapterCallbackListener;
import com.example.a11355.peoplescloudmedia.custom.DividerGridItem;
import com.example.a11355.peoplescloudmedia.custom.LoadingDialog;
import com.example.a11355.peoplescloudmedia.model.GetNewsList;
import com.example.a11355.peoplescloudmedia.model.GetNewsListEntity;
import com.example.a11355.peoplescloudmedia.util.Constant;
import com.example.a11355.peoplescloudmedia.util.DesUtil;
import com.example.a11355.peoplescloudmedia.util.LogUtils;
import com.example.a11355.peoplescloudmedia.util.OkHttpUtil;
import com.example.a11355.peoplescloudmedia.util.SharedPreferencesUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.example.a11355.peoplescloudmedia.custom.DividerGridItem.ATTRS_B;

/**
 * 发现文章模块
 */
public class FindArticleFragment extends BaseFragment  implements OnAdapterCallbackListener, OkHttpUtil.OnDataListener, SwipeRefreshLayout.OnRefreshListener, AbsRecyclerViewAdapter.OnItemClickListener, View.OnClickListener {
    @BindView(R.id.rv_store)
    RecyclerView rvStore;
    @BindView(R.id.srl_store)
    SwipeRefreshLayout srlStore;
    private LoadingDialog loadingDialog;


    private int PageIndex = 1;
    private int PageSize = 5;
    private int nextPage = 1;
    private List<GetNewsListEntity.DataBean.ArtilesBean> newsData = new ArrayList<>();

    private Gson gson = new GsonBuilder().create();
    private GetNewsListAdapter getNewsListAdapter;
    private String typeId;

    public FindArticleFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getViewResId() {
        return R.layout.fragment_find_article;
    }

    public static FindArticleFragment instanceFragment(String typeId) {

        FindArticleFragment findArticleFragment = new FindArticleFragment();
        Bundle bundle = new Bundle();
        bundle.putString("typeId", typeId);
        findArticleFragment.setArguments(bundle);

        return findArticleFragment;

    }

    @Override
    protected void init(View v) {
        typeId = getArguments().getString("typeId");

        rvStore.setLayoutManager(new LinearLayoutManager(getContext()));
        getNewsListAdapter = new GetNewsListAdapter(getActivity(), this, this);
        rvStore.addItemDecoration(new DividerGridItem(getContext(), ATTRS_B));
        rvStore.setAdapter(getNewsListAdapter);
        srlStore.setOnRefreshListener(this);
        getNewsListAdapter.setOnItemClickListener(this);
        /*loadingDialog = LoadingDialog.newInstance("加载中...");
        loadingDialog.show(getActivity().getFragmentManager());*/
        getNewsListAdapter.setEmptyView(R.layout.empty_tips);
        getNewsListAdapter.setEmptyTips(R.id.tv_emptyTips, "暂无数据");
    }

    @Override
    protected void loadData() {
        String toJson = gson.toJson(new GetNewsList(typeId,"default",nextPage + "", PageSize + ""));
        OkHttpUtil.postJson(Constant.URL.GetNewsList, DesUtil.encrypt(toJson), this);
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
                case Constant.URL.GetNewsList: {
                    LogUtils.e("GetNewsList", decrypt);
                    if (PageIndex == 1) {
                        newsData.clear();
                        dismissLoading();
                    }
                    removeLoadingItem();
                    GetNewsListEntity getMessageListEntity = gson.fromJson(decrypt, GetNewsListEntity.class);
                    if (getMessageListEntity.getCode() == Constant.Integers.SUC) {
                        newsData.addAll(getMessageListEntity.getData().getArtiles());
                        if (getMessageListEntity.getData().getArtiles().size() % PageSize == 0 && getMessageListEntity.getData().getArtiles().size() != 0) {//可能还有下一页
                            newsData.add(new GetNewsListEntity.DataBean.ArtilesBean(1));
                            nextPage = PageIndex + 1;
                        } else {
                            addBaseLine();
                        }
                    } else {
                        addBaseLine();
                    }

                }
                getNewsListAdapter.setData(newsData);
                break;
            }
        }
    }

    @Override
    public void onFailure(String url, String error) {

    }

    private void removeLoadingItem() {
        if (newsData.size() > 0) {
            if (newsData.get(newsData.size() - 1).getType() == 1) {
                newsData.remove(newsData.size() - 1);
            }
        }
    }

    private void addBaseLine() {
        if (PageIndex != 1) {
            newsData.add(new GetNewsListEntity.DataBean.ArtilesBean(2));
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
        Intent intent = new Intent(getContext(), H5Activity.class);
        intent.putExtra("title", newsData.get(position).getTitle());
        intent.putExtra("url", String.format(Constant.URL.NewsDetailsLink,newsData.get(position).getId()+"", SharedPreferencesUtil.getUserId(getContext())));
        startActivity(intent);
    }
   
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_focus://已查看
               
                break;
            case R.id.tv_review://已评论

                break;
            case R.id.tv_share://已分享

                break;

        }
    }
}



