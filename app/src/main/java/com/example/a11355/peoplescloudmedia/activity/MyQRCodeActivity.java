package com.example.a11355.peoplescloudmedia.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.BaseActivity;
import com.example.a11355.peoplescloudmedia.custom.CustomPopupWindow;
import com.example.a11355.peoplescloudmedia.model.GetEntityUserEntity;
import com.example.a11355.peoplescloudmedia.util.BitMapUtil;
import com.example.a11355.peoplescloudmedia.util.Constant;
import com.example.a11355.peoplescloudmedia.util.PhoneUtil;
import com.example.a11355.peoplescloudmedia.util.PreferencesUtil;
import com.example.a11355.peoplescloudmedia.util.ScreenshotUtils;
import com.example.a11355.peoplescloudmedia.util.ToastUtil;
import com.example.a11355.peoplescloudmedia.util.ToolBarUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;

import butterknife.BindView;

/*
* 我的二维码
* */
public class MyQRCodeActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.toolbar_icon)
    Toolbar toolbarIcon;
    @BindView(R.id.sdv_userHead)
    SimpleDraweeView sdvUserHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.iv_QRCode)
    ImageView ivQRCode;
    @BindView(R.id.scr)
    ScrollView scr;

    private CustomPopupWindow customPopupWindow;

    private String ShearLink;
    private GetEntityUserEntity.DataBean userInfo;


    @Override
    protected int getViewResId() {
        return R.layout.activity_my_qrcode;
    }

    @Override
    protected void init() {
        ToolBarUtil.initToolBar(toolbarIcon, "我的二维码", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        }, R.drawable.qr_code, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customPopupWindow = new CustomPopupWindow.Builder()
                        .setContentView(R.layout.pop_qr_code)
                        .setContext(MyQRCodeActivity.this)
                        .setheight(LinearLayout.LayoutParams.WRAP_CONTENT)
                        .setwidth(LinearLayout.LayoutParams.WRAP_CONTENT)
                        .builder()
                        .showAsLaction(findViewById(R.id.toolbar_iconNext), Gravity.BOTTOM, 0, 0);
                customPopupWindow.getItemView(R.id.tv_save).setOnClickListener(MyQRCodeActivity.this);
                customPopupWindow.getItemView(R.id.tv_shear).setOnClickListener(MyQRCodeActivity.this);

            }
        });

        ShearLink=Constant.URL.ShearLink;
        GetEntityUserEntity GetEntityUserEntity = PhoneUtil.getUserInfo(this);

        if (GetEntityUserEntity!=null){
            userInfo = GetEntityUserEntity.getData();
            sdvUserHead.setImageURI(PhoneUtil.getHead(userInfo.getHeadIcon()));
            tvName.setText(userInfo.getNickName());
            ShearLink= ShearLink+ userInfo.getMobile();

        }

        Bitmap qrCode = BitMapUtil.createQRImage(ShearLink, PhoneUtil.getPhoneWidth(this) / 2);
        ivQRCode.setImageBitmap(qrCode);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_save:      //保存
                customPopupWindow.dismiss();
                File file = ScreenshotUtils.shootScrollView(scr, "");
                if (file.exists()) {
                    saveSuccess(file);
                }
                break;
            case R.id.tv_shear://分享
                customPopupWindow.dismiss();

                share();
                break;
        }
    }

    //分享
    public void share() {
        String filePath = Environment.getExternalStorageDirectory() + "/Android/data/" +
                this.getPackageName() + "/cache/logo.jpg";
        if (!new File(filePath).exists()) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
            BitMapUtil.saveBitmap2File(bitmap, filePath);
        }
        PreferencesUtil.showShare(this, "我发现了一个很好用的人众云媒哦~", "点击链接",
                "美好生活从这里开始，快来人众云媒\n", filePath, this);
    }

    //保存后发送广播 弹出提示
    private void saveSuccess(File file) {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        this.sendBroadcast(intent);
        ToastUtil.initToast(this, "已保存");

    }
}
