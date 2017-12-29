package com.example.a11355.peoplescloudmedia.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.BaseActivity;
import com.example.a11355.peoplescloudmedia.base.BaseDialog;
import com.example.a11355.peoplescloudmedia.custom.LoadingDialog;
import com.example.a11355.peoplescloudmedia.custom.SectorProgressBar;
import com.example.a11355.peoplescloudmedia.custom.TipsAuthDialog;
import com.example.a11355.peoplescloudmedia.custom.UploadGenderDialog;
import com.example.a11355.peoplescloudmedia.custom.UploadImgDialog;
import com.example.a11355.peoplescloudmedia.model.GetEntityUserEntity;
import com.example.a11355.peoplescloudmedia.model.SingleWordEntity;
import com.example.a11355.peoplescloudmedia.model.UploadImgEntity;
import com.example.a11355.peoplescloudmedia.util.BitMapUtil;
import com.example.a11355.peoplescloudmedia.util.Constant;
import com.example.a11355.peoplescloudmedia.util.DesUtil;
import com.example.a11355.peoplescloudmedia.util.LogUtils;
import com.example.a11355.peoplescloudmedia.util.OkHttpUtil;
import com.example.a11355.peoplescloudmedia.util.PhoneUtil;
import com.example.a11355.peoplescloudmedia.util.PreferencesUtil;
import com.example.a11355.peoplescloudmedia.util.SharedPreferencesUtil;
import com.example.a11355.peoplescloudmedia.util.ToastUtil;
import com.example.a11355.peoplescloudmedia.util.ToolBarUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import butterknife.BindView;
import butterknife.OnClick;

/*
*    个人资料
*
* */
public class PersonalDataActivity extends BaseActivity implements BaseDialog.OnItemClickListener, OkHttpUtil.OnProgressMultiListener, OkHttpUtil.OnDataListener {


    @BindView(R.id.toolbar_text)
    Toolbar toolbarText;
    @BindView(R.id.sdv_userHead)
    SimpleDraweeView sdvUserHead;
    @BindView(R.id.tv_nickName)
    TextView tvNickName;
    @BindView(R.id.gender)
    TextView gender;
    @BindView(R.id.tv_signature)
    TextView tvSignature;
    @BindView(R.id.spb_head)
    SectorProgressBar progressBar;
    private TipsAuthDialog tipsDialog;
    private Handler handler = new Handler();
    private String encode = "";
    private LoadingDialog loadingDialog;
    private boolean isUpdating = false;
    private boolean isChangeHead = false;
    private String headUrl;

    private Gson gson = new GsonBuilder().create();

    private boolean needMineReflash = false; //是否需要mineFragement更新
    private boolean isChangeGender;
    private String genderForVisible;


    @Override
    protected int getViewResId() {
        return R.layout.activity_personal_data;
    }

    @Override
    protected void init() {

        ToolBarUtil.initToolBar(toolbarText, "个人资料", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (needMineReflash) {
                    setResult(RESULT_OK);
                }
                onBackPressed();
            }
        });

        GetEntityUserEntity getEntityUserEntity = PhoneUtil.getUserInfo(this);

        if (getEntityUserEntity != null) {
            GetEntityUserEntity.DataBean userInfo = getEntityUserEntity.getData();

            encode = userInfo.getEnCode();
            sdvUserHead.setImageURI(PhoneUtil.getHead(userInfo.getHeadIcon()));
            tvNickName.setText(userInfo.getNickName());
            gender.setText(TextUtils.isEmpty(userInfo.getGender()) ? "暂未设置" : userInfo.getGender());
            tvSignature.setText(TextUtils.isEmpty(userInfo.getSignature()) ? "暂未设置" : userInfo.getSignature());
        }


    }

    @OnClick({R.id.sdv_userHead, R.id.ll_nickName, R.id.ll_gender, R.id.ll_signature})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sdv_userHead: {   //   更换头像
                isChangeHead = true;
                isChangeGender = false;
                UploadImgDialog upImgDialog = UploadImgDialog.newInstance();
                upImgDialog.setOnItemClickListener(this);
                upImgDialog.show(getFragmentManager());
            }
            break;
            case R.id.ll_nickName: {    // 修改昵称

                if (isUpdating) {
                    ToastUtil.initToast(this, "正在上传头像，请稍后");
                    return;
                }
                isChangeHead = false;
                isChangeGender = false;
                Intent intent = new Intent(this, NikcAndSignatureActivity.class);
                intent.putExtra("type", 0);
                startActivityForResult(intent, Constant.Code.Nick);
            }
            break;
            case R.id.ll_gender: { //  修改性别
                if (isUpdating) {
                    ToastUtil.initToast(this, "正在上传头像，请稍后");
                    return;
                }
                isChangeHead = false;
                isChangeGender = true;
                UploadGenderDialog uploadGenderDialog = UploadGenderDialog.newInstance();
                uploadGenderDialog.setOnItemClickListener(this);
                uploadGenderDialog.show(getFragmentManager());
            }
            break;
            case R.id.ll_signature: {// 修个个性签名
                if (isUpdating) {
                    ToastUtil.initToast(this, "正在上传头像，请稍后");
                    return;
                }
                isChangeHead = false;
                isChangeGender = false;
                Intent intent = new Intent(this, NikcAndSignatureActivity.class);
                intent.putExtra("type", 1);
                startActivityForResult(intent, Constant.Code.Signature);
            }
            break;
        }
    }

    @Override
    public void onItemClick(View v) {
        switch (v.getId()) {
            case R.id.btn_openAlbum://打开相册
                //选择图库中的图片
                Intent intentAlbum = new Intent(Intent.ACTION_PICK);
                intentAlbum.setType("image/*");
                startActivityForResult(intentAlbum, Constant.Code.AlbumCode);
                break;
            case R.id.btn_openCamera://拍照
                try {
                    Intent intent = new Intent();
                    Intent intentCamera = getPackageManager().getLaunchIntentForPackage("com.android.camera");
                    if (intentCamera != null) {
                        intent.setPackage("com.android.camera");
                    }
                    intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, Constant.Code.CameraCode);
                } catch (SecurityException e) {
                    tipsDialog = TipsAuthDialog.newInstance(Constant.Strings.PermissionCameraTips);
                    tipsDialog.show(getFragmentManager(), "tips");
                }
                break;
            case R.id.tv_man: //男
                loadingDialog = LoadingDialog.newInstance("设置中...");
                loadingDialog.show(getFragmentManager());
                genderForVisible = "男";
                PreferencesUtil.submitUserInfo(this, "Gender", "男", this);

                break;
            case R.id.tv_woman://女
                loadingDialog = LoadingDialog.newInstance("设置中...");
                loadingDialog.show(getFragmentManager());
                genderForVisible = "女";
                PreferencesUtil.submitUserInfo(this, "Gender", "女", this);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constant.Code.AlbumCode://打开相册
                    Uri uriAlbum = data.getData();
                    Bitmap bitmapAlbum = BitMapUtil.decodeBitmap(BitMapUtil.Uri2Bitmap(this, uriAlbum));
                    decodeBm(bitmapAlbum);
                    break;
                case Constant.Code.CameraCode://拍照
                    try {
                        Bitmap bm = data.getParcelableExtra("data");
                        Bitmap bitmapCamera = BitMapUtil.decodeBitmap(bm);
                        decodeBm(bitmapCamera);
                    } catch (Exception e) {
                        tipsDialog = TipsAuthDialog.newInstance(Constant.Strings.PermissionFileTips);
                        tipsDialog.show(getFragmentManager(), "tips");
                    }
                    break;
                case Constant.Code.Nick: {//昵称

                    tvNickName.setText(data.getStringExtra("changeText"));
                    needMineReflash = true;

                }
                break;
                case Constant.Code.Signature: {//个性签名
                    tvSignature.setText(data.getStringExtra("changeText"));
                    needMineReflash = true;

                }
                break;


            }
        }
    }

    private void decodeBm(Bitmap bitmap) {
        if (bitmap == null) {
            ToastUtil.initToast(this, "图片错误");
        } else {
            if (progressBar != null) {
                progressBar.setVisibility(View.VISIBLE);
                progressBar.initProgress();

            }
            isUpdating = true;
            OkHttpUtil.postStream(Constant.URL.UploadImg, encode, 0, bitmap, this, this);
        }
    }


    @Override
    public void onProgressMulti(int index, int rate) {
        if (progressBar.getVisibility() != View.VISIBLE) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.initProgress();
                }
            });
        }
        if (progressBar != null) {
            progressBar.setProgress(rate);
        }
    }

    @Override
    public void onResponse(String url, String json) {
        if (!TextUtils.isEmpty(json)) {
            String decrypt = DesUtil.decrypt(json);
            switch (url) {
                case Constant.URL.UploadImg: {//上传头像

                    LogUtils.e("loge", "UploadImg: " + decrypt);
                    UploadImgEntity img = new Gson().fromJson(decrypt, UploadImgEntity.class);
                    if (img.getCode() == Constant.Integers.SUC) {
                        loadingDialog = LoadingDialog.newInstance("设置中...");
                        loadingDialog.show(getFragmentManager());
                        headUrl = img.getData();

                        PreferencesUtil.submitUserInfo(this, "HeadIcon", img.getData(), this);
                    } else {
                        ToastUtil.initToast(this, img.getMessage());
                        isUpdating = false;
                    }

                }
                break;

                case Constant.URL.UpdateUserEntity: {
                    LogUtils.e("UpdateUserEntity", "UpdateUserEntity: " + decrypt);

                    dismissLoading();
                    SingleWordEntity img = new Gson().fromJson(decrypt, SingleWordEntity.class);
                    ToastUtil.initToast(this, img.getMessage());
                    if (img.getCode() == Constant.Integers.SUC) {

                        if (isChangeHead) { //
                            isUpdating = false;
                            progressBar.setVisibility(View.GONE);
                            GetEntityUserEntity userInfo = PhoneUtil.getUserInfo(this);
                            userInfo.getData().setHeadIcon(headUrl);
                            SharedPreferencesUtil.saveUserInfo(this, DesUtil.encrypt(gson.toJson(userInfo), DesUtil.LOCAL_KEY));
                            sdvUserHead.setImageURI(PhoneUtil.getHead(headUrl));


                        } else if (isChangeGender) {
                            GetEntityUserEntity userInfo = PhoneUtil.getUserInfo(this);
                            userInfo.getData().setGender(genderForVisible);
                            SharedPreferencesUtil.saveUserInfo(this, DesUtil.encrypt(gson.toJson(userInfo), DesUtil.LOCAL_KEY));
                            gender.setText(genderForVisible);
                        }

                        needMineReflash = true;
                    }
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
        if (tipsDialog != null) {
            tipsDialog.dismiss();
        }
    }


}
