package com.example.a11355.peoplescloudmedia.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.adapter.GetNewsListSearchAdapter;
import com.example.a11355.peoplescloudmedia.base.AbsRecyclerViewAdapter;
import com.example.a11355.peoplescloudmedia.base.BaseActivity;
import com.example.a11355.peoplescloudmedia.base.OnAdapterCallbackListener;
import com.example.a11355.peoplescloudmedia.custom.DividerGridItem;
import com.example.a11355.peoplescloudmedia.custom.LoadingDialog;
import com.example.a11355.peoplescloudmedia.model.GetNewsList;
import com.example.a11355.peoplescloudmedia.model.GetNewsListEntity;
import com.example.a11355.peoplescloudmedia.util.Constant;
import com.example.a11355.peoplescloudmedia.util.DesUtil;
import com.example.a11355.peoplescloudmedia.util.LogUtils;
import com.example.a11355.peoplescloudmedia.util.OkHttpUtil;
import com.example.a11355.peoplescloudmedia.util.PhoneUtil;
import com.example.a11355.peoplescloudmedia.util.SharedPreferencesUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class FindSearchActivity extends BaseActivity implements TextView.OnEditorActionListener, OkHttpUtil.OnDataListener, AbsRecyclerViewAdapter.OnItemClickListener, OnAdapterCallbackListener, View.OnClickListener {
    @BindView(R.id.rv_store)
    RecyclerView rvStore;
    @BindView(R.id.et_search)
    EditText etSearch;
    private Gson gson = new GsonBuilder().create();
    private int PageIndex = 1;
    private int PageSize = 10;
    private int nextPage = 1;
    private LoadingDialog loadingDialog;
    private List<GetNewsListEntity.DataBean.ArtilesBean> newsData = new ArrayList<>();
    private GetNewsListSearchAdapter getNewsListSearchAdapter;
    private String key;


    /*
        *新闻页搜索
        * */
    @Override
    protected int getViewResId() {
        return R.layout.activity_find_search;
    }


    @OnClick(R.id.tv_cancel)
    public void onViewClicked(View v) {
        PhoneUtil.hideKeyboard(v);
        onBackPressed();
    }

    @Override
    protected void init() {
        etSearch.setOnEditorActionListener(this);
        rvStore.setLayoutManager(new LinearLayoutManager(this));
        getNewsListSearchAdapter = new GetNewsListSearchAdapter(this, this, this);
        rvStore.addItemDecoration(new DividerGridItem(this));
        rvStore.setAdapter(getNewsListSearchAdapter);
        getNewsListSearchAdapter.setOnItemClickListener(this);


        getNewsListSearchAdapter.setEmptyView(R.layout.empty_tips);
        getNewsListSearchAdapter.setEmptyTips(R.id.tv_emptyTips, "暂无数据");

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH && !TextUtils.isEmpty(etSearch.getText().toString())) {//点击搜索
            PhoneUtil.hideKeyboard(v);
            loadingDialog = LoadingDialog.newInstance("搜索中...");
            loadingDialog.show(getFragmentManager());
            key = etSearch.getText().toString();
            String toJson = gson.toJson(new GetNewsList("default", key, nextPage + "", PageSize + ""));
            OkHttpUtil.postJson(Constant.URL.GetNewsList, DesUtil.encrypt(toJson), this);
            return true;
        }
        return false;
    }

    @Override
    public void onResponse(String url, String json) {
        if (!TextUtils.isEmpty(json)) {
            String decrypt = DesUtil.decrypt(json);
            switch (url) {
                case Constant.URL.GetNewsList: {
                    dismissLoading();
                    LogUtils.e("GetNewsList", decrypt);
                    if (PageIndex == 1) {
                        newsData.clear();

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
                getNewsListSearchAdapter.setData(newsData);
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
    protected void loadData() {
        String toJson = gson.toJson(new GetNewsList("default", key, nextPage + "", PageSize + ""));
        OkHttpUtil.postJson(Constant.URL.GetNewsList, DesUtil.encrypt(toJson), this);
    }

    @Override
    public void onItemClick(View v, int position) {
        Intent intent = new Intent(this, H5ActivityForNewsDetical.class);
        intent.putExtra("url", String.format(Constant.URL.NewsDetailsLink,newsData.get(position).getId()+"", SharedPreferencesUtil.getUserId(this)));

        intent.putExtra("data", newsData.get(position));
        startActivity(intent);
    }

    private void dismissLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }

    }

    @Override
    public void onClick(View v) {

    }
}
