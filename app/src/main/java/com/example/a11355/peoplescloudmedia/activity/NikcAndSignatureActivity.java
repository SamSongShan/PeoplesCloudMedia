package com.example.a11355.peoplescloudmedia.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.BaseActivity;
import com.example.a11355.peoplescloudmedia.custom.LoadingDialog;
import com.example.a11355.peoplescloudmedia.model.GetEntityUserEntity;
import com.example.a11355.peoplescloudmedia.model.SingleWordEntity;
import com.example.a11355.peoplescloudmedia.util.Constant;
import com.example.a11355.peoplescloudmedia.util.DesUtil;
import com.example.a11355.peoplescloudmedia.util.LogUtils;
import com.example.a11355.peoplescloudmedia.util.OkHttpUtil;
import com.example.a11355.peoplescloudmedia.util.PhoneUtil;
import com.example.a11355.peoplescloudmedia.util.PreferencesUtil;
import com.example.a11355.peoplescloudmedia.util.SharedPreferencesUtil;
import com.example.a11355.peoplescloudmedia.util.ToastUtil;
import com.example.a11355.peoplescloudmedia.util.ToolBarUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import butterknife.BindView;

/*
* 修改昵称  个性签名   个人资料
* */
public class NikcAndSignatureActivity extends BaseActivity implements OkHttpUtil.OnDataListener {


    @BindView(R.id.et)
    EditText et;
    @BindView(R.id.toolbar_text)
    Toolbar toolbarText;
    private int type; //0为修改昵称   1为修个个性签名
    private LoadingDialog loadingDialog;

    private Gson gson = new GsonBuilder().create();

    @Override
    protected int getViewResId() {
        return R.layout.activity_nikc_and_signature;
    }

    @Override
    protected void init() {
        ToolBarUtil.initToolBar(toolbarText, "个人资料", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        }, "确定", new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(et.getText())) {
                    loadingDialog = LoadingDialog.newInstance("设置中...");
                    loadingDialog.show(getFragmentManager());
                    if (type == 0) {

                        PreferencesUtil.submitUserInfo(NikcAndSignatureActivity.this, "NickName", et.getText().toString(), NikcAndSignatureActivity.this);
                    } else {
                        PreferencesUtil.submitUserInfo(NikcAndSignatureActivity.this, "Signature", et.getText().toString(), NikcAndSignatureActivity.this);

                    }
                } else {
                    ToastUtil.initToast(NikcAndSignatureActivity.this, "修改数据不能为空");
                }
            }
        });

        type = getIntent().getIntExtra("type", 0);
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
            LogUtils.e("UpdateUserEntity", "UpdateUserEntity: " + decrypt);
            dismissLoading();
            SingleWordEntity img = new Gson().fromJson(decrypt, SingleWordEntity.class);
            ToastUtil.initToast(this, img.getMessage());
            if (img.getCode() == Constant.Integers.SUC) {
                GetEntityUserEntity userInfo = PhoneUtil.getUserInfo(this);
                if (type == 0) {
                    userInfo.getData().setNickName(et.getText().toString());

                } else {
                    userInfo.getData().setSignature(et.getText().toString());

                }
                SharedPreferencesUtil.saveUserInfo(this, DesUtil.encrypt(gson.toJson(userInfo), DesUtil.LOCAL_KEY));
                Intent intent = new Intent();
                intent.putExtra("changeText", et.getText().toString());
                setResult(RESULT_OK, intent);
                onBackPressed();
            }
        }
    }

    @Override
    public void onFailure(String url, String error) {

    }
}
