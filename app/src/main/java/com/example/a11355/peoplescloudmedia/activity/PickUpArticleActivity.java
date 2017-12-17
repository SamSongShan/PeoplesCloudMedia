package com.example.a11355.peoplescloudmedia.activity;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.BaseActivity;
import com.example.a11355.peoplescloudmedia.custom.LoadingDialog;
import com.example.a11355.peoplescloudmedia.model.AddRZTArticle;
import com.example.a11355.peoplescloudmedia.util.Constant;
import com.example.a11355.peoplescloudmedia.util.DesUtil;
import com.example.a11355.peoplescloudmedia.util.LogUtils;
import com.example.a11355.peoplescloudmedia.util.OkHttpUtil;
import com.example.a11355.peoplescloudmedia.util.PreferencesUtil;
import com.example.a11355.peoplescloudmedia.util.ToastUtil;
import com.example.a11355.peoplescloudmedia.util.ToolBarUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import butterknife.BindView;
import butterknife.OnClick;

/*
* 文章抓取
* */
public class PickUpArticleActivity extends BaseActivity implements OkHttpUtil.OnDataListener {
    @BindView(R.id.toolbar_text)
    Toolbar toolbarText;
    @BindView(R.id.et_url)
    EditText etUrl;

    private Gson gson = new GsonBuilder().create();
    private LoadingDialog loadingDialog;

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

        toolbarText.setFocusableInTouchMode(true);
        toolbarText.setFocusable(true);
    }


    @OnClick({R.id.tv_commit, R.id.tv_clean})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_commit: {

                if (!TextUtils.isEmpty(etUrl.getText().toString())) {
                    loadingDialog = LoadingDialog.newInstance("提交中...");
                    loadingDialog.show(this.getFragmentManager());
                    OkHttpUtil.postJson(Constant.URL.AddRZTArticle, DesUtil.encrypt(gson.toJson(new AddRZTArticle(PreferencesUtil.getToken(this), PreferencesUtil.getUserId(this), etUrl.getText().toString().trim()))), this);
                } else {
                    ToastUtil.initToast(this, "请输入文章链接");
                }
            }
            break;
            case R.id.tv_clean:
                etUrl.setText("");
                break;
        }
    }

    @Override
    public void onResponse(String url, String json) {
        dismissLoading();
        if (!TextUtils.isEmpty(json)) {
            String decrypt = DesUtil.decrypt(json);
            LogUtils.e("AddRZTArticle",decrypt);
        } else {

        }
    }

    @Override
    public void onFailure(String url, String error) {

    }

    private void dismissLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }


    }
}
