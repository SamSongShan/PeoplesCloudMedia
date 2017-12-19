package com.example.a11355.peoplescloudmedia.activity;

import android.media.MediaPlayer;
import android.text.TextUtils;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.BaseActivity;
import com.example.a11355.peoplescloudmedia.custom.LoadingDialog;
import com.example.a11355.peoplescloudmedia.model.GetVideoDetail;
import com.example.a11355.peoplescloudmedia.model.GetVideoDetailEntity;
import com.example.a11355.peoplescloudmedia.model.GetVideoListEntity;
import com.example.a11355.peoplescloudmedia.util.Constant;
import com.example.a11355.peoplescloudmedia.util.DesUtil;
import com.example.a11355.peoplescloudmedia.util.LogUtils;
import com.example.a11355.peoplescloudmedia.util.OkHttpUtil;
import com.example.a11355.peoplescloudmedia.util.PreferencesUtil;
import com.example.a11355.peoplescloudmedia.util.StatusBarUtils;
import com.example.a11355.peoplescloudmedia.util.ToastUtil;
import com.example.a11355.peoplescloudmedia.videomodel.VideoPlayer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

/*
* 视频播放详情
*
* */
public class VideoActivity extends BaseActivity implements OkHttpUtil.OnDataListener, MediaPlayer.OnCompletionListener {

    @BindView(R.id.Player)
    VideoPlayer Player;
    private MediaPlayer mediaPlayer;
    private Timer mTimer;
    private long count = 10L;
    private GetVideoListEntity.DataBean data;

    private Gson gson = new GsonBuilder().create();
    private LoadingDialog loadingDialog;

    @Override
    protected int getViewResId() {
        return R.layout.activity_video;
    }

    @Override
    protected void init() {
        StatusBarUtils.translucentStatusBar(this, true);
        data = getIntent().getParcelableExtra("data");
        //  setTimer();

    }

    @Override
    protected void loadData() {
        loadingDialog = LoadingDialog.newInstance("加载中...");
        loadingDialog.show(getFragmentManager());
        GetVideoDetail getVideoDetail = new GetVideoDetail(data.getVideoId(), PreferencesUtil.getUserId(this));
        OkHttpUtil.postJson(Constant.URL.GetVideoDetail, DesUtil.encrypt(gson.toJson(getVideoDetail)), this);
    }

    @Override
    public void onResponse(String url, String json) {

        if (!TextUtils.isEmpty(json)) {
            dismissLoading();
            String decrypt = DesUtil.decrypt(json);
            switch (url) {
                case Constant.URL.GetVideoDetail:   //获取视频详情

                    LogUtils.e("GetVideoDetail", decrypt);

                    GetVideoDetailEntity getVideoDetailEntity = gson.fromJson(decrypt, GetVideoDetailEntity.class);
                    if (getVideoDetailEntity.getCode() == Constant.Integers.SUC) {
                        Player.playVideo(Constant.URL.BaseH5 + getVideoDetailEntity.getData().getVideoPath(), "");

                    } else {
                        ToastUtil.initToast(this, getVideoDetailEntity.getMessage());
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
}
