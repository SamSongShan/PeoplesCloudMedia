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

    @Override
    public void onPageSelected(int position) {
        if ( mFragments.size() > 1) { //多于1，才会循环跳转
            if ( position < 1) { //首位之前，跳转到末尾（N）
                position = 2;
                setCurrentItem(position,false);
            } else if ( position > 2) { //末位之后，跳转到首位（1）
                setCurrentItem(1,false); //false:不显示跳转过程的动画
                position = 1;
            }
        }
    }

}
