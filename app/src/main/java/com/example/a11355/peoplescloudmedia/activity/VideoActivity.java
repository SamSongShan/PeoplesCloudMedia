package com.example.a11355.peoplescloudmedia.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.adapter.GetUserEvaluationeListAdapter;
import com.example.a11355.peoplescloudmedia.base.BaseActivity;
import com.example.a11355.peoplescloudmedia.base.OnAdapterCallbackListener;
import com.example.a11355.peoplescloudmedia.custom.CustomPopupWindow;
import com.example.a11355.peoplescloudmedia.custom.DividerGridItem;
import com.example.a11355.peoplescloudmedia.custom.LoadingDialog;
import com.example.a11355.peoplescloudmedia.model.AddFindCollectEntity;
import com.example.a11355.peoplescloudmedia.model.AddFindCommentEntity;
import com.example.a11355.peoplescloudmedia.model.AddUserCollect;
import com.example.a11355.peoplescloudmedia.model.AddUserEvaluation;
import com.example.a11355.peoplescloudmedia.model.AddVideoPV;
import com.example.a11355.peoplescloudmedia.model.AddVideoSharingPV;
import com.example.a11355.peoplescloudmedia.model.CancelUserCollect;
import com.example.a11355.peoplescloudmedia.model.GetUserEvaluationeList;
import com.example.a11355.peoplescloudmedia.model.GetUserEvaluationeListEntity;
import com.example.a11355.peoplescloudmedia.model.GetVideoDetail;
import com.example.a11355.peoplescloudmedia.model.GetVideoDetailEntity;
import com.example.a11355.peoplescloudmedia.model.GetVideoListEntity;
import com.example.a11355.peoplescloudmedia.util.BitMapUtil;
import com.example.a11355.peoplescloudmedia.util.Constant;
import com.example.a11355.peoplescloudmedia.util.DesUtil;
import com.example.a11355.peoplescloudmedia.util.LogUtils;
import com.example.a11355.peoplescloudmedia.util.OkHttpUtil;
import com.example.a11355.peoplescloudmedia.util.PhoneUtil;
import com.example.a11355.peoplescloudmedia.util.PreferencesUtil;
import com.example.a11355.peoplescloudmedia.util.StatusBarUtils;
import com.example.a11355.peoplescloudmedia.util.ToastUtil;
import com.example.a11355.peoplescloudmedia.videomodel.VideoPlayer;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;



/*
* 视频播放详情
*
* */
public class VideoActivity extends BaseActivity implements OkHttpUtil.OnDataListener, MediaPlayer.OnCompletionListener, View.OnClickListener, OnAdapterCallbackListener {

    @BindView(R.id.Player)
    VideoPlayer Player;
    @BindView(R.id.sdv_head)
    SimpleDraweeView sdvHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    @BindView(R.id.img_collect)
    ImageView imgCollect;
    private MediaPlayer mediaPlayer;
    private Timer mTimer;
    private long count = 10L;
    private GetVideoListEntity.DataBean data;

    private Gson gson = new GsonBuilder().create();
    private LoadingDialog loadingDialog;
    private String filePath;
    private GetVideoDetailEntity getVideoDetailEntity;
    private String userId;
    private CustomPopupWindow builder;
    private TextView tvReviewNum1;
    private RecyclerView rv;
    private TextView tvPl;
    private LinearLayout llWrite;
    private EditText etPl;
    private TextView tvCommit;
    private GetUserEvaluationeListAdapter getUserEvaluationeListAdapter;
    private List<GetUserEvaluationeListEntity.DataEntity.EvaluationListEntity> dataReview=new ArrayList<>();

    private int PageIndex = 1;
    private int PageSize = 6;
    private int nextPage = 1;

    @Override
    protected int getViewResId() {
        return R.layout.activity_video;
    }

    @Override
    protected void init() {
        StatusBarUtils.translucentStatusBar(this, true);
        data = getIntent().getParcelableExtra("data");
        tvTips.setText(data.getPV() + "人看过");
        tvName.setText(data.getFullHead());

        //  setTimer();

    }

    @Override
    protected void loadData() {
        loadingDialog = LoadingDialog.newInstance("加载中...");
        loadingDialog.show(getFragmentManager());
        //获取详情
        GetVideoDetail getVideoDetail = new GetVideoDetail(data.getVideoId(), PreferencesUtil.getUserId(this));
        OkHttpUtil.postJson(Constant.URL.GetVideoDetail, DesUtil.encrypt(gson.toJson(getVideoDetail)), this);
        //添加浏览量
        AddVideoPV addVideoPV = new AddVideoPV(data.getVideoId());
        OkHttpUtil.postJson(Constant.URL.AddVideoPV, DesUtil.encrypt(gson.toJson(addVideoPV)), this);
    }

    @Override
    public void onResponse(String url, String json) {

        if (!TextUtils.isEmpty(json)) {
            dismissLoading();
            String decrypt = DesUtil.decrypt(json);
            switch (url) {
                case Constant.URL.GetVideoDetail:   //获取视频详情

                    LogUtils.e("GetVideoDetail", decrypt);

                    getVideoDetailEntity = gson.fromJson(decrypt, GetVideoDetailEntity.class);
                    if (getVideoDetailEntity.getCode() == Constant.Integers.SUC) {
                        sdvHead.setImageURI(Constant.URL.BaseImg + getVideoDetailEntity.getData().getHeadIcon());
                        Player.playVideo(Constant.URL.BaseH5 + getVideoDetailEntity.getData().getVideoPath(), "");
                        imgCollect.setSelected(getVideoDetailEntity.getData().getIsCollect() != 0);
                    } else {
                        ToastUtil.initToast(this, getVideoDetailEntity.getMessage());
                    }
                    break;
                case Constant.URL.AddVideoPV: {
                    LogUtils.e("AddVideoPV", decrypt);

                }
                break;

                case Constant.URL.AddUserCollect: {//添加视频收藏
                    LogUtils.e("AddUserCollect", decrypt);


                    AddFindCollectEntity addFindCollectEntity = gson.fromJson(decrypt, AddFindCollectEntity.class);

                    ToastUtil.initToast(this, addFindCollectEntity.getMessage());
                    if (addFindCollectEntity.getCode() == Constant.Integers.SUC) {
                        imgCollect.setSelected(true);
                    } else {
                        if ("帐号已在其它地方登录".equals(addFindCollectEntity.getMessage())) {
                            startActivityForResult(new Intent(this, LoginActivity.class), Constant.Code.LoginCode);
                        }

                    }

                }
                break;
                case Constant.URL.CancelUserCollect: {//取消视频收藏
                    LogUtils.e("CancelUserCollect", decrypt);
                    AddFindCollectEntity addFindCollectEntity = gson.fromJson(decrypt, AddFindCollectEntity.class);

                    ToastUtil.initToast(this, addFindCollectEntity.getMessage());
                    if (addFindCollectEntity.getCode() == Constant.Integers.SUC) {
                        imgCollect.setSelected(false);
                    } else {

                        if ("帐号已在其它地方登录".equals(addFindCollectEntity.getMessage())) {
                            startActivityForResult(new Intent(this, LoginActivity.class), Constant.Code.LoginCode);

                        }

                    }

                }
                break;
                case Constant.URL.AddVideoSharingPV: {//增加视频分享

                }
                break;
                case Constant.URL.GetUserEvaluationeList:{//视频评论列表

                    LogUtils.e("GetUserEvaluationeList", decrypt);
                    if (PageIndex == 1) {
                        dataReview.clear();

                    }
                    removeLoadingItem();
                    GetUserEvaluationeListEntity getUserEvaluationeListEntity = gson.fromJson(decrypt, GetUserEvaluationeListEntity.class);

                    if (getUserEvaluationeListEntity.getCode()==Constant.Integers.SUC){
                        dataReview.addAll(getUserEvaluationeListEntity.getData().getEvaluationList());

                      /*  if (getUserEvaluationeListEntity.getData().getTotal()%PageSize>0) {
                            maxPage = getUserEvaluationeListEntity.getData().getTotal() / PageSize + 1;

                        }  else {
                            maxPage = getUserEvaluationeListEntity.getData().getTotal()/PageSize ;
                        }*/

                        if ( /*maxPage>nextPage*/getUserEvaluationeListEntity.getData().getEvaluationList().size()%PageSize==0&& getUserEvaluationeListEntity.getData().getEvaluationList().size() != 0) {//可能还有下一页
                            dataReview.add(new GetUserEvaluationeListEntity.DataEntity.EvaluationListEntity(1));
                            nextPage = PageIndex + 1;
                        } else {
                            addBaseLine();
                        }
                    } else {
                        addBaseLine();

                    }

                    getUserEvaluationeListAdapter.setData(dataReview);

                }
                    break;

                case Constant.URL.AddUserEvaluation:{
                    LogUtils.e("AddUserEvaluation", decrypt);
                    AddFindCommentEntity addFindCommentEntity = gson.fromJson(decrypt, AddFindCommentEntity.class);
                    ToastUtil.initToast(this, addFindCommentEntity.getMessage());

                    if (addFindCommentEntity.getCode() == Constant.Integers.SUC) {
                        PageIndex = 1;
                        nextPage = 1;
                        loadReviewData();
                        tvPl.setVisibility(View.VISIBLE);
                        llWrite.setVisibility(View.GONE);
                    } else {
                        if ("帐号已在其它地方登录".equals(addFindCommentEntity.getMessage())) {
                            startActivityForResult(new Intent(this, LoginActivity.class), Constant.Code.LoginCode);
                        }

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

    }

    @Override
    public void onBackPressed() {


        if (Player.isFullScreen()) {
            Player.setProtrait();
            return;
        }
        setResult(RESULT_OK);
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        //一定要记得销毁View
        if (Player != null) {
            Player.destroyVideo();
            Player = null;
        }
        super.onDestroy();
    }

    public void setTimer() {
        mTimer = new Timer();

        TimerTask mTimerTask = new TimerTask() {

            @Override
            public void run() {
                if (mediaPlayer == null) {
                    mediaPlayer = Player.mediaPlayer;

                }

                if (mediaPlayer != null) {
                    // mediaPlayer.setOnCompletionListener(VideoActivity.this);
                    stopTimer();
                }


                /*isTimerRunning = true;

                if (isChanging == true)//当用户正在拖动进度进度条时不处理进度条的的进度

                    return;

                MusicBox.skbMusic.setProgress(mediaPlayer.getCurrentPosition());*/

            }

        };

//每隔10毫秒检测一下播放进度

        mTimer.schedule(mTimerTask, 0, count);
    }

    private void stopTimer() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }

        count = 0L;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @OnClick({R.id.sdv_review, R.id.img_collect, R.id.img_shear, R.id.img_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sdv_review://留言
                if (getVideoDetailEntity == null) {
                    return;
                }
                Player.pauseVideo();
                initPopu();
                break;
            case R.id.img_collect://收藏
                if (getVideoDetailEntity == null) {
                    return;
                }

                if (isLogin()) {
                    Player.pauseVideo();
                    if (imgCollect.isSelected()) {
                        loadingDialog = LoadingDialog.newInstance("取消收藏中...");
                        loadingDialog.show(this.getFragmentManager());

                        CancelUserCollect addUserCollect = new CancelUserCollect(PreferencesUtil.getToken(this), PreferencesUtil.getUserId(this), getVideoDetailEntity.getData().getVideoId());

                        OkHttpUtil.postJson(Constant.URL.CancelUserCollect, DesUtil.encrypt(gson.toJson(addUserCollect)), this);

                    } else {
                        loadingDialog = LoadingDialog.newInstance("收藏中...");
                        loadingDialog.show(this.getFragmentManager());
                        AddUserCollect addUserCollect = new AddUserCollect(PreferencesUtil.getToken(this), PreferencesUtil.getUserId(this), "0", getVideoDetailEntity.getData().getVideoId());
                        OkHttpUtil.postJson(Constant.URL.AddUserCollect, DesUtil.encrypt(gson.toJson(addUserCollect)), this);

                    }

                } else {
                    ToastUtil.initToast(this, "未登录");
                }

                break;
            case R.id.img_shear://分享
                if (getVideoDetailEntity == null) {
                    return;
                }

                AddVideoSharingPV AddVideoSharingPV = new AddVideoSharingPV(getVideoDetailEntity.getData().getVideoId());

                OkHttpUtil.postJson(Constant.URL.AddVideoSharingPV, DesUtil.encrypt(gson.toJson(AddVideoSharingPV)), this);
                share();
                break;
            case R.id.img_close://关闭

                onBackPressed();
                break;
        }
    }

    //初始化评论Popu
    private void initPopu() {
        loadingDialog = LoadingDialog.newInstance("加载中...");
        loadingDialog.show(getFragmentManager());

        builder = new CustomPopupWindow.Builder()
                .setContext(this)
                .setContentView(R.layout.popu_review)
                .setwidth(LinearLayout.LayoutParams.MATCH_PARENT)
                .setheight(LinearLayout.LayoutParams.MATCH_PARENT)
                .setOutSideCancel(false)
                .builder()
                .showAtLocation(R.layout.activity_h5_fornewsdetical, Gravity.BOTTOM, 0, 0);


        tvReviewNum1 = (TextView) builder.getItemView(R.id.tv_reviewNum);
        ImageView imgClose = (ImageView) builder.getItemView(R.id.img_close);
        rv = (RecyclerView) builder.getItemView(R.id.rv);
        tvPl = (TextView) builder.getItemView(R.id.tv_pl);
        llWrite = (LinearLayout) builder.getItemView(R.id.ll_write);
        etPl = (EditText) builder.getItemView(R.id.et_pl);
        tvCommit = (TextView) builder.getItemView(R.id.tv_commit);

        imgClose.setOnClickListener(this);
        tvCommit.setOnClickListener(this);
        tvPl.setOnClickListener(this);

        tvReviewNum1.setText(getVideoDetailEntity.getData().getEvaluationNum()+"");

        getUserEvaluationeListAdapter = new GetUserEvaluationeListAdapter(this, this);
        rv.addItemDecoration(new DividerGridItem(this));
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(getUserEvaluationeListAdapter);
        getUserEvaluationeListAdapter.setEmptyView(R.layout.empty_tips);
        getUserEvaluationeListAdapter.setEmptyTips(R.id.tv_emptyTips, "暂无数据");
        loadReviewData();
    }

    public boolean isLogin() {
        userId = PreferencesUtil.getUserId(this);
        if ("default".equals(userId)) {
            return false;
        } else {
            return true;
        }
    }

    //分享
    public void share() {
        if (getVideoDetailEntity == null) {
            return;
        }
        Player.pauseVideo();
        if (TextUtils.isEmpty(getVideoDetailEntity.getData().getFilePath())) {
            filePath = Environment.getExternalStorageDirectory() + "/Android/data/" +
                    getPackageName() + "/cache/logo.jpg";
            if (!new File(filePath).exists()) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
                BitMapUtil.saveBitmap2File(bitmap, filePath);
            } else {
                filePath = Constant.URL.BaseImg + getVideoDetailEntity.getData().getFilePath();
            }
        }


        PreferencesUtil.showShare(this, getVideoDetailEntity.getData().getNickName(), Constant.URL.ShearVideo + getVideoDetailEntity.getData().getVideoId(),
                getVideoDetailEntity.getData().getNickName(), filePath, this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_close: { //关闭popu
                if (builder != null) {
                    builder.dismiss();

                    PhoneUtil.hideKeyboard(v);
                    tvPl.setVisibility(View.VISIBLE);
                    llWrite.setVisibility(View.GONE);

                }
            }
            break;
            case R.id.tv_commit: { //提交评论


                if (TextUtils.isEmpty(etPl.getText().toString())) {
                    ToastUtil.initToast(this, "请添加评论");
                } else {
                    PhoneUtil.hideKeyboard(v);

                    loadingDialog = LoadingDialog.newInstance("评论中...");
                    loadingDialog.show(getFragmentManager());
                    AddUserEvaluation addUserEvaluation = new AddUserEvaluation(PreferencesUtil.getToken(this), PreferencesUtil.getUserId(this),   etPl.getText().toString(),"0",getVideoDetailEntity.getData().getVideoId());
                    OkHttpUtil.postJson(Constant.URL.AddUserEvaluation, DesUtil.encrypt(gson.toJson(addUserEvaluation)), this);
                }
            }
            break;
            case R.id.tv_pl: {   //显示评论输入框

                if (isLogin()) {

                    tvPl.setVisibility(View.GONE);
                    llWrite.setVisibility(View.VISIBLE);
                } else {
                    ToastUtil.initToast(this, "未登录");

                }

            }
            break;
        }
    }

    private void loadReviewData() {
        GetUserEvaluationeList getUserEvaluationeList = new GetUserEvaluationeList(PreferencesUtil.getToken(this),PreferencesUtil.getUserId(this),getVideoDetailEntity.getData().getVideoId(), nextPage + "", PageSize + "");

        OkHttpUtil.postJson(Constant.URL.GetUserEvaluationeList, DesUtil.encrypt(gson.toJson(getUserEvaluationeList)), this);
    }


    @Override
    public void onCallback() {
        if (nextPage == PageIndex + 1) {
            PageIndex++;
            loadReviewData();
        }

    }

    private void removeLoadingItem() {
        if (dataReview.size() > 0) {
            if (dataReview.get(dataReview.size() - 1).getType() == 1) {
                dataReview.remove(dataReview.size() - 1);
            }
        }
    }

    private void addBaseLine() {
        if (PageIndex != 1) {
            dataReview.add(new GetUserEvaluationeListEntity.DataEntity.EvaluationListEntity(2));
        }
    }




}
