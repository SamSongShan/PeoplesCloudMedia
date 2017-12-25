package com.example.a11355.peoplescloudmedia.fragement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a11355.peoplescloudmedia.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusinessCardFragment extends Fragment {

    /*
    * 自媒体制作  名片
    * */
    public BusinessCardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_business_card, container, false);
    }

}
