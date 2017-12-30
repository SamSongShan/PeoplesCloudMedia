package com.example.a11355.peoplescloudmedia.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.BaseActivity;
import com.example.a11355.peoplescloudmedia.fragement.BusinessCardFragment;
import com.example.a11355.peoplescloudmedia.fragement.ProductFragment;
import com.example.a11355.peoplescloudmedia.util.TabLayoutUtils;

import butterknife.BindView;
import butterknife.OnClick;

/*
*自媒体制作
*
* */
public class ZMTZZActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;

    private String[] titles = {"名片", "产品"};
    private BusinessCardFragment businessCardFragment;
    private ProductFragment productFragment;
    private int position;

    @Override
    protected int getViewResId() {
        return R.layout.activity_zmtzz;
    }

    @Override
    protected void init() {
        businessCardFragment = new BusinessCardFragment();
        productFragment = new ProductFragment();

        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return position == 0 ? businessCardFragment :productFragment ;
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
        vp.addOnPageChangeListener(this);
        tab.setupWithViewPager(vp);
        TabLayoutUtils.reflex(tab, 10);
    }



    @OnClick({R.id.toolbar_iconBack, R.id.tv_preview})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_iconBack:
                onBackPressed();
                break;
            case R.id.tv_preview:

                if (position==0){
                    businessCardFragment.setPreview();
                }
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        this.position = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    public interface SetPreview{


        void setPreview();
    }

}
