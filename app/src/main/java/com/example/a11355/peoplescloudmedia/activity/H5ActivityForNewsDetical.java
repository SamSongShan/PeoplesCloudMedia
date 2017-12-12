package com.example.a11355.peoplescloudmedia.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.BaseActivity;
import com.example.a11355.peoplescloudmedia.custom.CustomPopupWindow;
import com.example.a11355.peoplescloudmedia.custom.LoadingDialog;
import com.example.a11355.peoplescloudmedia.model.AddFindCollect;
import com.example.a11355.peoplescloudmedia.model.AddFindCollectEntity;
import com.example.a11355.peoplescloudmedia.model.AddFindPV;
import com.example.a11355.peoplescloudmedia.model.GetFindNewsCollectListEntity;
import com.example.a11355.peoplescloudmedia.model.GetFindOutDetail;
import com.example.a11355.peoplescloudmedia.model.GetFindOutDetailEntity;
import com.example.a11355.peoplescloudmedia.model.GetNewsListEntity;
import com.example.a11355.peoplescloudmedia.util.BitMapUtil;
import com.example.a11355.peoplescloudmedia.util.Constant;
import com.example.a11355.peoplescloudmedia.util.DesUtil;
import com.example.a11355.peoplescloudmedia.util.LogUtils;
import com.example.a11355.peoplescloudmedia.util.OkHttpUtil;
import com.example.a11355.peoplescloudmedia.util.PhoneUtil;
import com.example.a11355.peoplescloudmedia.util.PreferencesUtil;
import com.example.a11355.peoplescloudmedia.util.ToastUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * H5页面
 */
public class H5ActivityForNewsDetical extends BaseActivity implements View.OnClickListener, OkHttpUtil.OnDataListener {

    @BindView(R.id.pb_h5)
    public ProgressBar progressBar;
    @BindView(R.id.wv_h5)
    public WebView webView;
    @BindView(R.id.sdv_from)
    SimpleDraweeView sdvFrom;
    @BindView(R.id.img_collect)
    ImageView imgCollect;
    @BindView(R.id.tv_reviewNum)
    TextView tvReviewNum;
    private GetNewsListEntity.DataBean.ArtilesBean data;
    private GetFindNewsCollectListEntity.DataBean.ArtilesBean data1;
    private String filePath;
    private String url;
    private String userId;

    private Gson gson = new GsonBuilder().create();
    private LoadingDialog loadingDialog;
    private TextView tvReviewNum1;
    private ImageView imgClose;
    private RecyclerView rv;
    private TextView tvPl;
    private LinearLayout llWrite;
    private EditText etPl;
    private TextView tvCommit;
    private CustomPopupWindow builder;

    @Override
    protected int getViewResId() {
        return R.layout.activity_h5_fornewsdetical;
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        url = intent.getStringExtra("url");

        data = intent.getParcelableExtra("data");
        data1 = intent.getParcelableExtra("data1");
        isCollect();
        initView();

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

    private void isCollect() {
        loadingDialog = LoadingDialog.newInstance("加载中...");
        loadingDialog.show(getFragmentManager());
        AddFindPV addFindPV = new AddFindPV(data == null ? data1.getId() + "" : data.getId() + "");
        OkHttpUtil.postJson(Constant.URL.AddFindPV, DesUtil.encrypt(gson.toJson(addFindPV)), this);
        if (isLogin()) {

            GetFindOutDetail getFindOutDetail = new GetFindOutDetail(data == null ? data1.getId() + "" : data.getId() + "", PreferencesUtil.getToken(this), PreferencesUtil.getUserId(this));

            OkHttpUtil.postJson(Constant.URL.GetFindOutDetail, DesUtil.encrypt(gson.toJson(getFindOutDetail)), this);
        }
    }

    private void initView() {


        sdvFrom.setImageURI(data == null ? data1.getAvatar() : data.getAvatar());

    }


    @OnClick({R.id.img_colse, R.id.sdv_from, R.id.img_shear, R.id.img_collect, R.id.img_review})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_colse:   //关闭

                onBackPressed();
                break;
            case R.id.sdv_from://点击头像
                break;
            case R.id.img_shear: //分享

                if (data == null) {
                    shareForcollect();
                } else {
                    share();
                }

                break;
            case R.id.img_collect:   //收藏

                if (isLogin()) {

                    AddFindCollect addFindCollect = new AddFindCollect(PreferencesUtil.getToken(this), PreferencesUtil.getUserId(this), data == null ? data1.getId() + "" : data.getId() + "");

                    if (imgCollect.isSelected()) {
                        loadingDialog = LoadingDialog.newInstance("取消收藏中...");
                        loadingDialog.show(this.getFragmentManager());
                        OkHttpUtil.postJson(Constant.URL.CancelFindCollect, DesUtil.encrypt(gson.toJson(addFindCollect)), this);

                    } else {
                        loadingDialog = LoadingDialog.newInstance("收藏中...");
                        loadingDialog.show(this.getFragmentManager());
                        OkHttpUtil.postJson(Constant.URL.AddFindCollect, DesUtil.encrypt(gson.toJson(addFindCollect)), this);

                    }

                } else {
                    ToastUtil.initToast(this, "未登录");
                }
                break;
            case R.id.img_review: //评论
                if (isLogin()) {

                     initPopu();
                } else {
                    ToastUtil.initToast(this, "未登录");

                }
                break;

        }

    }

    public boolean isLogin() {
        userId = PreferencesUtil.getUserId(this);
        if ("default".equals(userId)) {
            return false;
        } else {
            return true;
        }
    }

    //分享
    public void share() {
        if (TextUtils.isEmpty(data.getThumb())) {
            filePath = Environment.getExternalStorageDirectory() + "/Android/data/" +
                    getPackageName() + "/cache/logo.jpg";
            if (!new File(filePath).exists()) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
                BitMapUtil.saveBitmap2File(bitmap, filePath);
            } else {
                filePath = data.getThumb();
            }
        }


        PreferencesUtil.showShare(this, data.getTitle(), url,
                data.getAuthor(), filePath, this);
    }

    //分享
    public void shareForcollect() {
        if (TextUtils.isEmpty(data1.getThumb())) {
            filePath = Environment.getExternalStorageDirectory() + "/Android/data/" +
                    getPackageName() + "/cache/logo.jpg";
            if (!new File(filePath).exists()) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
                BitMapUtil.saveBitmap2File(bitmap, filePath);
            } else {
                filePath = data1.getThumb();
            }
        }


        PreferencesUtil.showShare(this, data1.getTitle(), url,
                data1.getAuthor(), filePath, this);
    }

    @Override
    public void onClick(View v) { //分享复制链接
        switch (v.getId()) {
            case R.id.img_close: { //关闭popu
                if (builder != null) {
                    builder.dismiss();

                    PhoneUtil.hideKeyboard(v);
                    tvPl.setVisibility(View.VISIBLE);
                    llWrite.setVisibility(View.GONE);

                }
            }
            break;
            case R.id.tv_commit: { //提交评论
                PhoneUtil.hideKeyboard(v);
            }
            break;
            case R.id.tv_pl: {   //显示评论输入框
                tvPl.setVisibility(View.GONE);
                llWrite.setVisibility(View.VISIBLE);
            }
            break;
        }
    }

    @Override
    public void onResponse(String url, String json) {
        dismissLoading();
        if (!TextUtils.isEmpty(json)) {
            String decrypt = DesUtil.decrypt(json);

            switch (url) {
                case Constant.URL.CancelFindCollect: { //取消收藏
                    LogUtils.e("CancelFindCollect", decrypt);

                    LogUtils.e("AddFindCollect", decrypt);
                    AddFindCollectEntity addFindCollectEntity = gson.fromJson(decrypt, AddFindCollectEntity.class);

                    ToastUtil.initToast(this, addFindCollectEntity.getMessage());
                    if (addFindCollectEntity.getCode() == Constant.Integers.SUC) {
                        imgCollect.setSelected(false);
                    } else {

                        if ("帐号已在其它地方登录".equals(addFindCollectEntity.getMessage())) {
                            startActivityForResult(new Intent(this, LoginActivity.class), Constant.Code.LoginCode);

                        }

                    }
                    break;

                }

                case Constant.URL.AddFindCollect: {//收藏
                    LogUtils.e("AddFindCollect", decrypt);
                    AddFindCollectEntity addFindCollectEntity = gson.fromJson(decrypt, AddFindCollectEntity.class);

                    ToastUtil.initToast(this, addFindCollectEntity.getMessage());
                    if (addFindCollectEntity.getCode() == Constant.Integers.SUC) {
                        imgCollect.setSelected(true);
                    } else {
                        if ("帐号已在其它地方登录".equals(addFindCollectEntity.getMessage())) {
                            startActivityForResult(new Intent(this, LoginActivity.class), Constant.Code.LoginCode);
                        }

                    }
                    break;
                }
                case Constant.URL.GetFindOutDetail: {  //是否收藏
                    LogUtils.e("GetFindOutDetail", decrypt);
                    GetFindOutDetailEntity getFindOutDetailEntity = gson.fromJson(decrypt, GetFindOutDetailEntity.class);

                    if (getFindOutDetailEntity.getCode() == Constant.Integers.SUC) {
                        imgCollect.setSelected(getFindOutDetailEntity.getData().isFavorited());
                    } else {
                        if ("帐号已在其它地方登录".equals(getFindOutDetailEntity.getMessage())) {
                            startActivityForResult(new Intent(this, LoginActivity.class), Constant.Code.LoginCode);
                        }

                    }
                    break;
                }
                case Constant.URL.AddFindPV: {
                    LogUtils.e("AddFindPV", decrypt);

                }
                break;


            }
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

    //初始化评论Popu
    private void initPopu() {

        builder = new CustomPopupWindow.Builder()
                .setContext(this)
                .setContentView(R.layout.popu_review)
                .setwidth(LinearLayout.LayoutParams.MATCH_PARENT)
                .setheight(LinearLayout.LayoutParams.MATCH_PARENT)
                .setOutSideCancel(false)
                .builder()
                .showAtLocation(R.layout.activity_h5_fornewsdetical, Gravity.BOTTOM, 0, 0);




        tvReviewNum1 = (TextView) builder.getItemView(R.id.tv_reviewNum);
        imgClose = (ImageView) builder.getItemView(R.id.img_close);
        rv = (RecyclerView) builder.getItemView(R.id.rv);
        tvPl = (TextView) builder.getItemView(R.id.tv_pl);
        llWrite = (LinearLayout) builder.getItemView(R.id.ll_write);
        etPl = (EditText) builder.getItemView(R.id.et_pl);
        tvCommit = (TextView) builder.getItemView(R.id.tv_commit);

        imgClose.setOnClickListener(this);
        tvCommit.setOnClickListener(this);
        tvPl.setOnClickListener(this);

        tvReviewNum1.setText(data == null ? data1.getComments_count() + "" : data.getComments_count() + "");
    }




}
