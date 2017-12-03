package com.example.a11355.peoplescloudmedia.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.BaseActivity;
import com.example.a11355.peoplescloudmedia.custom.LoadingDialog;
import com.example.a11355.peoplescloudmedia.model.GetAppVersion;
import com.example.a11355.peoplescloudmedia.model.GetMobileCode;
import com.example.a11355.peoplescloudmedia.model.LoginRole;
import com.example.a11355.peoplescloudmedia.model.LoginRoleEntity;
import com.example.a11355.peoplescloudmedia.model.MobileCodeEntity;
import com.example.a11355.peoplescloudmedia.model.Registered;
import com.example.a11355.peoplescloudmedia.model.RegisteredEntity;
import com.example.a11355.peoplescloudmedia.util.Constant;
import com.example.a11355.peoplescloudmedia.util.DesUtil;
import com.example.a11355.peoplescloudmedia.util.LogUtils;
import com.example.a11355.peoplescloudmedia.util.OkHttpUtil;
import com.example.a11355.peoplescloudmedia.util.PhoneUtil;
import com.example.a11355.peoplescloudmedia.util.StatusBarUtils;
import com.example.a11355.peoplescloudmedia.util.ToastUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/*
* 登录
* */
public class LoginActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, OkHttpUtil.OnDataListener {


    @BindView(R.id.sdv)
    SimpleDraweeView sdv;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.et_loginName)
    EditText etLoginName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.iv_psw)
    ImageView ivPsw;
    @BindView(R.id.ll_login)
    LinearLayout llLogin;
    @BindView(R.id.et_regMobile)
    EditText etRegMobile;
    @BindView(R.id.et_regIdentify)
    EditText etRegIdentify;
    @BindView(R.id.et_regPassword)
    EditText etRegPassword;
    @BindView(R.id.sv_personal_register)
    ScrollView svPersonalRegister;
    @BindView(R.id.rv_forget)
    RelativeLayout rvForget;
    @BindView(R.id.sv_login)
    ScrollView svLogin;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.btn_identify)
    Button btnIdentify;


    private final int retryLimit = Constant.Integers.CodeRetryTime;//重试秒数上限
    private int timeNum = retryLimit;//获取验证码倒计时
    private long getCodeTime;//获取到验证码的时间
    private boolean isRegister;//是否正在注册
    private String correctCode;//正确的验证码
    private String mobile;//获取验证码的手机号
    private Gson gson = new GsonBuilder().create();
    private Handler handler = new Handler();


    private int type = 0;//选中的个人0 /企业1
    private LoadingDialog loadingDialog;

    @Override
    protected int getViewResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {
        StatusBarUtils.translucentStatusBar(this, true);
        rg.setOnCheckedChangeListener(this);
        rg.setFocusable(true);
        rg.setFocusableInTouchMode(true);
        //版本检查
        String jsonString = gson.toJson(new GetAppVersion("安卓"));
        OkHttpUtil.postJson(Constant.URL.GetAppVersion, DesUtil.encrypt(jsonString), this);
    }

    @OnClick({R.id.iv_psw, R.id.btn_identify, R.id.tv_forgetPassword, R.id.tv_personal_register, R.id.btn_login, R.id.btn_register, R.id.btn_login_byWX})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_psw: {//查看密码
                ivPsw.setSelected(!ivPsw.isSelected());
                if (ivPsw.isSelected()) {
                    etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);//设置密码可见

                } else {
                    etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);//设置密码不可见

                }
            }
            break;
            case R.id.btn_identify:    //发送验证码
                getCode();
                break;
            case R.id.tv_forgetPassword:   //忘记密码

                startActivity(new Intent(this, ForgotPasswordActivity.class));
                break;
            case R.id.tv_personal_register:   //注册
                if (type == 0) {//个人注册
                    llLogin.setVisibility(View.GONE);
                    btnLogin.setVisibility(View.GONE);
                    rvForget.setVisibility(View.GONE);
                    svPersonalRegister.setVisibility(View.VISIBLE);
                    btnRegister.setVisibility(View.VISIBLE);

                } else if (type == 1) { //企业注册
                    startActivityForResult(new Intent(this, RegisterForBusinessActivity.class), Constant.Code.RegisterForBusinessCode);
                }

                break;
            case R.id.btn_login: {   //登录
                if (etLoginName.getText().length() == 0) {
                    ToastUtil.initToast(this, "手机号码不能为空");
                } else if (!etLoginName.getText().toString().matches(Constant.Strings.RegexMobile)) {
                    ToastUtil.initToast(this, "请输入正确的手机号码");
                } else if (etPassword.getText().length() == 0) {
                    ToastUtil.initToast(this, "请输入密码");
                    etPassword.requestFocus();
                } else if (etPassword.getText().length() < 6) {//密码太短
                    ToastUtil.initToast(this, "请输入六位数密码");
                    etPassword.setText(null);
                } else {
                    PhoneUtil.hideKeyboard(view);
                    loadingDialog = LoadingDialog.newInstance("登陆中...");
                    loadingDialog.show(getFragmentManager());
                    String jsonString = gson.toJson(new LoginRole("Mobile", etLoginName.getText().toString(),
                            etPassword.getText().toString()));
                    String encrypt = DesUtil.encrypt(jsonString);
                    LogUtils.e("loge", "login: " + encrypt);
                    OkHttpUtil.postJson(Constant.URL.LoginRole, DesUtil.encrypt(jsonString), this);
                }

            }
            break;
            case R.id.btn_register: //确认注册

                if (isRegister) {
                    ToastUtil.initToast(this, "请不要重复操作");
                    break;
                } else if (TextUtils.isEmpty(etRegMobile.getText().toString()) && etRegMobile.getText().toString().length() == 0) {
                    ToastUtil.initToast(this, "手机号码不能为空");
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
                    etPassword.requestFocus();
                } else if (etRegPassword.getText().length() < 6) {//密码太短
                    ToastUtil.initToast(this, "请输入六位数密码");
                    etPassword.setHint("请输入六位数密码");
                    etPassword.setText(null);
                } else {//输入正确，注册
                    PhoneUtil.hideKeyboard(view);
                    isRegister = true;
                    loadingDialog = LoadingDialog.newInstance("注册中...");
                    loadingDialog.show(getFragmentManager());

                    String jsonString = gson.toJson(new Registered("Mobile", "Android", mobile, etRegPassword.getText().toString(), "default", "default", "default", "default", "0"));

                    LogUtils.e("loge", "注册json: " + jsonString);
                    OkHttpUtil.postJson(Constant.URL.Registered, DesUtil.encrypt(jsonString), this);

                }

                break;
            case R.id.btn_login_byWX:  //微信登录
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_personal:  //个人
                type = 0;
                llLogin.setVisibility(View.VISIBLE);
                btnLogin.setVisibility(View.VISIBLE);
                rvForget.setVisibility(View.VISIBLE);
                svPersonalRegister.setVisibility(View.GONE);
                btnRegister.setVisibility(View.GONE);
                //初始化所有数据
                checkInit();

                break;
            case R.id.rb_business:   //企业
                type = 1;
                llLogin.setVisibility(View.VISIBLE);
                btnLogin.setVisibility(View.VISIBLE);
                rvForget.setVisibility(View.VISIBLE);
                svPersonalRegister.setVisibility(View.GONE);
                btnRegister.setVisibility(View.GONE);
                //初始化所有数据
                checkInit();
                break;

        }
    }

    //初始化所有数据
    private void checkInit() {
        handler.removeCallbacksAndMessages(null);

        etLoginName.setText("");
        etPassword.setText("");
        etRegMobile.setText("");
        etRegIdentify.setText("");
        etRegPassword.setText("");

        btnIdentify.setText("发送验证码");
        timeNum = retryLimit;
        isRegister = false;
        correctCode = "";
        mobile = "";
        getCodeTime = 0;

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
                case Constant.URL.LoginRole:

                    LogUtils.e("LoginRole", "onResponse: " + decrypt);
                    LoginRoleEntity loginRole = new Gson().fromJson(decrypt, LoginRoleEntity.class);
                    ToastUtil.initToast(this, loginRole.getMessage(), Toast.LENGTH_LONG);
                    isRegister = false;
                    if (loginRole.getCode() == Constant.Integers.SUC) {
                        //登录成功
                        afterRegister(loginRole.getData().getUserId(), loginRole.getData().getToken());
                    } else {
                        dismissLoading();
                    }
                    break;
            }
        }

    }

    @Override
    public void onFailure(String url, String error) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        handler.removeCallbacksAndMessages(null);
    }

    /**
     * 注册之后
     */
    private void afterRegister(String userId, String token) {
        //用户信息加密后保存到本地
        SharedPreferences.Editor editor = getSharedPreferences("user", MODE_PRIVATE).edit();
        editor.putString("UserId", DesUtil.encrypt(userId, DesUtil.LOCAL_KEY));
        editor.putString("Token", DesUtil.encrypt(token, DesUtil.LOCAL_KEY));
        editor.putString("LoginName", DesUtil.encrypt(mobile, DesUtil.LOCAL_KEY));
        editor.putString("LoginPassword", DesUtil.encrypt(etPassword.getText().toString(), DesUtil.LOCAL_KEY));
        editor.putLong("LoginTime", System.currentTimeMillis());
        editor.commit();


        dismissLoading();
        handler.removeCallbacksAndMessages(null);
        setResult(RESULT_OK);
        finish();

    }

    private void dismissLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            setResult(RESULT_OK);
            finish();
        }
    }
}
