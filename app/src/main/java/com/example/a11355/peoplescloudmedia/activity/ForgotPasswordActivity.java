package com.example.a11355.peoplescloudmedia.activity;

import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.BaseActivity;
import com.example.a11355.peoplescloudmedia.custom.LoadingDialog;
import com.example.a11355.peoplescloudmedia.model.GetMobileCode;
import com.example.a11355.peoplescloudmedia.model.MobileCodeEntity;
import com.example.a11355.peoplescloudmedia.model.RegisteredEntity;
import com.example.a11355.peoplescloudmedia.util.Constant;
import com.example.a11355.peoplescloudmedia.util.DesUtil;
import com.example.a11355.peoplescloudmedia.util.LogUtils;
import com.example.a11355.peoplescloudmedia.util.OkHttpUtil;
import com.example.a11355.peoplescloudmedia.util.PhoneUtil;
import com.example.a11355.peoplescloudmedia.util.ToastUtil;
import com.example.a11355.peoplescloudmedia.util.ToolBarUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/*
* 忘记密码
* */

// TODO: 2017/11/25 接口没有 
public class ForgotPasswordActivity extends BaseActivity implements OkHttpUtil.OnDataListener {


    @BindView(R.id.toolbar_text)
    Toolbar toolbarText;
    @BindView(R.id.et_regMobile)
    EditText etRegMobile;
    @BindView(R.id.et_regIdentify)
    EditText etRegIdentify;
    @BindView(R.id.btn_identify)
    Button btnIdentify;
    @BindView(R.id.et_regPassword)
    EditText etRegPassword;

    private final int retryLimit = Constant.Integers.CodeRetryTime;//重试秒数上限
    private int timeNum = retryLimit;//获取验证码倒计时
    private long getCodeTime;//获取到验证码的时间
    private boolean isRegister;//是否正在注册
    private String correctCode;//正确的验证码
    private String mobile;//获取验证码的手机号
    private Gson gson = new GsonBuilder().create();
    private Handler handler = new Handler();

    private LoadingDialog loadingDialog;

    @Override
    protected int getViewResId() {
        return R.layout.activity_forgot_password;
    }


    @Override
    protected void init() {
        ToolBarUtil.initToolBar(toolbarText, "忘记密码", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacksAndMessages(null);
                onBackPressed();
            }
        });
        toolbarText.setFocusable(true);
        toolbarText.setFocusableInTouchMode(true);
    }

    @OnClick({R.id.btn_identify, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_identify: //发送验证码
                getCode();
                break;
            case R.id.btn_register:   //修改

                if (isRegister) {
                    ToastUtil.initToast(this, "请不要重复操作");
                    break;
                } else if (TextUtils.isEmpty(etRegMobile.getText().toString()) && etRegMobile.getText().toString().length() == 0) {
                    ToastUtil.initToast(this, "企业/个人手机号码不能为空");
                } else if (TextUtils.isEmpty(correctCode)) {
                    ToastUtil.initToast(this, "请获取验证码");
                } else if (!etRegMobile.getText().toString().equals(mobile)) {
                    ToastUtil.initToast(this, "请填写获取验证码的手机号 或重新获取验证码");
                } else if (etRegIdentify.getText().length() == 0) {
                    ToastUtil.initToast(this, "请输入验证码");
                } else if (System.currentTimeMillis() - getCodeTime >= (retryLimit + 10) * 1000) {//验证码失效
                    ToastUtil.initToast(this, "验证码已失效 请重新获取");
                } else if (!etRegIdentify.getText().toString().equals(correctCode)) {//验证码输入错误
                    ToastUtil.initToast(this, "验证码错误");
                } else if (etRegPassword.getText().length() == 0) {
                    ToastUtil.initToast(this, "请输入密码");
                    etRegPassword.requestFocus();
                } else if (etRegPassword.getText().length() < 6) {//密码太短
                    ToastUtil.initToast(this, "请输入六位数密码");
                    etRegPassword.setText(null);
                } else {//输入正确，注册
                    /*PhoneUtil.hideKeyboard(view);
                    isRegister = true;
                    loadingDialog = LoadingDialog.newInstance("密码找回中...");
                    loadingDialog.show(getFragmentManager());

                    String jsonString = gson.toJson(new Registered("Mobile", "Android", mobile, etRegPassword.getText().toString(), "default", etBusinessName.getText().toString(), etBossName.getText().toString(), "default", "1"));

                    LogUtils.e("loge", "注册json: " + jsonString);
                    OkHttpUtil.postJson(Constant.URL.Registered, DesUtil.encrypt(jsonString), this);*/

                }

                break;
        }
    }

    //门店手机号判断输入是否正确
    @OnTextChanged(value = R.id.et_regMobile, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void etStoreMobileChanged(CharSequence text) {
        PhoneUtil.isEditError2WithDel(text.toString(), etRegMobile, "");
        if (text.length() == 11 && text.toString().matches(Constant.Strings.RegexMobile)) {
            btnIdentify.setSelected(true);
            btnIdentify.setClickable(true);
        } else {
            btnIdentify.setClickable(false);
            btnIdentify.setSelected(false);

            btnIdentify.setText("发送验证码");
            handler.removeCallbacksAndMessages(null);
            timeNum = retryLimit;
            isRegister = false;
            correctCode = "";
            mobile = "";
            getCodeTime = 0;

        }
    }

    //获取验证码
    private void getCode() {
        if (btnIdentify.isSelected()) {
            mobile = etRegMobile.getText().toString();
            if (mobile.length() == 0) {
                ToastUtil.initToast(this, "手机号码不能为空");
            } else if (!mobile.matches(Constant.Strings.RegexMobile)) {
                ToastUtil.initToast(this, "请输入正确的手机号码");
            } else {
                btnIdentify.setSelected(false);
                String jsonString = gson.toJson(new GetMobileCode(mobile, Constant.Strings.GetMobileCodeType[0]));
                OkHttpUtil.postJson(Constant.URL.GetMobileCode, DesUtil.encrypt(jsonString), this);
                //设置XX秒后重试


                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (btnIdentify != null) {
                            btnIdentify.setText("点击重新发送 (" + timeNum + ")");
                            if (timeNum > 0) {
                                handler.postDelayed(this, 1000);
                                timeNum--;
                            } else {
                                btnIdentify.setText("发送验证码");
                                timeNum = retryLimit;
                                if (etRegMobile.getText().length() == 11) {
                                    btnIdentify.setSelected(true);
                                }
                            }
                        }
                    }
                });

            }
        }
    }

    @Override
    public void onResponse(String url, String json) {
        if (!TextUtils.isEmpty(json)) {
            String decrypt = DesUtil.decrypt(json);
            switch (url) {
                case Constant.URL.GetMobileCode: {
                    LogUtils.e("GetMobileCode", "onResponse: " + decrypt);
                    MobileCodeEntity mobileCode = new Gson().fromJson(decrypt, MobileCodeEntity.class);

                    if (mobileCode.getCode() == Constant.Integers.SUC) {
                        etRegIdentify.requestFocus();
                        getCodeTime = System.currentTimeMillis();
                        correctCode = mobileCode.getData().getCode();
//                    ToastUtil.initToast(this, correctCode);
                    } else {
                        ToastUtil.initToast(this, mobileCode.getMessage());

                    }

                }
                break;
                case Constant.URL.Registered:

                    LogUtils.e("Registered", "onResponse: " + decrypt);
                    RegisteredEntity register = new Gson().fromJson(decrypt, RegisteredEntity.class);
                    ToastUtil.initToast(this, register.getMessage(), Toast.LENGTH_LONG);
                    isRegister = false;
                    if (register.getCode() == Constant.Integers.SUC) {
                        //注册成功后跳过登录
                        afterRegister(register.getData().getUserId(), register.getData().getToken());
                    } else {
                        handler.removeCallbacksAndMessages(null);
                        dismissLoading();
                    }
                    break;
            }
        }
    }

    @Override
    public void onFailure(String url, String error) {

    }

    /**
     * 修改之后
     */
    private void afterRegister(String userId, String token) {
        //用户信息加密后保存到本地
        SharedPreferences.Editor editor = getSharedPreferences("user", MODE_PRIVATE).edit();
        editor.putString("UserId", DesUtil.encrypt(userId, DesUtil.LOCAL_KEY));
        editor.putString("Token", DesUtil.encrypt(token, DesUtil.LOCAL_KEY));
        editor.putString("LoginName", DesUtil.encrypt(mobile, DesUtil.LOCAL_KEY));
        editor.putString("LoginPassword", DesUtil.encrypt(etRegPassword.getText().toString(), DesUtil.LOCAL_KEY));
        editor.putLong("LoginTime", System.currentTimeMillis());
        editor.commit();


        dismissLoading();
        setResult(RESULT_OK);
        handler.removeCallbacksAndMessages(null);
        finish();

    }

    private void dismissLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }


}
