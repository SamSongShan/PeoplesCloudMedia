package com.example.a11355.peoplescloudmedia.activity;

import android.graphics.Bitmap;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.BaseActivity;
import com.example.a11355.peoplescloudmedia.custom.CustomPopupWindow;
import com.example.a11355.peoplescloudmedia.util.BitMapUtil;
import com.example.a11355.peoplescloudmedia.util.PhoneUtil;
import com.example.a11355.peoplescloudmedia.util.ToolBarUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;

/*
* 我的二维码
* */
public class MyQRCodeActivity extends BaseActivity {


    @BindView(R.id.toolbar_icon)
    Toolbar toolbarIcon;
    @BindView(R.id.sdv_userHead)
    SimpleDraweeView sdvUserHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.iv_QRCode)
    ImageView ivQRCode;


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
new CustomPopupWindow.Builder().builder();
            }
        });

  //





        Bitmap qrCode = BitMapUtil.createQRImage("这是个二维码字符串", PhoneUtil.getPhoneWidth(this) / 2);
        ivQRCode.setImageBitmap(qrCode );



    }
}
