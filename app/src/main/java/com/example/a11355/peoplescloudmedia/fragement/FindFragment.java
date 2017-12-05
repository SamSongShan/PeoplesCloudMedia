package com.example.a11355.peoplescloudmedia.fragement;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.BaseFragment;
import com.example.a11355.peoplescloudmedia.custom.LoadingDialog;
import com.example.a11355.peoplescloudmedia.model.GetNewsTypeListEntity;
import com.example.a11355.peoplescloudmedia.util.Constant;
import com.example.a11355.peoplescloudmedia.util.DesUtil;
import com.example.a11355.peoplescloudmedia.util.LogUtils;
import com.example.a11355.peoplescloudmedia.util.OkHttpUtil;
import com.example.a11355.peoplescloudmedia.util.TabLayoutUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import butterknife.BindView;


/**
 * 发现
 */
public class FindFragment extends BaseFragment implements OkHttpUtil.OnDataListener {


    @BindView(R.id.tab_find)
    TabLayout tabFind;
    @BindView(R.id.vp_find)
    ViewPager vpFind;
    private Gson gson = new GsonBuilder().create();
    private LoadingDialog loadingDialog;

    public FindFragment() {
        // Required empty public constructor
    }

    @Override
    protected void init(View v) {
        loadingDialog = LoadingDialog.newInstance("加载中...");
        loadingDialog.show(getActivity().getFragmentManager());
    }

    @Override
    protected void loadData() {
        OkHttpUtil.postJson(Constant.URL.GetNewsTypeList, "0", this);
    }

    @Override
    protected int getViewResId() {
        return R.layout.fragment_find;
    }


    @Override
    public void onResponse(String url, String json) {
        dismissLoading();
        if (!TextUtils.isEmpty(json)) {

            String decrypt = DesUtil.decrypt(json);
            if (Constant.URL.GetNewsTypeList.equals(url)) {
                LogUtils.e("GetNewsTypeList", decrypt);
                GetNewsTypeListEntity getNewsTypeListEntity = gson.fromJson(decrypt, GetNewsTypeListEntity.class);
                initVP(getNewsTypeListEntity.getData());
            }
        }

    }

    private void initVP(final List<GetNewsTypeListEntity.DataBean> data) {
        vpFind.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return FindArticleFragment.instanceFragment(data.get(position).getId() + "", position);
            }

            @Override
            public int getCount() {
                return data.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return data.get(position).getName();
            }
        });

        tabFind.setupWithViewPager(vpFind);

        TabLayoutUtils.reflex(tabFind, 10);
    }

    @Override
    public void onFailure(String url, String error) {

    }

    private void dismissLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }

    }
}
