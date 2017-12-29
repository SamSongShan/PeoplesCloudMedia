package com.example.a11355.peoplescloudmedia.fragement;


import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.activity.UpdateBusinessCardActivity;
import com.example.a11355.peoplescloudmedia.activity.UpdateCardCompanyInfoActivity;
import com.example.a11355.peoplescloudmedia.base.BaseFragment;
import com.example.a11355.peoplescloudmedia.custom.LoadingDialog;
import com.example.a11355.peoplescloudmedia.model.GetBusinessCardInfo;
import com.example.a11355.peoplescloudmedia.model.GetBusinessCardInfoEntity;
import com.example.a11355.peoplescloudmedia.util.Constant;
import com.example.a11355.peoplescloudmedia.util.DesUtil;
import com.example.a11355.peoplescloudmedia.util.LogUtils;
import com.example.a11355.peoplescloudmedia.util.OkHttpUtil;
import com.example.a11355.peoplescloudmedia.util.PreferencesUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * 自媒体制作  名片
 */
public class BusinessCardFragment extends BaseFragment implements OkHttpUtil.OnDataListener {


    @BindView(R.id.sdv_userHead)
    SimpleDraweeView sdvUserHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_Signature)
    TextView tvSignature;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_wechat)
    TextView tvWechat;
    @BindView(R.id.tv_QQ)
    TextView tvQQ;
    @BindView(R.id.rl_personalShow)
    RelativeLayout rlPersonalShow;
    @BindView(R.id.tv_addmsg)
    TextView tvAddmsg;
    @BindView(R.id.tv_addcompanymsg)
    TextView tvAddcompanymsg;
    @BindView(R.id.tv_companyName)
    TextView tvCompanyName;
    @BindView(R.id.tv_companyNet)
    TextView tvCompanyNet;
    @BindView(R.id.wv_h5)
    WebView wvH5;
    @BindView(R.id.ll_company_d)
    LinearLayout llCompanyD;
    @BindView(R.id.tv_addMusic)
    TextView tvAddMusic;
    Unbinder unbinder;
    private Gson gson = new GsonBuilder().create();
    private LoadingDialog loadingDialog;
    private GetBusinessCardInfoEntity getBusinessCardInfoEntity;


    @Override
    protected int getViewResId() {
        return R.layout.fragment_business_card;
    }

    @Override
    protected void init(View v) {

    }

    @Override
    protected void loadData() {
        loadingDialog = LoadingDialog.newInstance("内容加载中...");
        loadingDialog.show(getActivity().getFragmentManager());
        GetBusinessCardInfo getBusinessCardInfo = new GetBusinessCardInfo(PreferencesUtil.getToken(getContext()), PreferencesUtil.getUserId(getContext()));
        OkHttpUtil.postJson(Constant.URL.GetBusinessCardInfo, DesUtil.encrypt(gson.toJson(getBusinessCardInfo)), this);
    }

    @OnClick({R.id.rl_personalShow, R.id.tv_addmsg, R.id.tv_addcompanymsg, R.id.ll_company_d, R.id.tv_addMusic})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.rl_personalShow: {
                Intent intent = new Intent(getContext(), UpdateBusinessCardActivity.class);

                if (getBusinessCardInfoEntity!=null&&getBusinessCardInfoEntity.getData()!=null){
                    intent.putExtra("data",getBusinessCardInfoEntity.getData());

                }
                startActivityForResult(intent, Constant.Code.ZMTZZ_Personal);

            } break;
            case R.id.tv_addmsg: {
                Intent intent = new Intent(getContext(), UpdateBusinessCardActivity.class);

                if (getBusinessCardInfoEntity!=null&&getBusinessCardInfoEntity.getData()!=null){
                    intent.putExtra("data",getBusinessCardInfoEntity.getData());

                }
                startActivityForResult(intent, Constant.Code.ZMTZZ_Personal);
            }break;
            case R.id.tv_addcompanymsg: {
                Intent intent = new Intent(getContext(), UpdateCardCompanyInfoActivity.class);

                if (getBusinessCardInfoEntity!=null&&getBusinessCardInfoEntity.getData()!=null){
                    intent.putExtra("data",getBusinessCardInfoEntity.getData());

                }

                startActivityForResult(intent, Constant.Code.ZMTZZ_Company);
            }break;
            case R.id.ll_company_d: {
                Intent intent = new Intent(getContext(), UpdateCardCompanyInfoActivity.class);

                if (getBusinessCardInfoEntity!=null&&getBusinessCardInfoEntity.getData()!=null){
                    intent.putExtra("data",getBusinessCardInfoEntity.getData());

                }

                startActivityForResult(intent, Constant.Code.ZMTZZ_Company);            }break;
            case R.id.tv_addMusic: {
            } break;
        }
    }

    @Override
    public void onResponse(String url, String json) {

        String decrypt = DesUtil.decrypt(json);
        if (!TextUtils.isEmpty(json)) {
            switch (url) {
                case Constant.URL.GetBusinessCardInfo://用户编号获取用户名片信息
                    LogUtils.e("GetBusinessCardInfo", decrypt);
                    getBusinessCardInfoEntity = gson.fromJson(decrypt, GetBusinessCardInfoEntity.class);
                    if (getBusinessCardInfoEntity.getCode() == Constant.Integers.SUC) {
                        dismissLoading();
                        rlPersonalShow.setVisibility(View.VISIBLE);
                        tvAddmsg.setVisibility(View.GONE);
                        sdvUserHead.setImageURI(Constant.URL.BaseImg + getBusinessCardInfoEntity.getData().getHeadIcon());
                        tvName.setText(getBusinessCardInfoEntity.getData().getRealName() + "(" + getBusinessCardInfoEntity.getData().getPostName() + ")");
                        tvSignature.setText(getBusinessCardInfoEntity.getData().getSignature());
                        tvPhone.setText(getBusinessCardInfoEntity.getData().getMobile());
                        tvWechat.setText(getBusinessCardInfoEntity.getData().getWeChat());
                        tvQQ.setText(getBusinessCardInfoEntity.getData().getQQ());
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

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        loadData();    }
}


