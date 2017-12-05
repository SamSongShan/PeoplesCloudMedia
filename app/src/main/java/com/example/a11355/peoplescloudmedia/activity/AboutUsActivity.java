package com.example.a11355.peoplescloudmedia.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.BaseActivity;
import com.example.a11355.peoplescloudmedia.util.PhoneUtil;
import com.example.a11355.peoplescloudmedia.util.ToolBarUtil;

import butterknife.BindView;

public class AboutUsActivity extends BaseActivity {
    @BindView(R.id.toolbar_text)
    Toolbar toolbarText;
    @BindView(R.id.tv_version)
    TextView tvVersion;

    /*
        * 关于我们
        * */
    @Override
    protected int getViewResId() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void init() {

        ToolBarUtil.initToolBar(toolbarText, "关于我们V", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tvVersion.setText("人众云媒" + PhoneUtil.getAppVersion(this));
    }
}
