package com.example.a11355.peoplescloudmedia.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.BaseActivity;
import com.example.a11355.peoplescloudmedia.util.PhoneUtil;
import com.example.a11355.peoplescloudmedia.util.ToastUtil;
import com.example.a11355.peoplescloudmedia.util.ToolBarUtil;

import butterknife.BindView;

public class EditTitleActivity extends BaseActivity {
    @BindView(R.id.toolbar_text)
    Toolbar toolbarText;
    @BindView(R.id.et_title)
    EditText etTitle;

   /*
   * 图片编辑  标题
   *
   * */

    @Override
    protected int getViewResId() {
        return R.layout.activity_edit_title;
    }

    @Override
    protected void init() {
        ToolBarUtil.initToolBar(toolbarText, "编辑标题", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneUtil.hideKeyboard(v);
                onBackPressed();
            }
        }, "完成", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etTitle.getText().toString())) {
                    ToastUtil.initToast(EditTitleActivity.this,"请编辑标题");
                }   else {
                    Intent intent = new Intent();
                    intent.putExtra("title",etTitle.getText().toString())  ;
                    setResult(RESULT_OK,intent);
                    onBackPressed();
                }
            }
        });
    }
}
