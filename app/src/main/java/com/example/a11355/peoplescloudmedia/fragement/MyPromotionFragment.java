package com.example.a11355.peoplescloudmedia.fragement;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.BaseFragment;

import butterknife.BindView;

/**
 * 我的推广头部
 */
public class MyPromotionFragment extends BaseFragment {


    @BindView(R.id.tv)
    TextView tv;
    private int type;

    public MyPromotionFragment() {
    }

    public static MyPromotionFragment instanceFragment(int type) {
        MyPromotionFragment myPromotionFragment = new MyPromotionFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        myPromotionFragment.setArguments(bundle);


        return myPromotionFragment;
    }

    @Override
    protected int getViewResId() {
        return R.layout.fragment_my_promotion;
    }

    @Override
    protected void init(View v) {
        type = getArguments().getInt("type");

            switch (type){
                case 0:
                    tv.setBackground(getResources().getDrawable(R.drawable.shape_bluedark_5));
                    break;
                case 1:
                    tv.setBackground(getResources().getDrawable(R.drawable.shape_lightred_5));

                    break;
                case 2:
                    tv.setBackground(getResources().getDrawable(R.drawable.shape_orangedark_5));

                    break;

            }
    }

}
