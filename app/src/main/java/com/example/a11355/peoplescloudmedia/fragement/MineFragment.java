package com.example.a11355.peoplescloudmedia.fragement;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.activity.AboutUsActivity;
import com.example.a11355.peoplescloudmedia.activity.LoginActivity;
import com.example.a11355.peoplescloudmedia.activity.MyCollectActivity;
import com.example.a11355.peoplescloudmedia.activity.MyFocusActivity;
import com.example.a11355.peoplescloudmedia.activity.MyPromotionActivity;
import com.example.a11355.peoplescloudmedia.activity.MyQRCodeActivity;
import com.example.a11355.peoplescloudmedia.activity.SettingActivity;
import com.example.a11355.peoplescloudmedia.adapter.MineRVAdapter;
import com.example.a11355.peoplescloudmedia.base.AbsRecyclerViewAdapter;
import com.example.a11355.peoplescloudmedia.base.BaseFragment;
import com.example.a11355.peoplescloudmedia.custom.GridDividerItemDecoration;
import com.example.a11355.peoplescloudmedia.custom.LoadingDialog;
import com.example.a11355.peoplescloudmedia.model.GetEntityUser;
import com.example.a11355.peoplescloudmedia.model.GetEntityUserEntity;
import com.example.a11355.peoplescloudmedia.util.BitMapUtil;
import com.example.a11355.peoplescloudmedia.util.Constant;
import com.example.a11355.peoplescloudmedia.util.DesUtil;
import com.example.a11355.peoplescloudmedia.util.LogUtils;
import com.example.a11355.peoplescloudmedia.util.OkHttpUtil;
import com.example.a11355.peoplescloudmedia.util.PhoneUtil;
import com.example.a11355.peoplescloudmedia.util.PreferencesUtil;
import com.example.a11355.peoplescloudmedia.util.ToastUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

import static android.app.Activity.RESULT_OK;


/**
 * 我的
 */
public class MineFragment extends BaseFragment implements View.OnClickListener, AbsRecyclerViewAdapter.OnItemClickListener, OkHttpUtil.OnDataListener, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.rv_store)
    RecyclerView rvStore;
    @BindView(R.id.srl_store)
    SwipeRefreshLayout srlStore;
    private View view;
    private SimpleDraweeView sdvUserHead;
    private TextView tvMyQrCode;
    private ImageView imgShear;
    private ImageView imgSetting;
    private TextView tvName;
    private TextView tvSignature;
    private TextView tvFocusNum;
    private TextView tvFansNum;
    private TextView tvCollectNum;
    private List<String> strings;

    private Gson gson = new GsonBuilder().create();
    private String userId;
    private String token;
    private LoadingDialog loadingDialog;
    private TextView tvCollect;
    private TextView tvFans;
    private TextView tvFocus;
    private String ShearLink;

    private boolean isloading=true;

    public MineFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getViewResId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void init(View v) {

        //mine头布局
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.head_mine, null, false);
        MineRVAdapter mineRVAdapter = new MineRVAdapter(getContext());
        mineRVAdapter.addHeaderView(view);
        rvStore.setLayoutManager(new LinearLayoutManager(getContext()));
        rvStore.addItemDecoration(new GridDividerItemDecoration(getContext(), 2));
        rvStore.setAdapter(mineRVAdapter);
        strings = Arrays.asList(Constant.Strings.mines);
        mineRVAdapter.setData(strings);
        mineRVAdapter.setOnItemClickListener(this);
        initHeadView(view);
        srlStore.setOnRefreshListener(this);

    }

    @Override
    protected void loadData() {
        isloading=true;
        loadingDialog = LoadingDialog.newInstance("加载中...");
        loadingDialog.show(getActivity().getFragmentManager());
        userId = PreferencesUtil.getUserId(getActivity());
        token = PreferencesUtil.getToken(getActivity());
        String jsonUser = gson.toJson(new GetEntityUser(token, userId));
        OkHttpUtil.postJson(Constant.URL.GetEntityUser, DesUtil.encrypt(jsonUser), this);
    }

    public void initHeadView(View view) {
        sdvUserHead = (SimpleDraweeView) view.findViewById(R.id.sdv_userHead);
        tvMyQrCode = (TextView) view.findViewById(R.id.tv_myQrCode);
        imgShear = (ImageView) view.findViewById(R.id.img_shear);
        imgSetting = (ImageView) view.findViewById(R.id.img_setting);
        tvName = (TextView) view.findViewById(R.id.tv_name);
        tvSignature = (TextView) view.findViewById(R.id.tv_signature);
        tvFocusNum = (TextView) view.findViewById(R.id.tv_FocusNum);
        tvFansNum = (TextView) view.findViewById(R.id.tv_fansNum);
        tvCollectNum = (TextView) view.findViewById(R.id.tv_collectNum);
        tvCollect = (TextView) view.findViewById(R.id.tv_collect);
        tvFans = (TextView) view.findViewById(R.id.tv_fans);
        tvFocus = (TextView) view.findViewById(R.id.tv_focus);


        sdvUserHead.setOnClickListener(this);
        tvMyQrCode.setOnClickListener(this);
        imgShear.setOnClickListener(this);
        imgSetting.setOnClickListener(this);

        tvFocusNum.setOnClickListener(this);
        tvFansNum.setOnClickListener(this);
        tvCollectNum.setOnClickListener(this);
        tvCollect.setOnClickListener(this);
        tvFans.setOnClickListener(this);
        tvFocus.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (isloading){
            ToastUtil.initToast(getContext(),"数据正在加载，请稍后");
            return;
        }
        switch (v.getId()) {
            case R.id.sdv_userHead: {//头像
                srlStore.setRefreshing(false);
            }
            break;
            case R.id.tv_myQrCode: {//我的二维码
                startActivity(new Intent(getContext(), MyQRCodeActivity.class));
            }
            break;
            case R.id.img_shear: {//分享
                share();
            }
            break;
            case R.id.img_setting: {//设置
                startActivityForResult(new Intent(getContext(), SettingActivity.class), Constant.Code.LoginCode);
            }

            break;
            case R.id.tv_FocusNum: // 关注
            case R.id.tv_focus: {
                Intent intent = new Intent(getContext(), MyFocusActivity.class);
                intent.putExtra("type", 0);
                startActivity(intent);
            }

            break;
            case R.id.tv_fansNum:  //  粉丝
            case R.id.tv_fans: {
                Intent intent = new Intent(getContext(), MyFocusActivity.class);
                intent.putExtra("type", 1);
                startActivity(intent);
            }


            break;
            case R.id.tv_collectNum: //收藏
            case R.id.tv_collect:
                startActivity(new Intent(getContext(), MyCollectActivity.class));

                break;
        }
    }

    //分享
    public void share() {
        String filePath = Environment.getExternalStorageDirectory() + "/Android/data/" +
                getContext().getPackageName() + "/cache/logo.jpg";
        if (!new File(filePath).exists()) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
            BitMapUtil.saveBitmap2File(bitmap, filePath);
        }
        PreferencesUtil.showShare(getContext(), "我发现了一个很好用的人众云媒哦~", ShearLink,
                "美好生活从这里开始，快来人众云媒\n", filePath, this);
    }

    @Override
    public void onItemClick(View v, int position) {
        if (isloading){
            ToastUtil.initToast(getContext(),"数据正在加载，请稍后");
            return;
        }
        switch (strings.get(position)) {
            case "我的文章": {
            }
            break;
            case "我的推广": {
                startActivity(new Intent(getContext(), MyPromotionActivity.class));
            }
            break;
            case "我的名片": {
            }
            break;
            case "关于我们": {

                startActivity(new Intent(getContext(), AboutUsActivity.class));
            }
            break;

        }
    }

    @Override
    public void onResponse(String url, String json) {
        if (!TextUtils.isEmpty(json)) {
            String decrypt = DesUtil.decrypt(json);
            switch (url) {

                case Constant.URL.GetEntityUser: { //个人信息
                    isloading=false;
                    LogUtils.e("loge", "GetEntityUser: " + decrypt);
                    GetEntityUserEntity getEntityUserEntity = gson.fromJson(decrypt, GetEntityUserEntity.class);

                    if (getEntityUserEntity.getCode() == Constant.Integers.SUC) { //成功
                        PreferencesUtil.saveUserInfo(getActivity(), DesUtil.encrypt(decrypt, DesUtil.LOCAL_KEY));
                        setHeadViewData(getEntityUserEntity.getData());
                        dismissLoading();

                    } else if (getEntityUserEntity.getCode() == Constant.Integers.TOKEN_OUT_OF) { //token过期
                        dismissLoading();
                        ToastUtil.initToast(getContext(), getEntityUserEntity.getMessage());
                        startActivityForResult(new Intent(getContext(), LoginActivity.class), Constant.Code.LoginCode);

                    } else {//其他
                        dismissLoading();
                        ToastUtil.initToast(getContext(), getEntityUserEntity.getMessage());
                    }

                }
                break;
            }
        }
    }

    private void setHeadViewData(GetEntityUserEntity.DataBean data) {
        sdvUserHead.setImageURI(PhoneUtil.getHead(data.getHeadIcon()));

        tvName.setText(data.getNickName());
        tvSignature.setText(TextUtils.isEmpty(data.getSignature()) ? "暂未设置个性签名" : data.getSignature());
        tvFocusNum.setText(data.getFocusNum() + "");
        tvFansNum.setText(data.getFansNum() + "");
        tvCollectNum.setText(data.getCollectNum() + "");
        ShearLink=Constant.URL.ShearLink+data.getMobile();


    }

    @Override
    public void onFailure(String url, String error) {

    }

    @Override
    public void onRefresh() {
        loadData();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constant.Code.LoginCode) {
            if (resultCode == RESULT_OK) {
                onRefresh();
            }


        }
    }

    private void dismissLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
        if (srlStore != null && srlStore.isRefreshing()) {
            srlStore.setRefreshing(false);
        }
    }
}
