package com.example.a11355.peoplescloudmedia.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.BaseActivity;
import com.example.a11355.peoplescloudmedia.util.ToolBarUtil;

import butterknife.BindView;

/*
* 修改昵称  个性签名   个人资料
* */
public class NikcAndSignatureActivity extends BaseActivity {


    @BindView(R.id.et)
    EditText et;
    @BindView(R.id.toolbar_text)
    Toolbar toolbarText;

    @Override
    protected int getViewResId() {
        return R.layout.activity_nikc_and_signature;
    }

    @Override
    protected void init() {
        ToolBarUtil.initToolBar(toolbarText, "个人资料", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        }, "确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里提交一下
            }
        });
    }


}
