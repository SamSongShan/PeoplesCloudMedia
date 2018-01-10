package com.example.a11355.peoplescloudmedia.activity;

import android.content.Intent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.BaseActivity;
import com.example.a11355.peoplescloudmedia.model.GetRZTArticleListEntity;
import com.example.a11355.peoplescloudmedia.util.Constant;
import com.example.a11355.peoplescloudmedia.util.PreferencesUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class H5ActivityForRZTPreview extends BaseActivity {
    @BindView(R.id.pb_h5)
    public ProgressBar progressBar;
    @BindView(R.id.wv_h5)
    public WebView webView;
    @BindView(R.id.rv_botton)
    public RelativeLayout rvBotton;
    private GetRZTArticleListEntity.DataBean data;
    private int type;

    @Override
    protected int getViewResId() {
        return R.layout.activity_h5_for_rztpreview;
    }

    @Override
    protected void init() {
        Intent intent = getIntent();

        data = intent.getParcelableExtra("data");
        type = intent.getIntExtra("type", -1);
        if (type == 1) {//人众推  文章跳转
            rvBotton.setVisibility(View.GONE);
        }

        webView.requestFocusFromTouch();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                try {
                    if (newProgress >= 100) {
                        progressBar.setVisibility(View.GONE);
                    } else {
                        if (progressBar.getVisibility() != View.VISIBLE) {
                            progressBar.setVisibility(View.VISIBLE);
                        }
                        progressBar.setProgress(newProgress);
                    }
                } catch (Exception e) {
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(Constant.URL.RZTPreviewArticle + data.getArticleId());


    }


    @OnClick({R.id.img_colse, R.id.img_shear, R.id.img_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_colse:

                onBackPressed();
                break;
            case R.id.img_shear://分享
                PreferencesUtil.showShare(this, data.getTitle() + "", Constant.URL.RztEditArticleShare + data.getArticleId(), data.getTitle() + "", Constant.URL.BaseImg + data.getImageUrl(), null);
                break;
            case R.id.img_edit://编辑
                Intent intent = new Intent(this, H5ActivityArticleEdit.class);
                intent.putExtra("url", Constant.URL.RztEditArticle + data.getArticleId());
                startActivityForResult(intent, 12);

                break;
        }
    }
}
