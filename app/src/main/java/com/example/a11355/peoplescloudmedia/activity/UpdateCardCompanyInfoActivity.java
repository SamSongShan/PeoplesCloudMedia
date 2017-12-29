package com.example.a11355.peoplescloudmedia.activity;

import android.content.Context;
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
import com.example.a11355.peoplescloudmedia.util.CacheUtil;
import com.example.a11355.peoplescloudmedia.util.Constant;
import com.example.a11355.peoplescloudmedia.util.OkHttpUtil;
import com.example.a11355.peoplescloudmedia.util.PhoneUtil;
import com.example.a11355.peoplescloudmedia.util.ToastUtil;
import com.example.a11355.peoplescloudmedia.util.ToolBarUtil;
import com.google.gson.Gson;
import com.luck.picture.lib.tools.DesUtil;

import java.util.List;

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

  /*
  * 自媒体制作  公司信息
  * */

    @Override
    protected int getViewResId() {
        return R.layout.activity_update_card_company_info;
    }

    @Override
    protected void init() {

        ToolBarUtil.initToolBar(toolbarText, "编辑", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        }, "完成", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        initWebView();
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

        webView.loadUrl("http://m.amap.com/navi/?dest=116.470098,39.992838&destName=%E9%98%9C%E9%80%9A%E8%A5%BF&hideRouteIcon=1&key=%E6%82%A8%E7%94%B3%E8%AF%B7%E7%9A%84Key");//百度地图地址

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

if (!TextUtils.isEmpty(json)){

    String decrypt = DesUtil.decrypt(json);
    switch (url){
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
               /* String placeId = (String) v.getTag();
                String placeName = (String) v.getTag(R.id.tag_relation);
                tvAddr.setText(placeName);
                checkNum--;*/
                break;
        }
    }
}
