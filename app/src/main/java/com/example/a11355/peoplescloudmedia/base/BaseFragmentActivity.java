package com.example.a11355.peoplescloudmedia.base;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.example.a11355.peoplescloudmedia.util.StatusBarUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 所有Activity的基类
 */
public abstract class BaseFragmentActivity extends FragmentActivity {

    private Unbinder unbinder;
    private static final String TAG = "BaseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getViewResId());
        StatusBarUtils.setStatusBarLightMode(this, Color.WHITE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//手机竖屏
        unbinder = ButterKnife.bind(this);
        init();
        loadData();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
     //   super.onSaveInstanceState(outState);

    }

    protected abstract int getViewResId();

    protected void init(){}

    protected void loadData(){}

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        FragmentManager fm = getSupportFragmentManager();
        int index = requestCode >> 16;
        if (index != 0) {
            index--;
            if (fm.getFragments() == null || index < 0
                    || index >= fm.getFragments().size()) {
                Log.w(TAG, "Activity result fragment index out of range: 0x"
                        + Integer.toHexString(requestCode));
                return;
            }
            Fragment frag = fm.getFragments().get(index);
            if (frag == null) {
                Log.w(TAG, "Activity result no fragment exists for index: 0x"
                        + Integer.toHexString(requestCode));
            } else {
                handleResult(frag, requestCode, resultCode, data);
            }
            return;
        }

    }

    /**
     * 递归调用，对所有子Fragement生效
     *
     * @param frag
     * @param requestCode
     * @param resultCode
     * @param data
     */
    private void handleResult(Fragment frag, int requestCode, int resultCode,
                              Intent data) {
        frag.onActivityResult(requestCode & 0xffff, resultCode, data);
        @SuppressLint("RestrictedApi") List<Fragment> frags = frag.getChildFragmentManager().getFragments();
        if (frags != null) {
            for (Fragment f : frags) {
                if (f != null)
                    handleResult(f, requestCode, resultCode, data);
            }
        }
    }
}
