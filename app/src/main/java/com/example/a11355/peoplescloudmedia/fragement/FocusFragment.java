package com.example.a11355.peoplescloudmedia.fragement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FocusFragment extends BaseFragment {


    private int type;

    public FocusFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getViewResId() {
        return R.layout.fragment_focus;
    }

    public static FocusFragment instance(int type) {

        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        FocusFragment focusFragment = new FocusFragment();
        focusFragment.setArguments(bundle);
        return focusFragment;

    }

    @Override
    protected void init(View v) {
        type = getArguments().getInt("type");
    }
}
