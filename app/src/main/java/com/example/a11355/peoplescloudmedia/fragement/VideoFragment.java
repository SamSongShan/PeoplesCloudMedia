package com.example.a11355.peoplescloudmedia.fragement;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.activity.VideoActivity;
import com.example.a11355.peoplescloudmedia.adapter.GetVideoListAdapter;
import com.example.a11355.peoplescloudmedia.base.AbsRecyclerViewAdapter;
import com.example.a11355.peoplescloudmedia.base.BaseFragment;
import com.example.a11355.peoplescloudmedia.base.OnAdapterCallbackListener;
import com.example.a11355.peoplescloudmedia.custom.LoadingDialog;
import com.example.a11355.peoplescloudmedia.model.GetVideoList;
import com.example.a11355.peoplescloudmedia.model.GetVideoListEntity;
import com.example.a11355.peoplescloudmedia.util.Constant;
import com.example.a11355.peoplescloudmedia.util.DesUtil;
import com.example.a11355.peoplescloudmedia.util.LogUtils;
import com.example.a11355.peoplescloudmedia.util.OkHttpUtil;
import com.example.a11355.peoplescloudmedia.util.PreferencesUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 发现
 */
public class VideoFragment extends BaseFragment implements OkHttpUtil.OnDataListener, OnAdapterCallbackListener, SwipeRefreshLayout.OnRefreshListener, AbsRecyclerViewAdapter.OnItemClickListener {


    @BindView(R.id.rv_store)
    RecyclerView rvStore;
    @BindView(R.id.srl_store)
    SwipeRefreshLayout srlStore;
    private LoadingDialog loadingDialog;


    private int PageIndex = 1;
    private int PageSize = 10;
    private int nextPage = 1;
    private List<GetVideoListEntity.DataBean> videosData = new ArrayList<>();

    private Gson gson = new GsonBuilder().create();
    private GetVideoListAdapter getVideoListAdapter;

    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getViewResId() {
        return R.layout.fragment_video;
    }

    @Override
    protected void init(View v) {
        rvStore.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        getVideoListAdapter = new GetVideoListAdapter(getActivity(), this);
        rvStore.setAdapter(getVideoListAdapter);
        srlStore.setOnRefreshListener(this);
        getVideoListAdapter.setOnItemClickListener(this);
        loadingDialog = LoadingDialog.newInstance("加载中...");
        loadingDialog.show(getActivity().getFragmentManager());
        getVideoListAdapter.setEmptyView(R.layout.empty_tips);
        getVideoListAdapter.setEmptyTips(R.id.tv_emptyTips, "暂无数据");
    }

    @Override
    protected void loadData() {
        String toJson = gson.toJson(new GetVideoList(PreferencesUtil.getUserId(getContext()), nextPage + "", PageSize + ""));
        OkHttpUtil.postJson(Constant.URL.GetVideoList, DesUtil.encrypt(toJson), this);
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
                case Constant.URL.GetVideoList: {
                    LogUtils.e("GetVideoList", "onResponse: " + decrypt);
                    if (PageIndex == 1) {
                        videosData.clear();
                        dismissLoading();
                    }
                    removeLoadingItem();
                    GetVideoListEntity getVideoListEntity = gson.fromJson(decrypt, GetVideoListEntity.class);
                    if (getVideoListEntity.getCode() == Constant.Integers.SUC) {
                        videosData.addAll(getVideoListEntity.getData());
                        if (getVideoListEntity.getData().size() % PageSize == 0 && getVideoListEntity.getData().size() != 0) {//可能还有下一页
                            videosData.add(new GetVideoListEntity.DataBean(1));
                            nextPage = PageIndex + 1;
                        } else {
                            addBaseLine();
                        }
                    } else {
                        addBaseLine();
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
            videosData.add(new GetVideoListEntity.DataBean(2));
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

        Intent intent = new Intent(getContext(), VideoActivity.class);
        intent.putExtra("data",videosData.get(position));

        startActivity(intent);


    }
}
