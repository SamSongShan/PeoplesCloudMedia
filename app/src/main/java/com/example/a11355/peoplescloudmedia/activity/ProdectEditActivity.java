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
import android.widget.TextView;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.BaseActivity;
import com.example.a11355.peoplescloudmedia.base.BaseDialog;
import com.example.a11355.peoplescloudmedia.custom.SectorProgressBar;
import com.example.a11355.peoplescloudmedia.custom.TipsAuthDialog;
import com.example.a11355.peoplescloudmedia.custom.UploadImgDialog;
import com.example.a11355.peoplescloudmedia.model.GetEntityUser;
import com.example.a11355.peoplescloudmedia.model.GetEntityUserEntity;
import com.example.a11355.peoplescloudmedia.model.UploadImgEntity;
import com.example.a11355.peoplescloudmedia.util.BitMapUtil;
import com.example.a11355.peoplescloudmedia.util.Constant;
import com.example.a11355.peoplescloudmedia.util.DesUtil;
import com.example.a11355.peoplescloudmedia.util.LogUtils;
import com.example.a11355.peoplescloudmedia.util.OkHttpUtil;
import com.example.a11355.peoplescloudmedia.util.PhoneUtil;
import com.example.a11355.peoplescloudmedia.util.PreferencesUtil;
import com.example.a11355.peoplescloudmedia.util.ToastUtil;
import com.example.a11355.peoplescloudmedia.util.ToolBarUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import butterknife.BindView;
import butterknife.OnClick;

import static com.mob.MobSDK.getContext;

/*产品编辑 页面
*
* */
public class ProdectEditActivity extends BaseActivity implements BaseDialog.OnItemClickListener, OkHttpUtil.OnDataListener, OkHttpUtil.OnProgressMultiListener {


    @BindView(R.id.toolbar_text)
    Toolbar toolbarText;
    @BindView(R.id.tv_addImg)
    TextView tvAddImg;
    @BindView(R.id.sdv)
    SimpleDraweeView sdv;
    @BindView(R.id.spb)
    SectorProgressBar progressBar;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.et_description)
    EditText etDescription;
    private TipsAuthDialog tipsDialog;
    private boolean isUpdating;
    private Handler handler = new Handler();

    private Gson gson = new GsonBuilder().create();
    private GetEntityUserEntity getEntityUserEntity;
    private String ImgUrl;

    @Override
    protected int getViewResId() {
        return R.layout.activity_prodect_edit;
    }


    @OnClick({R.id.tv_addImg, R.id.sdv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_addImg:
            case R.id.sdv:
                if (getEntityUserEntity == null || getEntityUserEntity.getData() == null) {
                    ToastUtil.initToast(ProdectEditActivity.this, "数据正在加载");
                } else {
                    UploadImgDialog upImgDialog = UploadImgDialog.newInstance();
                    upImgDialog.setOnItemClickListener(this);
                    upImgDialog.show(getFragmentManager());
                }

                break;


        }
    }

    @Override
    protected void init() {
        sdv.setFocusable(true);
        sdv.setFocusableInTouchMode(true);
        String jsonUser = gson.toJson(new GetEntityUser(PreferencesUtil.getToken(this), PreferencesUtil.getUserId(this)));
        OkHttpUtil.postJson(Constant.URL.GetEntityUser, DesUtil.encrypt(jsonUser), this);
        ToolBarUtil.initToolBar(toolbarText, "编辑", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        }, "完成", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ImgUrl == null) {
                    ToastUtil.initToast(ProdectEditActivity.this, "请上传产品图片");
                } else if (TextUtils.isEmpty(etName.getText().toString().trim())) {
                    ToastUtil.initToast(ProdectEditActivity.this, "请填写产品名称");

                } else if (TextUtils.isEmpty(etPrice.getText().toString().trim())) {
                    ToastUtil.initToast(ProdectEditActivity.this, "请填写产品价格");

                } else if (TextUtils.isEmpty(etDescription.getText().toString().trim())) {
                    ToastUtil.initToast(ProdectEditActivity.this, "请填写产品描述");

                } else {

                    PhoneUtil.hideKeyboard(v);
                    Intent intent = new Intent();

                    intent.putExtra("imgUrl", ImgUrl);
                    intent.putExtra("name", etName.getText().toString().trim());
                    intent.putExtra("price", etPrice.getText().toString().trim());
                    intent.putExtra("description", etDescription.getText().toString().trim());

                    setResult(RESULT_OK, intent);
                    onBackPressed();

                }
            }
        });
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
            OkHttpUtil.postStream(Constant.URL.UploadImg, getEntityUserEntity.getData().getEnCode(), 0, bitmap, this, this);
        }
    }

    @Override
    public void onResponse(String url, String json) {
        if (!TextUtils.isEmpty(json)) {

            String decrypt = DesUtil.decrypt(json);
            switch (url) {

                case Constant.URL.GetEntityUser: { //个人信息
                    LogUtils.e("loge", "GetEntityUser: " + decrypt);
                    getEntityUserEntity = gson.fromJson(decrypt, GetEntityUserEntity.class);

                    if (getEntityUserEntity.getCode() == Constant.Integers.SUC) { //成功
                        PreferencesUtil.saveUserInfo(this, DesUtil.encrypt(decrypt, DesUtil.LOCAL_KEY));


                    } else if (getEntityUserEntity.getCode() == Constant.Integers.TOKEN_OUT_OF) { //token过期

                        ToastUtil.initToast(getContext(), getEntityUserEntity.getMessage());
                        startActivityForResult(new Intent(getContext(), LoginActivity.class), Constant.Code.LoginCode);

                    } else {//其他

                        ToastUtil.initToast(getContext(), getEntityUserEntity.getMessage());
                    }

                }
                break;
                case Constant.URL.UploadImg: {//上传头像

                    LogUtils.e("loge", "UploadImg: " + decrypt);
                    UploadImgEntity img = new Gson().fromJson(decrypt, UploadImgEntity.class);
                    if (img.getCode() == Constant.Integers.SUC) {

                        isUpdating = false;
                        sdv.setImageURI(Constant.URL.BaseImg + img.getData());
                        sdv.setVisibility(View.VISIBLE);
                        ImgUrl = img.getData();

                        progressBar.setVisibility(View.GONE);

                        PreferencesUtil.submitUserInfo(this, "HeadIcon", img.getData(), this);
                    } else {
                        ToastUtil.initToast(this, img.getMessage());

                    }

                }
                break;
            }
        }
    }

    @Override
    public void onFailure(String url, String error) {

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
}
