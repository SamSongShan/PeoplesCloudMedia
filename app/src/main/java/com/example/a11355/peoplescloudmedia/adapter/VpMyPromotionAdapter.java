package com.example.a11355.peoplescloudmedia.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;

/**
 * Created by 11355 on 2017/11/28.
 */

public class VpMyPromotionAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

    private List<Fragment> mFragments;
    private ViewPager viewPager;

    public VpMyPromotionAdapter(FragmentManager fm, List<Fragment> fragments, ViewPager viewPager) {
        super(fm);
        mFragments=fragments;
        this.viewPager = viewPager;
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public Fragment getItem(int arg0) {
        // TODO Auto-generated method stub
        return mFragments.get(arg0);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mFragments.size();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if ( mFragments.size() > 1) { //多于1，才会循环跳转
            if (position == 4) { //首位之前，跳转到末尾（N）
                viewPager.setCurrentItem(1, false);
            } else if (position == 0) { //末位之后，跳转到首位（1）
                viewPager.setCurrentItem(3, false); //false:不显示跳转过程的动画

            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
