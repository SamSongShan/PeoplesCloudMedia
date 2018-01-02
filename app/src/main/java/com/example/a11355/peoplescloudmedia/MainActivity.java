package com.example.a11355.peoplescloudmedia;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a11355.peoplescloudmedia.activity.HRichEditorViewActivity;
import com.example.a11355.peoplescloudmedia.activity.LoginActivity;
import com.example.a11355.peoplescloudmedia.activity.RZTActivity;
import com.example.a11355.peoplescloudmedia.activity.ZMTZZActivity;
import com.example.a11355.peoplescloudmedia.base.BaseActivity;
import com.example.a11355.peoplescloudmedia.base.BaseDialog;
import com.example.a11355.peoplescloudmedia.custom.ConfirmDialog;
import com.example.a11355.peoplescloudmedia.custom.CustomPopupWindow;
import com.example.a11355.peoplescloudmedia.custom.DownloadDialog;
import com.example.a11355.peoplescloudmedia.custom.LoadingDialog;
import com.example.a11355.peoplescloudmedia.fragement.FindFragment;
import com.example.a11355.peoplescloudmedia.fragement.MessageFragment;
import com.example.a11355.peoplescloudmedia.fragement.MineFragment;
import com.example.a11355.peoplescloudmedia.fragement.VideoFragment;
import com.example.a11355.peoplescloudmedia.model.AppVersionEntity;
import com.example.a11355.peoplescloudmedia.model.GetAppVersion;
import com.example.a11355.peoplescloudmedia.model.GetAreaLastDateEntity;
import com.example.a11355.peoplescloudmedia.model.GetMobileCode;
import com.example.a11355.peoplescloudmedia.model.MobileCodeEntity;
import com.example.a11355.peoplescloudmedia.model.Registered;
import com.example.a11355.peoplescloudmedia.model.RegisteredEntity;
import com.example.a11355.peoplescloudmedia.util.Constant;
import com.example.a11355.peoplescloudmedia.util.DesUtil;
import com.example.a11355.peoplescloudmedia.util.LogUtils;
import com.example.a11355.peoplescloudmedia.util.OkHttpUtil;
import com.example.a11355.peoplescloudmedia.util.PhoneUtil;
import com.example.a11355.peoplescloudmedia.util.PreferencesUtil;
import com.example.a11355.peoplescloudmedia.util.ToastUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity implements View.OnClickListener, OkHttpUtil.OnProgressListener, RadioGroup.OnCheckedChangeListener, OkHttpUtil.OnDataListener, TextWatcher {
    @BindView(R.id.rg_mainTab)
    public RadioGroup radioGroup;


    private FragmentManager fragmentManager;
    private DownloadDialog downloadDialog;
    private String filePath;
    private boolean isForce = false;//是否强制升级
    private int page;//要启动的Fragment的页数

    private boolean isExit = false;//退出标识
    private String versionName;
    private String newAppLink;


    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;
    private FindFragment findFragment;
    private VideoFragment videoFragment;
    private MessageFragment messageFragment;
    private MineFragment mineFragment;
    private String userId;
    private Gson gson = new GsonBuilder().create();
    private CustomPopupWindow builder;
    private CustomPopupWindow builderPup;
    private EditText etRegMobile;
    private EditText etRegIdentify;
    private Button btnIdentify;
    private EditText etRegPassword;
    private Button btnCommit;

    private final int retryLimit = Constant.Integers.CodeRetryTime;//重试秒数上限
    private int timeNum = retryLimit;//获取验证码倒计时
    private long getCodeTime;//获取到验证码的时间
    private boolean isRegister;//是否正在注册
    private String correctCode;//正确的验证码
    private String mobile;//获取验证码的手机号
    private Handler handler = new Handler();
    private LoadingDialog loadingDialog;
    private String openId;
    private int type;


    @Override
    protected int getViewResId() {
        return R.layout.activity_main;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void init() {

        LogUtils.sHA1(this) ;
        //权限检查
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermissions();

        } else {
            checkVersion();
        }
        //行政区域修改时间
        OkHttpUtil.postJson(Constant.URL.GetAreaLastDate, "0", this);

        radioGroup.setOnCheckedChangeListener(this);
        radioGroup.getChildAt(0).performClick();


    }
    //版本检查

    private void checkVersion() {
        String jsonString = gson.toJson(new GetAppVersion("安卓"));
        OkHttpUtil.postJson(Constant.URL.GetAppVersion, DesUtil.encrypt(jsonString), this);
    }

    private void downLoadApp() {
        downloadDialog = DownloadDialog.newInstance(PhoneUtil.getAppName(this) + versionName, isForce);
        downloadDialog.show(getFragmentManager(), "download");
        filePath = Environment.getExternalStorageDirectory() + "/Download/" + PhoneUtil.getAppName(this) +
                "_" + versionName + ".apk";
        LogUtils.e("loge", "Download: " + filePath);
        OkHttpUtil.fileDownload(newAppLink, filePath, this, new OkHttpUtil.OnDataListener() {
            @Override
            public void onResponse(String url, String json) {//下载完成
                TextView btnInstall = downloadDialog.getBtnInstall();
                btnInstall.setSelected(true);
                btnInstall.setText("安装");
                btnInstall.setClickable(true);
                btnInstall.setOnClickListener(MainActivity.this);
                jumpInstall();
            }

            @Override
            public void onFailure(String url, String error) {
            }
        });
    }


    @Override
    public void onClick(View view) {
        if (builder != null) {
            builder.dismiss();
        }
        switch (view.getId()) {
            case R.id.img_zmt: {  //自媒体

                startActivity(new Intent(this, ZMTZZActivity.class));

            }
            break;
            case R.id.img_twbj: { //图文编辑
                startActivity(new Intent(this, HRichEditorViewActivity.class));

            }
            break;
            case R.id.img_rzt: {  //人众推
                startActivity(new Intent(this, RZTActivity.class));

            }
            break;
            case R.id.img_close: {   //关闭

            }
            break;

            case R.id.btn_identify: {//发送短信
                getCode();
            }
            break;
            case R.id.btn_commit: {//完成注册
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
                    PhoneUtil.hideKeyboard(view);
                    isRegister = true;
                    loadingDialog = LoadingDialog.newInstance("绑定中...");
                    loadingDialog.show(getFragmentManager());

                    String jsonString = gson.toJson(new Registered("WeChat", "Android", mobile, etRegPassword.getText().toString(), openId, "default", "default", "default", type+""));


                    OkHttpUtil.postJson(Constant.URL.Registered, DesUtil.encrypt(jsonString), this);

                }
            }
            break;
            default:
                jumpInstall();
                break;

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
                String jsonString = gson.toJson(new GetMobileCode(mobile, Constant.Strings.GetMobileCodeType[2]));
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

    @Override
    public void onBackPressed() {
        handler.removeCallbacksAndMessages(null);

        if (isExit) {
            System.exit(0);
        } else {
            ToastUtil.initToast(this, "再按一次退出" + PhoneUtil.getAppName(this));
            isExit = true;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 5000);//5秒内再按后退键真正退出
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkPermissions() {
        List<String> permissionsNeeded = new ArrayList<String>();

        final List<String> permissionsList = new ArrayList<String>();
        if (!addPermission(permissionsList, Manifest.permission.WRITE_EXTERNAL_STORAGE))//存储
            permissionsNeeded.add("存储");
        if (!addPermission(permissionsList, Manifest.permission.CAMERA))//相机
            permissionsNeeded.add("相机");
        if (!addPermission(permissionsList, Manifest.permission.RECORD_AUDIO))//麦克风
            permissionsNeeded.add("麦克风");
        if (!addPermission(permissionsList, Manifest.permission.ACCESS_FINE_LOCATION))//位置
            permissionsNeeded.add("位置");
        /*if (!addPermission(permissionsList, Manifest.permission.READ_CONTACTS))//联系人
            permissionsNeeded.add("联系人");
        if (!addPermission(permissionsList, Manifest.permission.CALL_PHONE))//手机
            permissionsNeeded.add("手机");
        if (!addPermission(permissionsList, Manifest.permission.SEND_SMS))//短信
            permissionsNeeded.add("短信");
        if (!addPermission(permissionsList, Manifest.permission.READ_CALENDAR))//日历
            permissionsNeeded.add("日历");
        if (!addPermission(permissionsList, Manifest.permission.BODY_SENSORS))//传感器
            permissionsNeeded.add("传感器");*/

        if (permissionsList.size() > 0) {
            if (permissionsNeeded.size() > 0) {
                // 需要去申请权限
                String message = "你需要申请 " + permissionsNeeded.get(0);
                for (int i = 1; i < permissionsNeeded.size(); i++) {
                    message = message + ", " + permissionsNeeded.get(i);

                }
                showMessageOKCancel(message + "等权限",
                        new DialogInterface.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.M)
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                                        REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
                            }
                        });
                return;
            }
            requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                    REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
            return;
        } else {
            checkVersion();
        }

        //可以操作
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean addPermission(List<String> permissionsList, String permission) {
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);
            // Check for Rationale Option
            if (!shouldShowRequestPermissionRationale(permission))
                return false;
        }
        return true;
    }
    //如果所有权限被授权，依然回调onRequestPermissionsResult，我用hashmap让代码整洁便于阅读。

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS: {
                Map<String, Integer> perms = new HashMap<String, Integer>();
                // 初始化所有权限
                perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);//存储
                perms.put(Manifest.permission.CAMERA, PackageManager.PERMISSION_GRANTED);//相机
                perms.put(Manifest.permission.RECORD_AUDIO, PackageManager.PERMISSION_GRANTED);//麦克风
                perms.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);//位置
                /*perms.put(Manifest.permission.READ_CONTACTS, PackageManager.PERMISSION_GRANTED);//联系人
                perms.put(Manifest.permission.CALL_PHONE, PackageManager.PERMISSION_GRANTED);//手机
                perms.put(Manifest.permission.SEND_SMS, PackageManager.PERMISSION_GRANTED);//短信
                perms.put(Manifest.permission.READ_CALENDAR, PackageManager.PERMISSION_GRANTED);//日历
                perms.put(Manifest.permission.BODY_SENSORS, PackageManager.PERMISSION_GRANTED);//传感器*/
                // Fill with results
                for (int i = 0; i < permissions.length; i++)
                    perms.put(permissions[i], grantResults[i]);
                // 授权反馈成功
                if (perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                        && perms.get(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                        && perms.get(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
                        &&perms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                       /* && perms.get(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED
                        && perms.get(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED
                        && perms.get(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED
                        && perms.get(Manifest.permission.READ_CALENDAR) == PackageManager.PERMISSION_GRANTED
                        && perms.get(Manifest.permission.BODY_SENSORS) == PackageManager.PERMISSION_GRANTED*/
                        ) {
                    // 授权成功
                    checkVersion();

                } else {
                    // 授权失败

                    Toast.makeText(MainActivity.this, "部分权限被拒绝，使用过程中可能会出现未知错误", Toast.LENGTH_SHORT)
                            .show();
                    checkVersion();
                }
            }
            break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("确定", okListener)
                .setNegativeButton("取消", null)
                .create()
                .show();
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //获取管理器
        if (fragmentManager == null) {
            fragmentManager = getSupportFragmentManager();

        }//获取事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //先隐藏全部
        hideFragments(transaction);
        switch (checkedId) {
            case R.id.rb_mainTab0://发现
                page = 0;
                if (findFragment == null) {
                    findFragment = new FindFragment();
                    transaction.add(R.id.fl_main, findFragment, "0");
                } else {
                    transaction.show(findFragment);
                    //findFragment.onRefresh();//强制进入刷新
                }
                break;
            case R.id.rb_mainTab1://视频
                page = 1;
                if (videoFragment == null) {
                    videoFragment = new VideoFragment();
                    transaction.add(R.id.fl_main, videoFragment, "1");
                } else {
                    transaction.show(videoFragment);
                    //videoFragment.onRefresh();//强制进入刷新
                }
                break;
            case R.id.rb_mainTab2://暂无展位用


                break;

            case R.id.rb_mainTab3://消息
                page = 3;


              /*  if (!isLogin()) {//当前未登录
                    startActivityForResult(new Intent(this, LoginActivity.class), Constant.Code.IntoCertifyCode);
                } else {*/
                if (messageFragment == null) {
                    messageFragment = new MessageFragment();
                    transaction.add(R.id.fl_main, messageFragment, "2");
                } else {
                    transaction.show(messageFragment);
                    //messageFragment.onRefresh();//强制进入刷新
                }
                //  }


                break;
            case R.id.rb_mainTab4://我的
                page = 4;
                if (!isLogin()) {//当前未登录
                    startActivityForResult(new Intent(this, LoginActivity.class), Constant.Code.IntoCertifyCode);
                } else {
                    if (mineFragment == null) {

                        mineFragment = new MineFragment();
                        transaction.add(R.id.fl_main, mineFragment, "3");
                    } else {
                        transaction.show(mineFragment);
                        mineFragment.onRefresh();//强制进入刷新
                    }
                }

                break;
        }
        transaction.commitAllowingStateLoss();
    }

    public boolean isLogin() {
        userId = PreferencesUtil.getUserId(this);
        if ("default".equals(userId)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 先隐藏所有Fragment
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (findFragment != null) {
            transaction.hide(findFragment);
        }
        if (videoFragment != null) {
            transaction.hide(videoFragment);
        }
        if (messageFragment != null) {
            transaction.hide(messageFragment);
        }
        if (mineFragment != null) {
            transaction.hide(mineFragment);
        }
    }


    @OnClick(R.id.img_add)//发布
    public void onViewClicked() {
        if (!isLogin()) {//当前未登录
            startActivityForResult(new Intent(this, LoginActivity.class), Constant.Code.IntoCertifyCode);
        } else {
            initPopu();
        }
    }


    //初始化发布Popu
    private void initPopu() {

        builder = new CustomPopupWindow.Builder()
                .setContext(this)
                .setContentView(R.layout.popu_publish)
                .setwidth(LinearLayout.LayoutParams.MATCH_PARENT)
                .setheight(LinearLayout.LayoutParams.WRAP_CONTENT)
                .setOutSideCancel(false)
                .builder()
                .showAtLocation(R.layout.activity_main, Gravity.BOTTOM, 0, 0);

        ImageView imgZmt = (ImageView) builder.getItemView(R.id.img_zmt);
        ImageView imgTwbj = (ImageView) builder.getItemView(R.id.img_twbj);
        ImageView imgRzt = (ImageView) builder.getItemView(R.id.img_rzt);
        ImageView imgClose = (ImageView) builder.getItemView(R.id.img_close);

        imgZmt.setOnClickListener(this);
        imgTwbj.setOnClickListener(this);
        imgRzt.setOnClickListener(this);
        imgClose.setOnClickListener(this);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.Code.IntoCertifyCode) {
            if (resultCode == RESULT_OK) {

                if (data != null && data.getIntExtra("page", -1) != -1) {
                    openId = data.getStringExtra("OpenId");
                    type = data.getIntExtra("type", 0);
                    radioGroup.getChildAt(0).performClick();

                    initRegestPopu();
                } else {
                    onCheckedChanged(radioGroup, radioGroup.getCheckedRadioButtonId());
                }
            } else {
                radioGroup.getChildAt(0).performClick();
            }
        }
    }

    private void initRegestPopu() {


        builderPup = new CustomPopupWindow.Builder()
                .setContext(this)
                .setContentView(R.layout.popu_regist_wx)
                .setwidth(LinearLayout.LayoutParams.MATCH_PARENT)
                .setheight(LinearLayout.LayoutParams.MATCH_PARENT)
                .setOutSideCancel(false)
                .builder()
                .showAtLocation(R.layout.activity_main, Gravity.TOP, 0, 0);

        etRegMobile = (EditText) builderPup.getItemView(R.id.et_regMobile);
        etRegIdentify = (EditText) builderPup.getItemView(R.id.et_regIdentify);
        btnIdentify = (Button) builderPup.getItemView(R.id.btn_identify);
        etRegPassword = (EditText) builderPup.getItemView(R.id.et_regPassword);
        btnCommit = (Button) builderPup.getItemView(R.id.btn_commit);

        btnIdentify.setOnClickListener(this);
        btnCommit.setOnClickListener(this);
        etRegMobile.addTextChangedListener(this);
    }

    @Override
    public void onResponse(String url, String json) {
        if (!TextUtils.isEmpty(json)) {
            String decrypt = DesUtil.decrypt(json);
            switch (url) {
                case Constant.URL.GetAppVersion: { //版本更新

                    LogUtils.e("GetAppVersion", "onResponse" + decrypt);
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
                        }
                    }
                }
                break;
                case Constant.URL.GetAreaLastDate:
                    LogUtils.e("GetAreaLastDate", "onResponse" + decrypt);

                    GetAreaLastDateEntity getAreaLastDateEntity = gson.fromJson(decrypt, GetAreaLastDateEntity.class);

                    if (getAreaLastDateEntity.getCode() == 200) {
                        SharedPreferences.Editor isFirst = getSharedPreferences("isFirst", MODE_PRIVATE).edit();
                        isFirst.putString("LastAddressModifyTime", getAreaLastDateEntity.getData().getLastDate());
                        isFirst.commit();
                    }
                    break;
                case Constant.URL.GetMobileCode: {
                    LogUtils.e("GetMobileCode", "onResponse: " + decrypt);
                    MobileCodeEntity mobileCode = new Gson().fromJson(decrypt, MobileCodeEntity.class);

                    if (mobileCode.getCode() == Constant.Integers.SUC) {
                        etRegIdentify.requestFocus();
                        getCodeTime = System.currentTimeMillis();
                        correctCode = mobileCode.getData().getCode();
                    ToastUtil.initToast(this, correctCode);
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
        handler.removeCallbacksAndMessages(null);
        radioGroup.getChildAt(4).performClick();


    }


    private void dismissLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }
    //门店手机号判断输入是否正确

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        PhoneUtil.isEditError2WithDel(s.toString(), etRegMobile, "");
        if (s.length() == 11 && s.toString().matches(Constant.Strings.RegexMobile)) {
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


    @Override
    public void afterTextChanged(Editable s) {

    }


}
