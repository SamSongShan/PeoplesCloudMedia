package com.example.a11355.peoplescloudmedia.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.BaseActivity;
import com.example.a11355.peoplescloudmedia.base.BaseDialog;
import com.example.a11355.peoplescloudmedia.custom.LoadingDialog;
import com.example.a11355.peoplescloudmedia.custom.SectorProgressBar;
import com.example.a11355.peoplescloudmedia.custom.TipsAuthDialog;
import com.example.a11355.peoplescloudmedia.custom.UploadImgDialog;
import com.example.a11355.peoplescloudmedia.model.GetBusinessCardInfoEntity;
import com.example.a11355.peoplescloudmedia.model.GetEntityUser;
import com.example.a11355.peoplescloudmedia.model.GetEntityUserEntity;
import com.example.a11355.peoplescloudmedia.model.UpdateBusinessCardEntity;
import com.example.a11355.peoplescloudmedia.model.UploadImgEntity;
import com.example.a11355.peoplescloudmedia.util.BitMapUtil;
import com.example.a11355.peoplescloudmedia.util.Constant;
import com.example.a11355.peoplescloudmedia.util.LogUtils;
import com.example.a11355.peoplescloudmedia.util.OkHttpUtil;
import com.example.a11355.peoplescloudmedia.util.PreferencesUtil;
import com.example.a11355.peoplescloudmedia.util.ToastUtil;
import com.example.a11355.peoplescloudmedia.util.ToolBarUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.luck.picture.lib.tools.DesUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class UpdateBusinessCardActivity extends BaseActivity implements BaseDialog.OnItemClickListener, OkHttpUtil.OnProgressMultiListener, OkHttpUtil.OnDataListener {
    @BindView(R.id.toolbar_text)
    Toolbar toolbarText;
    @BindView(R.id.sdv_userHead)
    SimpleDraweeView sdvUserHead;
    @BindView(R.id.spb_head)
    SectorProgressBar progressBar;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_zw)
    EditText etZw;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_wechat)
    EditText etWechat;
    @BindView(R.id.et_qq)
    EditText etQq;
    @BindView(R.id.et_Signature)
    EditText etSignature;
    private boolean isUpdating;


    private Gson gson = new GsonBuilder().create();
    private Bitmap bitmapAlbum;
    private LoadingDialog loadingDialog;
    private String headUrl;
    private TipsAuthDialog tipsDialog;

    private Handler handler = new Handler();
    private GetBusinessCardInfoEntity.DataEntity data;

    /*
    * 自媒体制作  名片
    * */


    @Override
    protected int getViewResId() {
        return R.layout.activity_update_business_card;
    }


    @OnClick(R.id.sdv_userHead)
    public void onViewClicked() {

        UploadImgDialog upImgDialog = UploadImgDialog.newInstance();
        upImgDialog.setOnItemClickListener(this);
        upImgDialog.show(getFragmentManager());
    }

    @Override
    protected void init() {
        sdvUserHead.setFocusable(true);
        sdvUserHead.setFocusableInTouchMode(true);

        data = getIntent().getParcelableExtra("data");
        initView();
        ToolBarUtil.initToolBar(toolbarText, "编辑", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        }, "完成", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUpdating) {
                    ToastUtil.initToast(UpdateBusinessCardActivity.this, "头像正在上传请稍候");
                } else if (TextUtils.isEmpty(headUrl)) {
                    ToastUtil.initToast(UpdateBusinessCardActivity.this, "请编辑头像");

                } else if (TextUtils.isEmpty(etName.getText().toString().trim())) {
                    ToastUtil.initToast(UpdateBusinessCardActivity.this, "请编辑姓名");

                } else if (TextUtils.isEmpty(etZw.getText().toString().trim())) {
                    ToastUtil.initToast(UpdateBusinessCardActivity.this, "请编辑职位");

                } else if (TextUtils.isEmpty(etPhone.getText().toString().trim())) {
                    ToastUtil.initToast(UpdateBusinessCardActivity.this, "请编辑手机号码");

                } else if (!etPhone.getText().toString().trim().matches(Constant.Strings.RegexMobile)) {
                    ToastUtil.initToast(UpdateBusinessCardActivity.this, "手机号码错误");

                } else if (TextUtils.isEmpty(etWechat.getText().toString().trim())) {
                    ToastUtil.initToast(UpdateBusinessCardActivity.this, "请编辑微信号码");

                } else if (TextUtils.isEmpty(etQq.getText().toString().trim())) {
                    ToastUtil.initToast(UpdateBusinessCardActivity.this, "请编辑QQ号码");

                } else if (TextUtils.isEmpty(etSignature.getText().toString().trim())) {
                    ToastUtil.initToast(UpdateBusinessCardActivity.this, "请编辑个性签名");

                } else {
                    UpdateBusinessCardEntity updateBusinessCardEntity = new UpdateBusinessCardEntity(PreferencesUtil.getUserId(UpdateBusinessCardActivity.this), PreferencesUtil.getToken(UpdateBusinessCardActivity.this), headUrl, etName.getText().toString().trim(), etZw.getText().toString().trim(),
                            etPhone.getText().toString().trim(), etWechat.getText().toString().trim(), etQq.getText().toString().trim(), etSignature.getText().toString().trim());

                    OkHttpUtil.postJson(Constant.URL.UpdateBusinessCardEntity, DesUtil.encrypt(gson.toJson(updateBusinessCardEntity)), UpdateBusinessCardActivity.this);
                }


            }
        });
    }

    private void initView() {
        if (data != null) {
            sdvUserHead.setImageURI(Constant.URL.BaseImg + data.getHeadIcon());
            etName.setText(data.getRealName());
            etZw.setText(data.getPostName());
            etPhone.setText(data.getMobile());
            etWechat.setText(data.getWeChat());
            etQq.setText(data.getQQ());
            etSignature.setText(data.getSignature());
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
                    TipsAuthDialog tipsDialog = TipsAuthDialog.newInstance(Constant.Strings.PermissionCameraTips);
                    tipsDialog.show(getFragmentManager(), "tips");
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constant.Code.AlbumCode://打开相册
                    Uri uriAlbum = data.getData();
                    bitmapAlbum = BitMapUtil.decodeBitmap(BitMapUtil.Uri2Bitmap(this, uriAlbum));
                    decodeBm(bitmapAlbum);
                    break;
                case Constant.Code.CameraCode://拍照
                    try {
                        Bitmap bm = data.getParcelableExtra("data");
                        bitmapAlbum = BitMapUtil.decodeBitmap(bm);
                        decodeBm(bitmapAlbum);
                    } catch (Exception e) {
                        tipsDialog = TipsAuthDialog.newInstance(Constant.Strings.PermissionFileTips);
                        tipsDialog.show(getFragmentManager(), "tips");
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

            if (PreferencesUtil.getUserInfo(this) != null) {
                OkHttpUtil.postStream(Constant.URL.UploadImg, PreferencesUtil.getUserInfo(this).getEnCode(), 0, bitmap, this, this);

            } else {
                String jsonUser = gson.toJson(new GetEntityUser(PreferencesUtil.getToken(this), PreferencesUtil.getUserId(this)));
                OkHttpUtil.postJson(Constant.URL.GetEntityUser, DesUtil.encrypt(jsonUser), this);
            }
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
                case Constant.URL.GetEntityUser: { //个人信息
                    LogUtils.e("loge", "GetEntityUser: " + decrypt);
                    GetEntityUserEntity getEntityUserEntity = gson.fromJson(decrypt, GetEntityUserEntity.class);

                    if (getEntityUserEntity.getCode() == Constant.Integers.SUC) { //成功
                        PreferencesUtil.saveUserInfo(this, DesUtil.encrypt(decrypt, DesUtil.LOCAL_KEY));
                        OkHttpUtil.postStream(Constant.URL.UploadImg, getEntityUserEntity.getData().getEnCode(), 0, bitmapAlbum, this, this);


                    } else if (getEntityUserEntity.getCode() == Constant.Integers.TOKEN_OUT_OF) { //token过期
                        ToastUtil.initToast(this, getEntityUserEntity.getMessage());
                        startActivityForResult(new Intent(this, LoginActivity.class), Constant.Code.LoginCode);

                    } else {//其他
                        ToastUtil.initToast(this, getEntityUserEntity.getMessage());
                    }

                }
                break;
                case Constant.URL.UploadImg: {//上传头像

                    LogUtils.e("loge", "UploadImg: " + decrypt);
                    UploadImgEntity img = new Gson().fromJson(decrypt, UploadImgEntity.class);
                    if (img.getCode() == Constant.Integers.SUC) {
                        isUpdating = false;
                        headUrl = img.getData();
                        sdvUserHead.setImageURI(Constant.URL.BaseImg + headUrl);
                    } else {
                        ToastUtil.initToast(this, img.getMessage());
                        isUpdating = false;
                    }

                }
                break;
                case Constant.URL.UpdateBusinessCardEntity:
                    LogUtils.e("loge", "UpdateBusinessCardEntity: " + decrypt);
                    UploadImgEntity UpdateBusinessCardEntity = new Gson().fromJson(decrypt, UploadImgEntity.class);
                    if (UpdateBusinessCardEntity.getCode() == Constant.Integers.SUC) {
                        headUrl = UpdateBusinessCardEntity.getData();
                        sdvUserHead.setImageURI(Constant.URL.BaseImg + headUrl);

                        finish();
                    } else {
                        ToastUtil.initToast(this, UpdateBusinessCardEntity.getMessage());
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
