package com.example.a11355.peoplescloudmedia.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.BaseActivity;
import com.example.a11355.peoplescloudmedia.util.ToastUtil;
import com.example.a11355.peoplescloudmedia.util.ToolBarUtil;

import butterknife.BindView;

/*
*
* 自媒体制作添加  网址*/
public class ZMTZZAddLinkActivity extends BaseActivity {


    @BindView(R.id.toolbar_text)
    Toolbar toolbarText;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_link)
    EditText etLink;

    @Override
    protected int getViewResId() {
        return R.layout.activity_zmtzzadd_link;
    }

    @Override
    protected void init() {
        ToolBarUtil.initToolBar(toolbarText, "编辑", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        }, "完成", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etName.getText().toString().trim())) {
                    ToastUtil.initToast(ZMTZZAddLinkActivity.this, "请输入链接名称");
                } else if (TextUtils.isEmpty(etLink.getText().toString().trim())) {
                    ToastUtil.initToast(ZMTZZAddLinkActivity.this, "请输入链接");

                } else {
                    Intent intent = new Intent();
                    intent.putExtra("linkName", etName.getText().toString().trim());
                    intent.putExtra("link", etLink.getText().toString().trim());

                    setResult(RESULT_OK, intent);
                    onBackPressed();

                }
            }
        });
    }
}
