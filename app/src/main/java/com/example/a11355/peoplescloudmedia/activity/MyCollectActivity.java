package com.example.a11355.peoplescloudmedia.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.BaseActivity;
import com.example.a11355.peoplescloudmedia.fragement.CollecVideoListFragment;
import com.example.a11355.peoplescloudmedia.fragement.NewsCollectListFragment;
import com.example.a11355.peoplescloudmedia.util.TabLayoutUtils;

import butterknife.BindView;
import butterknife.OnClick;
     /*
     * 我的收藏
     * */
public class MyCollectActivity extends BaseActivity {

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;

    private String[] titles = {"文章", "视频"};

    @Override
    protected int getViewResId() {
        return R.layout.activity_my_collect;
    }



    @Override
    protected void init() {

        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return position == 0 ? new NewsCollectListFragment() : new CollecVideoListFragment();
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


    @OnClick(R.id.toolbar_iconBack)
    public void onViewClicked() {

        onBackPressed();
    }
}
