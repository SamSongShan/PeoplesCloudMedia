package com.example.a11355.peoplescloudmedia.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 11355 on 2017/11/28.
 */

public class VpMyPromotionAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;

    public VpMyPromotionAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        mFragments=fragments;
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

}
