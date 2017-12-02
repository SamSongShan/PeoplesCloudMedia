package com.example.a11355.peoplescloudmedia.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.adapter.MyPromotionAdapter;
import com.example.a11355.peoplescloudmedia.adapter.VpMyPromotionAdapter;
import com.example.a11355.peoplescloudmedia.base.BaseActivity;
import com.example.a11355.peoplescloudmedia.base.OnAdapterCallbackListener;
import com.example.a11355.peoplescloudmedia.custom.CoverModeTransformer;
import com.example.a11355.peoplescloudmedia.custom.GridDividerItemDecoration;
import com.example.a11355.peoplescloudmedia.custom.LoadingDialog;
import com.example.a11355.peoplescloudmedia.fragement.MyPromotionFragment;
import com.example.a11355.peoplescloudmedia.model.GetRecommendUserList;
import com.example.a11355.peoplescloudmedia.model.GetRecommendUserListEntity;
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

public class MyPromotionActivity extends BaseActivity implements ViewPager.OnPageChangeListener, OkHttpUtil.OnDataListener, SwipeRefreshLayout.OnRefreshListener, OnAdapterCallbackListener {

    /*
    * 我的推广
    * */
    @BindView(R.id.toolbar_text)
    Toolbar toolbarText;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.rv_store)
    RecyclerView rvStore;
    @BindView(R.id.srl_store)
    SwipeRefreshLayout srlStore;
    @BindView(R.id.tv_describe)
    TextView tvDescribe;

    private List<MyPromotionFragment> fragmentList;

    private Gson gson = new GsonBuilder().create();
    private LoadingDialog loadingDialog;


    private int PageIndex = 1;
    private int PageNext = 1;
    private int PageSize = 10;

    private int type = 2; //（0-直接粉丝，1-间接粉丝，2-粉丝总数）

    List<GetRecommendUserListEntity.DataBean.UserListBean> dataBeans = new ArrayList<>();
    private MyPromotionAdapter myPromotionAdapter;

    @Override
    protected int getViewResId() {
        return R.layout.activity_my_promotion;
    }

    @Override
    protected void init() {
        ToolBarUtil.initToolBar(toolbarText, "我的推广", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        if (fragmentList == null) {
            fragmentList = new ArrayList<>();
        }
        fragmentList.add(MyPromotionFragment.instanceFragment(2));
        fragmentList.add(MyPromotionFragment.instanceFragment(0));
        fragmentList.add(MyPromotionFragment.instanceFragment(1));
        fragmentList.add(MyPromotionFragment.instanceFragment(2));
        fragmentList.add(MyPromotionFragment.instanceFragment(0));

        VpMyPromotionAdapter vpMyPromotionAdapter = new VpMyPromotionAdapter(getSupportFragmentManager(), fragmentList, vp);
        vp.setAdapter(vpMyPromotionAdapter);
        vp.setOffscreenPageLimit(5);
        vp.setCurrentItem(2, false);
        vp.addOnPageChangeListener(this);

        vp.setPageTransformer(true, new CoverModeTransformer(vp));
        srlStore.setOnRefreshListener(this);
        rvStore.setLayoutManager(new LinearLayoutManager(this));
        rvStore.addItemDecoration(new GridDividerItemDecoration(this));
        myPromotionAdapter = new MyPromotionAdapter(this, this);
        rvStore.setAdapter(myPromotionAdapter);
        myPromotionAdapter.setEmptyView(R.layout.empty_tips);
        myPromotionAdapter.setEmptyTips(R.id.tv_emptyTips, "暂无数据");
        tvDescribe.setText("粉丝总数");
    }

    @Override
    protected void loadData() {
        loadingDialog = LoadingDialog.newInstance("加载中...");
        loadingDialog.show(getFragmentManager());
        getData();
    }

    private void getData() {
        GetRecommendUserList getRecommendUserList = new GetRecommendUserList(PreferencesUtil.getToken(this), PreferencesUtil.getUserId(this), PageNext + "", PageSize + "", type + "");
        OkHttpUtil.postJson(Constant.URL.GetRecommendUserList, DesUtil.encrypt(gson.toJson(getRecommendUserList)), this);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        PageIndex = 1;
        PageNext = 1;
        if (position == 2) {
            type = 2;
            tvDescribe.setText("粉丝总数");

        } else if (position == 1) {
            type = 0;
            tvDescribe.setText("直接粉丝");

        } else if (position == 3) {
            type = 1;
            tvDescribe.setText("间接粉丝");



        }
        getData();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onResponse(String url, String json) {
        if (!TextUtils.isEmpty(json)) {
            String decrypt = DesUtil.decrypt(json);
            if (Constant.URL.GetRecommendUserList.equals(url)) {
                LogUtils.e("GetRecommendUserList", decrypt);
                GetRecommendUserListEntity getRecommendUserListEntity = gson.fromJson(decrypt,GetRecommendUserListEntity.class);
                dismissLoading();
                removeLoadingItem();
                if (PageIndex == 1) {
                    dataBeans.clear();
                    if (type == 0) {
                        fragmentList.get(1).setFansNum(getRecommendUserListEntity.getData().getCount() + "");

                    } else if (type == 1) {
                        fragmentList.get(3).setFansNum(getRecommendUserListEntity.getData().getCount() + "");

                    } else if (type == 2) {
                        fragmentList.get(2).setFansNum(getRecommendUserListEntity.getData().getCount() + "");

                    }

                }
                if (getRecommendUserListEntity.getCode() == Constant.Integers.SUC) { //成功

                    dataBeans.addAll(getRecommendUserListEntity.getData().getUserList());
                    PageNext = PageIndex + 1;
                    if (getRecommendUserListEntity.getData().getUserList().size() % PageSize == 0 && getRecommendUserListEntity.getData().getUserList() != null
                            ) {
                        dataBeans.add(new GetRecommendUserListEntity.DataBean.UserListBean(1));
                    } else {
                        addBaseLine();
                    }


                } else if (getRecommendUserListEntity.getCode() == Constant.Integers.TOKEN_OUT_OF) { //token过期

                    ToastUtil.initToast(this, getRecommendUserListEntity.getMessage());
                    startActivityForResult(new Intent(this, LoginActivity.class), Constant.Code.LoginCode);

                } else {//其他
                    addBaseLine();
                    ToastUtil.initToast(this, getRecommendUserListEntity.getMessage());
                }
                myPromotionAdapter.setData(dataBeans);
            }

        }
    }

    @Override
    public void onFailure(String url, String error) {

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
    public void onRefresh() {
        PageIndex = 1;
        PageNext = 1;
        getData();
    }

    private void removeLoadingItem() {
        if (dataBeans.size() > 0) {
            if (dataBeans.get(dataBeans.size() - 1).getType() == 1) {
                dataBeans.remove(dataBeans.size() - 1);
            }
        }
    }

    private void addBaseLine() {
        if (PageIndex != 1) {
            dataBeans.add(new GetRecommendUserListEntity.DataBean.UserListBean(2));
        }
    }

    @Override
    public void onCallback() {
        PageIndex++;
        getData();
    }
}
