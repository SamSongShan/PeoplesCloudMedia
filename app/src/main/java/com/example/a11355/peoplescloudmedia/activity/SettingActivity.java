package com.example.a11355.peoplescloudmedia.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.a11355.peoplescloudmedia.MainActivity;
import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.BaseActivity;
import com.example.a11355.peoplescloudmedia.base.BaseDialog;
import com.example.a11355.peoplescloudmedia.custom.ConfirmDialog;
import com.example.a11355.peoplescloudmedia.custom.DownloadDialog;
import com.example.a11355.peoplescloudmedia.custom.LoadingDialog;
import com.example.a11355.peoplescloudmedia.model.AppVersionEntity;
import com.example.a11355.peoplescloudmedia.model.GetAppVersion;
import com.example.a11355.peoplescloudmedia.util.CacheUtil;
import com.example.a11355.peoplescloudmedia.util.ConfigConstants;
import com.example.a11355.peoplescloudmedia.util.Constant;
import com.example.a11355.peoplescloudmedia.util.DesUtil;
import com.example.a11355.peoplescloudmedia.util.OkHttpUtil;
import com.example.a11355.peoplescloudmedia.util.PhoneUtil;
import com.example.a11355.peoplescloudmedia.util.PreferencesUtil;
import com.example.a11355.peoplescloudmedia.util.ToastUtil;
import com.example.a11355.peoplescloudmedia.util.ToolBarUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.OnClick;

/*
* 设置
*
* */
public class SettingActivity extends BaseActivity implements OkHttpUtil.OnDataListener, View.OnClickListener, OkHttpUtil.OnProgressListener {

    @BindView(R.id.toolbar_text)
    Toolbar toolbarText;
    @BindView(R.id.tv_clearCache)
    TextView tvClearCache;
    @BindView(R.id.tv_version)
    TextView tvVersion;

    private DecimalFormat decimal = new DecimalFormat("0.0");
    private boolean needMineReflash = false; //是否需要mineFragement更新

    private Gson gson = new GsonBuilder().create();
    private boolean isForce = false;//是否强制升级
    private String versionName;
    private String newAppLink;
    private DownloadDialog downloadDialog;
    private String filePath;
    private Handler handler = new Handler();
    private LoadingDialog loadingDialog;


    @Override
    protected int getViewResId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void init() {

        ToolBarUtil.initToolBar(toolbarText, "设置", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (needMineReflash) {
                    setResult(RESULT_OK);
                }
                onBackPressed();
            }
        });
        updateCacheSize();
    }

    @OnClick({R.id.tv_personalData, R.id.ll_clearCache, R.id.ll_update, R.id.tv_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_personalData: {   //个人资料
                startActivityForResult(new Intent(this, PersonalDataActivity.class), Constant.Code.LoginCode);
            }
            break;
            case R.id.ll_clearCache: {  //清除缓存
                ConfirmDialog confirmDialog = ConfirmDialog.newInstance("确定清除缓存吗?", "取消", "确认");
                confirmDialog.setOnItemClickListener(new BaseDialog.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v) {
                        if (v.getId() == R.id.btn_confirmDialog) {
                            CacheUtil.clearCache();
                            ConfigConstants.clearCache();
                            tvClearCache.setText("0K");
                            ToastUtil.initToast(SettingActivity.this, "已经清理缓存");

                        }
                    }
                });
                confirmDialog.show(getFragmentManager());

            }
            break;
            case R.id.ll_update: {    //检查更新
                /*ConfirmDialog confirmDialog = ConfirmDialog.newInstance("系统即将升级到" + "这里填版本号", "取消", "确认");
                confirmDialog.setOnItemClickListener(new BaseDialog.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v) {
                        if (v.getId() == R.id.btn_confirmDialog) {
                            CacheUtil.clearCache();
                            ConfigConstants.clearCache();
                            ToastUtil.initToast(SettingActivity.this, "已经清理缓存");


                        }
                    }
                });
                confirmDialog.show(getFragmentManager());*/

                loadingDialog = LoadingDialog.newInstance("检查更新中...");
                loadingDialog.show(getFragmentManager());
                String jsonString = gson.toJson(new GetAppVersion("安卓"));
                OkHttpUtil.postJson(Constant.URL.GetAppVersion, DesUtil.encrypt(jsonString), this);
            }
            break;
            case R.id.tv_logout: { //退出登录
                ConfirmDialog confirmDialog = ConfirmDialog.newInstance("确认退出登录?", "取消", "确认");
                confirmDialog.setOnItemClickListener(new BaseDialog.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v) {
                        if (v.getId() == R.id.btn_confirmDialog) {
                            clearData();
                        }
                    }
                });
                confirmDialog.show(getFragmentManager());
            }
            break;
        }
    }

    /**
     * 更新缓存数量
     */
    private void updateCacheSize() {
        float cache = (CacheUtil.cacheSize() + ConfigConstants.cacheSize()) / 1024.0F;
        String cacheSize;
        if (cache < 1024) {
            if (cache <= 0) {
                cacheSize = "0K";
            } else {
                cacheSize = decimal.format(cache) + "K";
            }
        } else {
            cacheSize = decimal.format(cache / 1024) + "M";
        }
        tvClearCache.setText(cacheSize);
    }

    private void clearData() {
        PreferencesUtil.clearData(this);
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            needMineReflash = true;
        }

    }

    @Override
    public void onResponse(String url, String json) {
        if (!TextUtils.isEmpty(json)) {
            dismissLoading();
            String decrypt = DesUtil.decrypt(json);

            Log.e("GetAppVersion", "onResponse" + decrypt);
            AppVersionEntity version = new Gson().fromJson(decrypt, AppVersionEntity.class);
            if (version.getCode() == Constant.Integers.SUC) {
                isForce = false;
                versionName = version.getData().getItemName();
                String charStr = versionName.charAt(0) + "";
                String ver = versionName;
                if ("v".equals(charStr)) {
                    ver = versionName.substring(1, versionName.length());
                }
                if ("V".equals(charStr)) {
                    isForce = true;
                }

                int isNeedUpdate = PhoneUtil.isNeedUpdate(PhoneUtil.getAppVersion(this), ver);
                switch (isNeedUpdate) {
                    case 2://需要强制更新
                        isForce = true;
                    case 1://需要更新

                        newAppLink = version.getData().getItemValue();
                        String newContent = version.getData().getDescription() + "</br>(建议在wifi下更新)";
                        ConfirmDialog confirmDialog = ConfirmDialog.newInstance("系统即将升级到" + newContent, "取消", "确认");
                        confirmDialog.setOnItemClickListener(new BaseDialog.OnItemClickListener() {
                            @Override
                            public void onItemClick(View v) {
                                if (v.getId() == R.id.btn_confirmDialog) {
                                    downLoadApp();
                                }
                            }
                        });
                        confirmDialog.show(getFragmentManager());
                        break;
                    default:

                        ToastUtil.initToast(this, "已是最新版本");
                        break;
                }
            }
        }
    }

    @Override
    public void onFailure(String url, String error) {

    }

    private void downLoadApp() {
        downloadDialog = DownloadDialog.newInstance(PhoneUtil.getAppName(this) + versionName, isForce);
        downloadDialog.show(getFragmentManager(), "download");
        filePath = Environment.getExternalStorageDirectory() + "/Download/" + PhoneUtil.getAppName(this) +
                "_" + versionName + ".apk";
        Log.e("loge", "Download: " + filePath);
        OkHttpUtil.fileDownload(newAppLink, filePath, this, new OkHttpUtil.OnDataListener() {
            @Override
            public void onResponse(String url, String json) {//下载完成
                TextView btnInstall = downloadDialog.getBtnInstall();
                btnInstall.setSelected(true);
                btnInstall.setText("安装");
                btnInstall.setClickable(true);
                btnInstall.setOnClickListener(SettingActivity.this);
                jumpInstall();
            }

            @Override
            public void onFailure(String url, String error) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        jumpInstall();
    }

    /**
     * 跳转到安装页面
     */
    private void jumpInstall() {
        File apkFile = new File(filePath);
        if (apkFile.exists()) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");

            } else {

                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);//给目标应用一个临时的授权
                Uri uriForFile = FileProvider.getUriForFile(this, PhoneUtil.getAppProcessName(this) + ".FileProvider", apkFile);
                intent.setDataAndType(uriForFile, "application/vnd.android.package-archive");

            }
            startActivity(intent);
            android.os.Process.killProcess(android.os.Process.myPid());


        }
    }

    @Override
    public void onProgress(final int rate) {
        ProgressBar pb = downloadDialog.getProgressBar();
        final TextView btnInstall = downloadDialog.getBtnInstall();
        if (pb != null) {
            pb.setProgress(rate);
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (btnInstall != null) {
                    btnInstall.setText("下载中(" + rate + "%)");
                    if (rate == 100) {
                        btnInstall.setText("安装");
                    }
                }
            }
        });
    }
    private void dismissLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }
}
