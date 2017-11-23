package com.example.a11355.peoplescloudmedia.activity;

import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.BaseActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {


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

    private int tpye = 0;//选中的个人0 /企业1

    @Override
    protected int getViewResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {
        rg.setOnCheckedChangeListener(this);
    }

    @OnClick({R.id.iv_psw, R.id.btn_identify, R.id.tv_forgetPassword, R.id.tv_personal_register, R.id.btn_login, R.id.btn_register, R.id.btn_login_byWX})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_psw: {//查看密码
                ivPsw.setSelected(!ivPsw.isSelected());
                if (ivPsw.isSelected()) {
                    etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);//设置密码可见

                } else {
                    etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT );//设置密码不可见

                }
            }
            break;
            case R.id.btn_identify:    //发送验证码
                break;
            case R.id.tv_forgetPassword:   //忘记密码
                break;
            case R.id.tv_personal_register:   //注册
                break;
            case R.id.btn_login:   //登录
                break;
            case R.id.btn_register: //确认注册
                break;
            case R.id.btn_login_byWX:  //微信登录
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_personal:  //个人
                tpye = 0;

                break;
            case R.id.rb_business:   //企业
                tpye = 1;
                break;
        }
    }
}
