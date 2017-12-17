package com.example.a11355.peoplescloudmedia.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.BaseActivity;
import com.example.a11355.peoplescloudmedia.util.ToolBarUtil;

import butterknife.BindView;

/*
* 文章抓取
* */
public class PickUpArticleActivity extends BaseActivity {
    @BindView(R.id.toolbar_text)
    Toolbar toolbarText;

    @Override
    protected int getViewResId() {
        return R.layout.activity_pack_up_article;
    }

    @Override
    protected void init() {
        ToolBarUtil.initToolBar(toolbarText, "文章抓取", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
