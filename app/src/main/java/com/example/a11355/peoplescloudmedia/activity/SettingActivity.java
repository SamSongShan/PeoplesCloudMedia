package com.example.a11355.peoplescloudmedia.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.a11355.peoplescloudmedia.MainActivity;
import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.BaseActivity;
import com.example.a11355.peoplescloudmedia.base.BaseDialog;
import com.example.a11355.peoplescloudmedia.custom.ConfirmDialog;
import com.example.a11355.peoplescloudmedia.util.CacheUtil;
import com.example.a11355.peoplescloudmedia.util.ConfigConstants;
import com.example.a11355.peoplescloudmedia.util.PreferencesUtil;
import com.example.a11355.peoplescloudmedia.util.ToastUtil;
import com.example.a11355.peoplescloudmedia.util.ToolBarUtil;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.OnClick;

/*
* 设置
*
* */
public class SettingActivity extends BaseActivity {

    @BindView(R.id.toolbar_text)
    Toolbar toolbarText;
    @BindView(R.id.tv_clearCache)
    TextView tvClearCache;
    @BindView(R.id.tv_version)
    TextView tvVersion;

    private DecimalFormat decimal = new DecimalFormat("0.0");


    @Override
    protected int getViewResId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void init() {

        ToolBarUtil.initToolBar(toolbarText, "设置", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        updateCacheSize();
    }

    @OnClick({R.id.tv_personalData, R.id.ll_clearCache, R.id.ll_update, R.id.tv_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_personalData: {   //个人资料
                        startActivity(new Intent(this,PersonalDataActivity.class));
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
                            ToastUtil.initToast(SettingActivity.this, "已经清理缓存");
                        }
                    }
                });
                confirmDialog.show(getFragmentManager());

            }
            break;
            case R.id.ll_update: {    //检查更新
                ConfirmDialog confirmDialog = ConfirmDialog.newInstance("系统即将升级到" + "这里填版本号", "取消", "确认");
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
                confirmDialog.show(getFragmentManager());
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
}
