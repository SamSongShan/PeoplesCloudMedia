package com.example.a11355.peoplescloudmedia.fragement;


import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.activity.H5Activity;
import com.example.a11355.peoplescloudmedia.adapter.GetMessageListAdapter;
import com.example.a11355.peoplescloudmedia.base.AbsRecyclerViewAdapter;
import com.example.a11355.peoplescloudmedia.base.BaseFragment;
import com.example.a11355.peoplescloudmedia.base.OnAdapterCallbackListener;
import com.example.a11355.peoplescloudmedia.custom.DividerGridItem;
import com.example.a11355.peoplescloudmedia.custom.LoadingDialog;
import com.example.a11355.peoplescloudmedia.model.GetMessageList;
import com.example.a11355.peoplescloudmedia.model.GetMessageListEntity;
import com.example.a11355.peoplescloudmedia.util.Constant;
import com.example.a11355.peoplescloudmedia.util.DesUtil;
import com.example.a11355.peoplescloudmedia.util.LogUtils;
import com.example.a11355.peoplescloudmedia.util.OkHttpUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.example.a11355.peoplescloudmedia.custom.GridDividerItemDecoration.ATTRS_B;

/**
 * 消息
 */
public class MessageFragment extends BaseFragment implements OnAdapterCallbackListener, OkHttpUtil.OnDataListener, SwipeRefreshLayout.OnRefreshListener, AbsRecyclerViewAdapter.OnItemClickListener, View.OnClickListener {

    @BindView(R.id.rv_store)
    RecyclerView rvStore;
    @BindView(R.id.srl_store)
    SwipeRefreshLayout srlStore;
    private LoadingDialog loadingDialog;


    private int PageIndex = 1;
    private int PageSize = 10;
    private int nextPage = 1;
    private List<GetMessageListEntity.DataBean> messageData = new ArrayList<>();

    private Gson gson = new GsonBuilder().create();
    private GetMessageListAdapter getMessageListAdapter;

    public MessageFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getViewResId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void init(View v) {
        rvStore.setLayoutManager(new LinearLayoutManager(getContext()));
        getMessageListAdapter = new GetMessageListAdapter(getActivity(), this, this);
        rvStore.addItemDecoration(new DividerGridItem(getContext(), ATTRS_B));
        rvStore.setAdapter(getMessageListAdapter);
        srlStore.setOnRefreshListener(this);
        getMessageListAdapter.setOnItemClickListener(this);
        loadingDialog = LoadingDialog.newInstance("加载中...");
        loadingDialog.show(getActivity().getFragmentManager());
        getMessageListAdapter.setEmptyView(R.layout.empty_tips);
        getMessageListAdapter.setEmptyTips(R.id.tv_emptyTips, "暂无数据");
    }

    @Override
    protected void loadData() {
        String toJson = gson.toJson(new GetMessageList(nextPage + "", PageSize + ""));
        OkHttpUtil.postJson(Constant.URL.GetMessageList, DesUtil.encrypt(toJson), this);
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
                case Constant.URL.GetMessageList: {
                    LogUtils.e("GetMessageList", decrypt);
                    if (PageIndex == 1) {
                        messageData.clear();
                        dismissLoading();
                    }
                    removeLoadingItem();
                    GetMessageListEntity getMessageListEntity = gson.fromJson(decrypt, GetMessageListEntity.class);
                    if (getMessageListEntity.getCode() == Constant.Integers.SUC) {
                        messageData.addAll(getMessageListEntity.getData());
                        if (getMessageListEntity.getData().size() % PageSize == 0 && getMessageListEntity.getData().size() != 0) {//可能还有下一页
                            messageData.add(new GetMessageListEntity.DataBean(1));
                            nextPage = PageIndex + 1;
                        } else {
                            addBaseLine();
                        }
                    } else {
                        addBaseLine();
                    }

                }
                getMessageListAdapter.setData(messageData);
                break;
            }
        }
    }

    @Override
    public void onFailure(String url, String error) {

    }

    private void removeLoadingItem() {
        if (messageData.size() > 0) {
            if (messageData.get(messageData.size() - 1).getType() == 1) {
                messageData.remove(messageData.size() - 1);
            }
        }
    }

    private void addBaseLine() {
        if (PageIndex != 1) {
            messageData.add(new GetMessageListEntity.DataBean(2));
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

    }

    @Override
    public void onClick(View v) {  //点击查看
        switch (v.getId()) {
            case R.id.tv_check:
                int tag = (int) v.getTag();
                Intent intent = new Intent(getContext(), H5Activity.class);
                intent.putExtra("title", messageData.get(tag).getFullHead());
                intent.putExtra("url", Constant.URL.MessageDetailsLink + messageData.get(tag).getMessageId());
                startActivity(intent);
                break;

        }
    }
}

