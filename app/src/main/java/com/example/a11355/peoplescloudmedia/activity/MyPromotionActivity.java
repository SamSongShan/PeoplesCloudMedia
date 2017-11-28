package com.example.a11355.peoplescloudmedia.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.adapter.VpMyPromotionAdapter;
import com.example.a11355.peoplescloudmedia.base.BaseActivity;
import com.example.a11355.peoplescloudmedia.fragement.MyPromotionFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MyPromotionActivity extends BaseActivity {

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
    private List<Fragment> fragmentList;

    @Override
    protected int getViewResId() {
        return R.layout.activity_my_promotion;
    }

    @Override
    protected void init() {
        if (fragmentList == null) {
            fragmentList = new ArrayList<>();
        }
        fragmentList.add(MyPromotionFragment.instanceFragment(0));
        fragmentList.add(MyPromotionFragment.instanceFragment(1));
        fragmentList.add(MyPromotionFragment.instanceFragment(2));
        VpMyPromotionAdapter vpMyPromotionAdapter = new VpMyPromotionAdapter(getSupportFragmentManager(), fragmentList);
        vp.setAdapter(vpMyPromotionAdapter);
    }
}
