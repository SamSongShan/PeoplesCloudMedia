package com.example.a11355.peoplescloudmedia.activity;

import android.content.Intent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.BaseActivity;
import com.example.a11355.peoplescloudmedia.model.GetRZTArticleListEntity;
import com.example.a11355.peoplescloudmedia.util.Constant;

import butterknife.BindView;

public class H5ActivityForRZTPreview extends BaseActivity {
    @BindView(R.id.pb_h5)
    public ProgressBar progressBar;
    @BindView(R.id.wv_h5)
    public WebView webView;
    private GetRZTArticleListEntity.DataBean data;

    @Override
    protected int getViewResId() {
        return R.layout.activity_h5_for_rztpreview;
    }

    @Override
    protected void init() {
        Intent intent = getIntent();

        data = intent.getParcelableExtra("data");
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
}