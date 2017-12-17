package com.example.a11355.peoplescloudmedia.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.BaseActivity;
import com.example.a11355.peoplescloudmedia.fragement.RZTAaDFragment;
import com.example.a11355.peoplescloudmedia.fragement.RZTArticleFragment;
import com.example.a11355.peoplescloudmedia.util.TabLayoutUtils;

import butterknife.BindView;
import butterknife.OnClick;

/*
* 人众推制作
*
* */

public class RZTActivity extends BaseActivity {

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.img_add)
    ImageView imgAdd;



    private String[] titles = {"文章", "广告"};


    @Override
    protected int getViewResId() {
        return R.layout.activity_rzt;
    }

    @Override
    protected void init() {
        imgAdd.setVisibility(View.VISIBLE);
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return position == 0 ? new RZTArticleFragment() : new RZTAaDFragment();
            }

            @Override
            public int getCount() {
                return titles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });
        tab.setupWithViewPager(vp);
        TabLayoutUtils.reflex(tab, 10);

    }

    @OnClick({R.id.toolbar_iconBack, R.id.img_add})
    public void onViewClicked(View view) {
                switch (view.getId()){
                    case R.id.toolbar_iconBack:

                        onBackPressed();
                        break;
                    case R.id.img_add:

                        startActivity(new Intent(this,PickUpArticleActivity.class));
                        break;
                }
    }

}
