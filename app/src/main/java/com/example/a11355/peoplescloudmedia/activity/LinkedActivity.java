package com.example.a11355.peoplescloudmedia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.model.LinkContent;

public class LinkedActivity extends AppCompatActivity {

    private EditText etLinked, etDesc;
    private static final int REQUEST_CODE_EDIT_LINKED = 2001;//编辑文本

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linked);
        etLinked = (EditText) findViewById(R.id.et_txteditor_linked);
        etDesc = (EditText) findViewById(R.id.et_txteditor_desc);
    }

    public void onOK(View view) {
        if (TextUtils.isEmpty(etLinked.getText().toString())) {
            Toast.makeText(this, getString(R.string.link_dont_null), Toast.LENGTH_SHORT).show();
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
