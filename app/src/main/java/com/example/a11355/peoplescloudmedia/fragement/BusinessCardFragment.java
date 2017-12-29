package com.example.a11355.peoplescloudmedia.fragement;


import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.activity.GetMusicListActivity;
import com.example.a11355.peoplescloudmedia.activity.UpdateBusinessCardActivity;
import com.example.a11355.peoplescloudmedia.activity.UpdateCardCompanyInfoActivity;
import com.example.a11355.peoplescloudmedia.base.BaseFragment;
import com.example.a11355.peoplescloudmedia.custom.LoadingDialog;
import com.example.a11355.peoplescloudmedia.custom.UpdateCardOther;
import com.example.a11355.peoplescloudmedia.model.GetBusinessCardInfo;
import com.example.a11355.peoplescloudmedia.model.GetBusinessCardInfoEntity;
import com.example.a11355.peoplescloudmedia.model.UpdateCardCompanyInfo;
import com.example.a11355.peoplescloudmedia.model.UploadImgEntity;
import com.example.a11355.peoplescloudmedia.util.Constant;
import com.example.a11355.peoplescloudmedia.util.DesUtil;
import com.example.a11355.peoplescloudmedia.util.LogUtils;
import com.example.a11355.peoplescloudmedia.util.OkHttpUtil;
import com.example.a11355.peoplescloudmedia.util.PreferencesUtil;
import com.example.a11355.peoplescloudmedia.util.ToastUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;


/**
 * 自媒体制作  名片
 */
public class BusinessCardFragment extends BaseFragment implements OkHttpUtil.OnDataListener {


    @BindView(R.id.sdv_userHead)
    SimpleDraweeView sdvUserHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_Signature)
    TextView tvSignature;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_wechat)
    TextView tvWechat;
    @BindView(R.id.tv_QQ)
    TextView tvQQ;
    @BindView(R.id.rl_personalShow)
    RelativeLayout rlPersonalShow;
    @BindView(R.id.tv_addmsg)
    TextView tvAddmsg;
    @BindView(R.id.tv_addcompanymsg)
    TextView tvAddcompanymsg;
    @BindView(R.id.tv_companyName)
    TextView tvCompanyName;
    @BindView(R.id.tv_companyNet)
    TextView tvCompanyNet;
    @BindView(R.id.wv_h5)
    WebView webView;
    @BindView(R.id.ll_company_d)
    LinearLayout llCompanyD;
    @BindView(R.id.tv_addMusic)
    TextView tvAddMusic;
    Unbinder unbinder;
    private Gson gson = new GsonBuilder().create();
    private LoadingDialog loadingDialog;
    private GetBusinessCardInfoEntity getBusinessCardInfoEntity;


    @Override
    protected int getViewResId() {
        return R.layout.fragment_business_card;
    }

    @Override
    protected void init(View v) {

    }

    @Override
    protected void loadData() {
        loadingDialog = LoadingDialog.newInstance("内容加载中...");
        loadingDialog.show(getActivity().getFragmentManager());
        GetBusinessCardInfo getBusinessCardInfo = new GetBusinessCardInfo(PreferencesUtil.getToken(getContext()), PreferencesUtil.getUserId(getContext()));
        OkHttpUtil.postJson(Constant.URL.GetBusinessCardInfo, DesUtil.encrypt(gson.toJson(getBusinessCardInfo)), this);
    }

    private void initWebView() {
        webView.getSettings().setDatabaseEnabled(true);//开启数据库

        webView.setFocusable(true);//获取焦点

        webView.requestFocus();

        String dir = this.getContext().getDir("database", Context.MODE_PRIVATE).getPath();//设置数据库路径

        webView.getSettings().setCacheMode(webView.getSettings().LOAD_CACHE_ELSE_NETWORK);//本地缓存

        webView.getSettings().setBlockNetworkImage(false);//显示网络图像

        webView.getSettings().setLoadsImagesAutomatically(true);//显示网络图像

        webView.getSettings().setPluginState(WebSettings.PluginState.ON);//插件支持

        webView.getSettings().setSupportZoom(false);//设置是否支持变焦

        webView.getSettings().setJavaScriptEnabled(true);//支持JavaScriptEnabled

        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//支持JavaScriptEnabled

        webView.getSettings().setGeolocationEnabled(true);//定位

        webView.getSettings().setGeolocationDatabasePath(dir);//数据库

        webView.getSettings().setDomStorageEnabled(true);//缓存 （ 远程web数据的本地化存储）

        WebViewClient myWebViewClient = new WebViewClient();//建立对象

        webView.setWebViewClient(myWebViewClient);//调用

        webView.loadUrl("http://m.amap.com/navi");//百度地图地址

        webView.setWebChromeClient(new WebChromeClient() {

//重写WebChromeClient的onGeolocationPermissionsShowPrompt

            public void onGeolocationPermissionsShowPrompt(String origin,

                                                           GeolocationPermissions.Callback callback) {

                callback.invoke(origin, true, false);

                super.onGeolocationPermissionsShowPrompt(origin, callback);

            }

        });
    }

    @OnClick({R.id.rl_personalShow, R.id.tv_addmsg, R.id.tv_addcompanymsg, R.id.tv_addMusic, R.id.img_colseCompany})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.rl_personalShow: {
                Intent intent = new Intent(getContext(), UpdateBusinessCardActivity.class);

                if (getBusinessCardInfoEntity != null && getBusinessCardInfoEntity.getData() != null) {
                    intent.putExtra("data", getBusinessCardInfoEntity.getData());

                }
                startActivityForResult(intent, Constant.Code.ZMTZZ_Personal);

            }
            break;
            case R.id.tv_addmsg: {
                Intent intent = new Intent(getContext(), UpdateBusinessCardActivity.class);

                if (getBusinessCardInfoEntity != null && getBusinessCardInfoEntity.getData() != null) {
                    intent.putExtra("data", getBusinessCardInfoEntity.getData());

                }
                startActivityForResult(intent, Constant.Code.ZMTZZ_Personal);
            }
            break;
            case R.id.tv_addcompanymsg: {
                Intent intent = new Intent(getContext(), UpdateCardCompanyInfoActivity.class);

                if (getBusinessCardInfoEntity != null && getBusinessCardInfoEntity.getData() != null) {
                    intent.putExtra("data", getBusinessCardInfoEntity.getData());

                }

                startActivityForResult(intent, Constant.Code.ZMTZZ_Company);
            }
            break;
           /* case R.id.ll_company_d: {
                Intent intent = new Intent(getContext(), UpdateCardCompanyInfoActivity.class);

                if (getBusinessCardInfoEntity != null && getBusinessCardInfoEntity.getData() != null) {
                    intent.putExtra("data", getBusinessCardInfoEntity.getData());

                }

                startActivityForResult(intent, Constant.Code.ZMTZZ_Company);
            }
            break;*/
            case R.id.tv_addMusic: {
                startActivityForResult(new Intent(getContext(), GetMusicListActivity.class), Constant.Code.ZMTZZ_Music);
            }
            break;
            case R.id.img_colseCompany: {
                if (getBusinessCardInfoEntity != null) {

                    if (("1").equals(getBusinessCardInfoEntity.getData().getIsUseCompany())) {
                        loadingDialog = LoadingDialog.newInstance("提交中...");
                        loadingDialog.show(getActivity().getFragmentManager());
                        UpdateCardCompanyInfo updateCardCompanyInfo = new UpdateCardCompanyInfo(PreferencesUtil.getUserId(getContext()),
                                PreferencesUtil.getToken(getContext()),
                                "0", getBusinessCardInfoEntity.getData().getCompanyName(), getBusinessCardInfoEntity.getData().getCompanyNet(),
                                getBusinessCardInfoEntity.getData().getProvinceId() + "|" + getBusinessCardInfoEntity.getData().getCityId() + "|" + getBusinessCardInfoEntity.getData().getCountyId(),
                                getBusinessCardInfoEntity.getData().getProvinceName() + "|" + getBusinessCardInfoEntity.getData().getCityName() + "|" + getBusinessCardInfoEntity.getData().getCompanyName() + "|" + getBusinessCardInfoEntity.getData().getAddress() + "|" + getBusinessCardInfoEntity.getData().getLatitude() + "|" + getBusinessCardInfoEntity.getData().getPrecision());
                        String encrypt = DesUtil.encrypt(gson.toJson(updateCardCompanyInfo));
                        OkHttpUtil.postJson(Constant.URL.UpdateCardCompanyInfo, DesUtil.encrypt(gson.toJson(updateCardCompanyInfo)), this);
                    }
                }
            }
            break;
        }
    }

    @Override
    public void onResponse(String url, String json) {

        String decrypt = DesUtil.decrypt(json);
        if (!TextUtils.isEmpty(json)) {
            switch (url) {
                case Constant.URL.GetBusinessCardInfo://用户编号获取用户名片信息
                    LogUtils.e("GetBusinessCardInfo", decrypt);
                    getBusinessCardInfoEntity = gson.fromJson(decrypt, GetBusinessCardInfoEntity.class);
                    if (getBusinessCardInfoEntity.getCode() == Constant.Integers.SUC) {
                        dismissLoading();
                        rlPersonalShow.setVisibility(View.VISIBLE);
                        tvAddmsg.setVisibility(View.GONE);
                        sdvUserHead.setImageURI(Constant.URL.BaseImg + getBusinessCardInfoEntity.getData().getHeadIcon());
                        tvName.setText(getBusinessCardInfoEntity.getData().getRealName() + "(" + getBusinessCardInfoEntity.getData().getPostName() + ")");
                        tvSignature.setText(getBusinessCardInfoEntity.getData().getSignature());
                        tvPhone.setText(getBusinessCardInfoEntity.getData().getMobile());
                        tvWechat.setText(getBusinessCardInfoEntity.getData().getWeChat());
                        tvQQ.setText(getBusinessCardInfoEntity.getData().getQQ());

                        if (("1").equals(getBusinessCardInfoEntity.getData().getIsUseCompany())) {
                            tvAddcompanymsg.setVisibility(View.GONE);
                            llCompanyD.setVisibility(View.VISIBLE);
                            tvCompanyName.setText(getBusinessCardInfoEntity.getData().getCompanyName());
                            tvCompanyNet.setText(getBusinessCardInfoEntity.getData().getCompanyNet());
                            webView.loadUrl("http://m.amap.com/navi/?dest=" + getBusinessCardInfoEntity.getData().getLatitude() + "," + getBusinessCardInfoEntity.getData().getPrecision() + "&destName=" + getBusinessCardInfoEntity.getData().getProvinceName() + getBusinessCardInfoEntity.getData().getCityName() + getBusinessCardInfoEntity.getData().getCountyName() + getBusinessCardInfoEntity.getData().getAddress() + "&key=2a2e47461740f64d5eca896aaa41cc42");//百度地图地址

                        } else if (("0").equals(getBusinessCardInfoEntity.getData().getIsUseCompany())) {
                            tvAddcompanymsg.setVisibility(View.VISIBLE);
                            llCompanyD.setVisibility(View.GONE);
                        }
                        if (("1").equals(getBusinessCardInfoEntity.getData().getIsUseMusic())) {
                            tvAddMusic.setText("已添加");
                        }
                    }
                    break;
                case Constant.URL.UpdateCardCompanyInfo: {
                    dismissLoading();
                    LogUtils.e("UpdateCardCompanyInfo", decrypt);
                    UploadImgEntity img = new Gson().fromJson(decrypt, UploadImgEntity.class);

                    ToastUtil.initToast(getContext(), img.getMessage());

                    if (img.getCode() == Constant.Integers.SUC) {
                        getBusinessCardInfoEntity.getData().setIsUseCompany("0");
                        tvAddcompanymsg.setVisibility(View.VISIBLE);
                        llCompanyD.setVisibility(View.GONE);

                    } else {

                    }
                }
                break;
                case Constant.URL.UpdateCardOther:
                    LogUtils.e("UpdateCardOther", decrypt);
                    UploadImgEntity img = new Gson().fromJson(decrypt, UploadImgEntity.class);

                    ToastUtil.initToast(getContext(), img.getMessage());

                    if (img.getCode() == Constant.Integers.SUC) {
                        loadData();

                    } else {

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == Constant.Code.ZMTZZ_Music) {

                data.getStringExtra("singer");
                UpdateCardOther updateCardOther = new UpdateCardOther(PreferencesUtil.getUserId(getContext()), PreferencesUtil.getToken(getContext()), "MusicPath", "1111");
                OkHttpUtil.postJson(Constant.URL.UpdateCardOther, DesUtil.encrypt(gson.toJson(updateCardOther)), this);

            } else {
                loadData();
            }

        }

    }
}


