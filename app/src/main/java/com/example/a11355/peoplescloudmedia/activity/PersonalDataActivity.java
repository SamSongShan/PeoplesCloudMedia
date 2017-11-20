package com.example.a11355.peoplescloudmedia.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.BaseActivity;
import com.example.a11355.peoplescloudmedia.base.BaseDialog;
import com.example.a11355.peoplescloudmedia.custom.SectorProgressBar;
import com.example.a11355.peoplescloudmedia.custom.TipsAuthDialog;
import com.example.a11355.peoplescloudmedia.custom.UploadGenderDialog;
import com.example.a11355.peoplescloudmedia.custom.UploadImgDialog;
import com.example.a11355.peoplescloudmedia.util.BitMapUtil;
import com.example.a11355.peoplescloudmedia.util.Constant;
import com.example.a11355.peoplescloudmedia.util.OkHttpUtil;
import com.example.a11355.peoplescloudmedia.util.ToastUtil;
import com.example.a11355.peoplescloudmedia.util.ToolBarUtil;
import com.facebook.drawee.view.SimpleDraweeView;

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


    @Override
    protected int getViewResId() {
        return R.layout.activity_personal_data;
    }

    @Override
    protected void init() {

        ToolBarUtil.initToolBar(toolbarText, "个人资料", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @OnClick({R.id.sdv_userHead, R.id.ll_nickName, R.id.ll_gender, R.id.ll_signature})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sdv_userHead: {   //   更换头像
                UploadImgDialog upImgDialog = UploadImgDialog.newInstance();
                upImgDialog.setOnItemClickListener(this);
                upImgDialog.show(getFragmentManager());
            }
            break;
            case R.id.ll_nickName: {    // 修改昵称
            }
            break;
            case R.id.ll_gender: { //  修改性别
                UploadGenderDialog uploadGenderDialog = UploadGenderDialog.newInstance();
                uploadGenderDialog.setOnItemClickListener(this);
                uploadGenderDialog.show(getFragmentManager());
            }
            break;
            case R.id.ll_signature: {// 修个个性签名
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
                    TipsAuthDialog tipsDialog = TipsAuthDialog.newInstance(Constant.Strings.PermissionCameraTips);
                    tipsDialog.show(getFragmentManager(), "tips");
                }
                break;
            case R.id.tv_man: //男


                break;
            case R.id.tv_woman://女


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

            OkHttpUtil.postStream("上传图像接口", "encode", 0, bitmap, this, this);
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

    }

    @Override
    public void onFailure(String url, String error) {

    }
}
