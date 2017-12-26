package com.example.a11355.peoplescloudmedia.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.BaseActivity;
import com.example.a11355.peoplescloudmedia.model.LinkContent;
import com.example.a11355.peoplescloudmedia.util.ToolBarUtil;

import butterknife.BindView;

public class LinkedActivity extends BaseActivity {

    @BindView(R.id.toolbar_text)
    Toolbar toolbarText;
    private EditText etLinked, etDesc;
    private static final int REQUEST_CODE_EDIT_LINKED = 2001;//编辑文本


    @Override
    protected int getViewResId() {
        return R.layout.activity_linked;
    }

    @Override
    protected void init() {

        ToolBarUtil.initToolBar(toolbarText, "添加链接", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        etLinked = (EditText) findViewById(R.id.et_txteditor_linked);
        etDesc = (EditText) findViewById(R.id.et_txteditor_desc);
    }

    public void onOK(View view) {
        if (TextUtils.isEmpty(etLinked.getText().toString())) {
            Toast.makeText(this, getString(R.string.link_dont_null), Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(etDesc.getText().toString())) {
            Toast.makeText(this, "描述不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent();
        LinkContent linkContent = new LinkContent();
        linkContent.setTitle(TextUtils.isEmpty(etDesc.getText().toString()) ? getString(R.string.link_address) : etDesc.getText().toString());
        linkContent.setLink(etLinked.getText().toString());
        intent.putExtra("linkContent", linkContent);
        this.setResult(REQUEST_CODE_EDIT_LINKED, intent);
        finish();
    }


}
