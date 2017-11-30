package com.example.a11355.peoplescloudmedia.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.a11355.peoplescloudmedia.fragement.MyPromotionFragment;

import java.util.List;

/**
 * Created by 11355 on 2017/11/28.
 */

public class VpMyPromotionAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

    private List<MyPromotionFragment> mFragments;
    private ViewPager viewPager;


    public VpMyPromotionAdapter(FragmentManager fm, List<MyPromotionFragment> fragments, ViewPager viewPager) {
        super(fm);
        mFragments = fragments;
                            viewPager.addOnPageChangeListener(this);

        this.viewPager = viewPager;
    }


    @Override
    public Fragment getItem(int position) {

        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mFragments.size();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (position==0){
            viewPager.setCurrentItem(3,false);
        } else if (position==4){
            viewPager.setCurrentItem(1,false);

        }
    }

    @Override
    public void onPageSelected(int position) {
        
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
