package com.example.a11355.peoplescloudmedia.activity;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.BaseActivity;
import com.example.a11355.peoplescloudmedia.base.BaseDialog;
import com.example.a11355.peoplescloudmedia.custom.ChooseAddressDialog;
import com.example.a11355.peoplescloudmedia.custom.LoadingDialog;
import com.example.a11355.peoplescloudmedia.model.AreaListEntity;
import com.example.a11355.peoplescloudmedia.model.GetBusinessCardInfoEntity;
import com.example.a11355.peoplescloudmedia.model.UpdateCardCompanyInfo;
import com.example.a11355.peoplescloudmedia.model.UploadImgEntity;
import com.example.a11355.peoplescloudmedia.util.CacheUtil;
import com.example.a11355.peoplescloudmedia.util.Constant;
import com.example.a11355.peoplescloudmedia.util.LogUtils;
import com.example.a11355.peoplescloudmedia.util.OkHttpUtil;
import com.example.a11355.peoplescloudmedia.util.PhoneUtil;
import com.example.a11355.peoplescloudmedia.util.PreferencesUtil;
import com.example.a11355.peoplescloudmedia.util.ToastUtil;
import com.example.a11355.peoplescloudmedia.util.ToolBarUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.luck.picture.lib.tools.DesUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

public class UpdateCardCompanyInfoActivity extends BaseActivity implements OkHttpUtil.OnDataListener, BaseDialog.OnItemClickListener {
    @BindView(R.id.toolbar_text)
    Toolbar toolbarText;
    @BindView(R.id.tv_companyName)
    EditText tvCompanyName;
    @BindView(R.id.tv_companyNet)
    EditText tvCompanyNet;
    @BindView(R.id.tv_addr)
    TextView tvAddr;
    @BindView(R.id.et_addr)
    EditText etAddr;
    @BindView(R.id.wv_h5)
    WebView webView;
    private LoadingDialog loadingDialog;
    private List<AreaListEntity.DataEntity> dataList;
    private String placeId;
    private String placeName;

    private Gson gson = new GsonBuilder().create();
    private GetBusinessCardInfoEntity.DataEntity data;

  /*
  * 自媒体制作  公司信息
  * */

    @Override
    protected int getViewResId() {
        return R.layout.activity_update_card_company_info;
    }

    @Override
    protected void init() {
        data = getIntent().getParcelableExtra("data");

        ititview();
        ToolBarUtil.initToolBar(toolbarText, "编辑", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        }, "完成", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(tvCompanyName.getText().toString().trim())) {
                    ToastUtil.initToast(UpdateCardCompanyInfoActivity.this, "请输入公司名称");
                } else if (TextUtils.isEmpty(tvCompanyNet.getText().toString().trim())) {
                    ToastUtil.initToast(UpdateCardCompanyInfoActivity.this, "请输入公司网址");

                } else if (TextUtils.isEmpty(placeId)) {
                    ToastUtil.initToast(UpdateCardCompanyInfoActivity.this, "请输入公司地址");

                } else if (TextUtils.isEmpty(etAddr.getText().toString().trim())) {
                    ToastUtil.initToast(UpdateCardCompanyInfoActivity.this, "请输入公司详细地址");

                } else {


                    PhoneUtil.hideKeyboard(v);
                    loadingDialog = LoadingDialog.newInstance("提交中...");
                    loadingDialog.show(getFragmentManager());
                    List<Address> locationList;
                    try {
                        Geocoder geocoder = new Geocoder(UpdateCardCompanyInfoActivity.this, Locale.CHINA);
                        String s = placeName;
                        String replace = s.replace("|", "");

                        locationList = geocoder.getFromLocationName(replace + etAddr.getText().toString().trim(), 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                        locationList = new ArrayList<>();
                    }

                    StringBuffer addressDetail = new StringBuffer(etAddr.getText().toString().trim() + "|");
                    if (locationList.size() > 0) {
                        addressDetail.append(locationList.get(0).getLongitude() + "|" + locationList.get(0).getLatitude());
                    } else {
                        //return;
                        addressDetail.append("default|default");
                    }

                    UpdateCardCompanyInfo updateCardCompanyInfo = new UpdateCardCompanyInfo(PreferencesUtil.getUserId(UpdateCardCompanyInfoActivity.this),
                            PreferencesUtil.getToken(UpdateCardCompanyInfoActivity.this),
                            "1", tvCompanyName.getText().toString().trim(), tvCompanyNet.getText().toString().trim(),
                            placeId, addressDetail.toString());
                    String encrypt = DesUtil.encrypt(gson.toJson(updateCardCompanyInfo));
                    OkHttpUtil.postJson(Constant.URL.UpdateCardCompanyInfo, DesUtil.encrypt(gson.toJson(updateCardCompanyInfo)), UpdateCardCompanyInfoActivity.this);
                }
            }
        });

        initWebView();
    }

    private void ititview() {
        if (data != null) {
            placeId = data.getProvinceId() + "|" + data.getCityId() + "|" + data.getCountyId();
            placeName = data.getProvinceName() + "|" + data.getCountyName() + "|" + data.getCountyName();
            tvCompanyName.setText(data.getCountyName());
            tvCompanyNet.setText(data.getCompanyNet());
            tvAddr.setText(data.getProvinceName() + " " + data.getCountyName() + " " + data.getCountyName());
            etAddr.setText(data.getAddress());
            webView.loadUrl("http://m.amap.com/navi/?dest="+data.getLatitude()+","+data.getPrecision()+"&destName="+data.getProvinceName()+data.getCityName()+data.getCountyName()+data.getAddress()+"&key=2a2e47461740f64d5eca896aaa41cc42");//百度地图地址

        }
    }

    private void initWebView() {
        webView.getSettings().setDatabaseEnabled(true);//开启数据库

        webView.setFocusable(true);//获取焦点

        webView.requestFocus();

        String dir = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();//设置数据库路径

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


    @OnClick(R.id.ll_addr)
    public void onViewClicked() {

        loadingDialog = LoadingDialog.newInstance("获取地址数据中...");
        loadingDialog.show(getFragmentManager());
        if (PhoneUtil.isUrlCacheValid(this, Constant.URL.GetAreaList)) {//本地数据可用
            chooseAddress();
        } else {
            CacheUtil.deleteUrlCache(Constant.URL.GetAreaList);
            OkHttpUtil.postJson(Constant.URL.GetAreaList, "0", this);
        }
//                }
    }

    private void chooseAddress() {
        dismissLoading();
        ChooseAddressDialog addressDialog = ChooseAddressDialog.newInstance();
        addressDialog.setOnItemClickListener(this);

        addressDialog.show(getFragmentManager());
    }

    private void dismissLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }

    }

    @Override
    public void onResponse(String url, String json) {

        if (!TextUtils.isEmpty(json)) {

            String decrypt = DesUtil.decrypt(json);
            switch (url) {
                case Constant.URL.GetAreaList: {//获取地址数据
                    dismissLoading();
                    AreaListEntity area = new Gson().fromJson(json, AreaListEntity.class);
                    if (area.getCode() == Constant.Integers.SUC) {
                        dataList = area.getData();

                        CacheUtil.setUrlCache(this, Constant.URL.GetAreaList, json);
                        chooseAddress();
                    } else {
                        ToastUtil.initToast(this, area.getMessage());
                    }
                }
                break;
                case Constant.URL.UpdateCardCompanyInfo: {
                    dismissLoading();
                    LogUtils.e("UpdateCardCompanyInfo", decrypt);

                    UploadImgEntity img = new Gson().fromJson(decrypt, UploadImgEntity.class);
                    if (img.getCode() == Constant.Integers.SUC) {

                        setResult(RESULT_OK);
                        finish();
                    } else {
                        ToastUtil.initToast(this, img.getMessage());

                    }
                }
                break;
            }

        }
    }

    @Override
    public void onFailure(String url, String error) {

    }

    @Override
    public void onItemClick(View v) {
        switch (v.getId()) {
            case R.id.tv_addressItem://选中地址
                placeId = (String) v.getTag();
                placeName = (String) v.getTag(R.id.tag_relation);
                tvAddr.setText(placeName);

                break;
        }
    }
}
