package com.example.a11355.peoplescloudmedia.activity;

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
import com.example.a11355.peoplescloudmedia.util.Constant;
import com.example.a11355.peoplescloudmedia.util.ToolBarUtil;

import butterknife.BindView;


/**
 * H5页面 我的文章
 */
public class H5ActivityForGraphEdit extends BaseActivity {


    @BindView(R.id.pb_h5)
    public ProgressBar progressBar;
    @BindView(R.id.wv_h5)
    public WebView webView;
    @BindView(R.id.toolbar_text)
    Toolbar toolbarIcon;
    private String url;

    @Override
    protected int getViewResId() {
        return R.layout.activity_h5_graphedit;
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        url = intent.getStringExtra("url");
        boolean isshowedit = intent.getBooleanExtra("isshowedit", false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbarIcon.setElevation(0);
        }
        if (isshowedit) {
            ToolBarUtil.initToolBar(toolbarIcon, title, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            }, "编辑", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(H5ActivityForGraphEdit.this, HRichEditorViewActivity.class);
        intent.putExtra("GraphicEditorId", getIntent().getStringExtra("GraphicEditorId"));

        startActivityForResult(intent, Constant.Code.CollectCode);
                }
            });
        }else {
            ToolBarUtil.initToolBar(toolbarIcon, title, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            }, "", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
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
        webView.loadUrl(url);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==RESULT_OK){
            webView.loadUrl(url);

        }
    }
}
