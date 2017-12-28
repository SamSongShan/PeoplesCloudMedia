package com.example.a11355.peoplescloudmedia.fragement;


import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.BaseFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * 自媒体制作  名片
 */
public class BusinessCardFragment extends BaseFragment  {



    private Gson gson = new GsonBuilder().create();



    @Override
    protected int getViewResId() {
        return R.layout.fragment_business_card;
    }


}


