package com.example.a11355.peoplescloudmedia.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.BaseActivity;
import com.example.a11355.peoplescloudmedia.util.ToastUtil;
import com.example.a11355.peoplescloudmedia.util.ToolBarUtil;

import butterknife.BindView;


/**
 * H5页面
 */
public class H5ActivityForCopy extends BaseActivity {


    @BindView(R.id.pb_h5)
    public ProgressBar progressBar;
    @BindView(R.id.wv_h5)
    public WebView webView;
    @BindView(R.id.toolbar_icon)
    Toolbar toolbarIcon;

    @Override
    protected int getViewResId() {
        return R.layout.activity_h5_for_copy;
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        final String url = intent.getStringExtra("url");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbarIcon.setElevation(0);
        }
        ToolBarUtil.initToolBar(toolbarIcon, title, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        }, R.drawable.copy, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager mClipboardManager =
                        (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData mClipData;
                String text = webView.getOriginalUrl();
                mClipData = ClipData.newPlainText("test", text);
                mClipboardManager.setPrimaryClip(mClipData);

                ToastUtil.initToast(H5ActivityForCopy.this, "复制成功");
            }
        });
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
        webView.loadUrl(url);
    }


}
