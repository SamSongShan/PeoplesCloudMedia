package com.example.a11355.peoplescloudmedia.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.BaseActivity;
import com.example.a11355.peoplescloudmedia.base.BaseDialog;
import com.example.a11355.peoplescloudmedia.custom.LoadingDialog;
import com.example.a11355.peoplescloudmedia.custom.SectorProgressBar;
import com.example.a11355.peoplescloudmedia.custom.TipsAuthDialog;
import com.example.a11355.peoplescloudmedia.custom.UploadImgDialog;
import com.example.a11355.peoplescloudmedia.model.GetEntityUserEntity;
import com.example.a11355.peoplescloudmedia.model.UploadImgEntity;
import com.example.a11355.peoplescloudmedia.util.BitMapUtil;
import com.example.a11355.peoplescloudmedia.util.Constant;
import com.example.a11355.peoplescloudmedia.util.LogUtils;
import com.example.a11355.peoplescloudmedia.util.OkHttpUtil;
import com.example.a11355.peoplescloudmedia.util.PhoneUtil;
import com.example.a11355.peoplescloudmedia.util.PreferencesUtil;
import com.example.a11355.peoplescloudmedia.util.ToastUtil;
import com.example.a11355.peoplescloudmedia.util.ToolBarUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.OnClick;


public class PickUpADActivity extends BaseActivity implements BaseDialog.OnItemClickListener, OkHttpUtil.OnDataListener, OkHttpUtil.OnProgressMultiListener {
    @BindView(R.id.toolbar_text)
    Toolbar toolbarText;
    @BindView(R.id.tv_addImg)
    TextView tvAddImg;
    @BindView(R.id.sdv)
    SimpleDraweeView sdv;
    @BindView(R.id.spb)
    SectorProgressBar progressBar;
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.et_link)
    EditText etLink;
    @BindView(R.id.et_content)
    EditText etContent;
    private boolean isUpdating;
    private String encode = "";
    private TipsAuthDialog tipsDialog;
    private Handler handler = new Handler();

    /*
   * 抓取广告
   *
   * */

    @Override
    protected int getViewResId() {
        return R.layout.activity_pick_up_ad;
    }

    @Override
    protected void init() {
        GetEntityUserEntity getEntityUserEntity = PhoneUtil.getUserInfo(this);

        if (getEntityUserEntity != null) {
            GetEntityUserEntity.DataBean userInfo = getEntityUserEntity.getData();

            encode = userInfo.getEnCode();

        }
        ToolBarUtil.initToolBar(toolbarText, "广告", new View.OnClickListener() {
            @Override
            public void onClick(View v) {//返回
                onBackPressed();
            }
        }, "完成", new View.OnClickListener() {
            @Override
            public void onClick(View v) {//提交

            }
        });
    }

    @OnClick({R.id.tv_addImg, R.id.sdv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_addImg://添加图片


                UploadImgDialog upImgDialog = UploadImgDialog.newInstance();
                upImgDialog.setOnItemClickListener(this);
                upImgDialog.show(getFragmentManager());
                break;
            case R.id.sdv:
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
            sdv.setImageBitmap(bitmap);

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
        }

    }

    @Override
    public void onFailure(String url, String error) {

    }

    private void dismissLoading() {

        if (tipsDialog != null) {
            tipsDialog.dismiss();
        }
    }
}
