package com.example.a11355.peoplescloudmedia.activity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.adapter.GetMusicListAdapter;
import com.example.a11355.peoplescloudmedia.base.AbsRecyclerViewAdapter;
import com.example.a11355.peoplescloudmedia.base.BaseActivity;
import com.example.a11355.peoplescloudmedia.base.OnAdapterCallbackListener;
import com.example.a11355.peoplescloudmedia.custom.DividerGridItem;
import com.example.a11355.peoplescloudmedia.custom.LoadingDialog;
import com.example.a11355.peoplescloudmedia.model.GetMusicList;
import com.example.a11355.peoplescloudmedia.model.GetMusicListEntity;
import com.example.a11355.peoplescloudmedia.util.Constant;
import com.example.a11355.peoplescloudmedia.util.DesUtil;
import com.example.a11355.peoplescloudmedia.util.LogUtils;
import com.example.a11355.peoplescloudmedia.util.OkHttpUtil;
import com.example.a11355.peoplescloudmedia.util.PhoneUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class GetMusicListActivity extends BaseActivity implements TextView.OnEditorActionListener, OkHttpUtil.OnDataListener, AbsRecyclerViewAdapter.OnItemClickListener, OnAdapterCallbackListener, View.OnClickListener {

    @BindView(R.id.rv_store)
    RecyclerView rvStore;
    @BindView(R.id.et_search)
    EditText etSearch;
    private Gson gson = new GsonBuilder().create();
    private int PageIndex = 1;
    private int PageSize = 10;
    private int nextPage = 1;
    private LoadingDialog loadingDialog;
    private List<GetMusicListEntity.DataBean> newsData = new ArrayList<>();
    private GetMusicListAdapter getMusicListAdapter;
    private String key;
    private int lastPosition;//上次播放位置
    private MediaPlayer  player = new MediaPlayer();
    ;

    @Override
    protected int getViewResId() {
        return R.layout.activity_get_music_list;
    }
    @OnClick(R.id.tv_cancel)
    public void onViewClicked(View v) {
        PhoneUtil.hideKeyboard(v);
        onBackPressed();
    }

    @Override
    protected void init() {
        etSearch.setOnEditorActionListener(this);
        rvStore.setLayoutManager(new LinearLayoutManager(this));
        getMusicListAdapter = new GetMusicListAdapter(this, this, this);
        rvStore.addItemDecoration(new DividerGridItem(this));
        rvStore.setAdapter(getMusicListAdapter);
        getMusicListAdapter.setOnItemClickListener(this);


        getMusicListAdapter.setEmptyView(R.layout.empty_tips);
        getMusicListAdapter.setEmptyTips(R.id.tv_emptyTips, "暂无数据");

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH && !TextUtils.isEmpty(etSearch.getText().toString())) {//点击搜索
            PhoneUtil.hideKeyboard(v);
            loadingDialog = LoadingDialog.newInstance("搜索中...");
            loadingDialog.show(getFragmentManager());
            key = etSearch.getText().toString();
            String toJson = gson.toJson(new GetMusicList( key));
            OkHttpUtil.postJson(Constant.URL.GetMusicList, DesUtil.encrypt(toJson), this);
            return true;
        }
        return false;
    }

    @Override
    public void onResponse(String url, String json) {
        if (!TextUtils.isEmpty(json)) {
            String decrypt = DesUtil.decrypt(json);
            switch (url) {
                case Constant.URL.GetMusicList: {
                    dismissLoading();
                    newsData.clear();
                    LogUtils.e("GetMusicList", decrypt);
                    GetMusicListEntity getMessageListEntity = gson.fromJson(decrypt, GetMusicListEntity.class);
                    if (getMessageListEntity.getCode() == Constant.Integers.SUC) {
                        newsData.addAll(getMessageListEntity.getData());

                    } else {
                    }

                }
                getMusicListAdapter.setData(newsData);
                break;
            }
        }
    }

    @Override
    public void onFailure(String url, String error) {

    }


    @Override
    public void onCallback() {

    }


    @Override
    public void onItemClick(View v, int position) {

        Intent intent = new Intent();
        intent.putExtra("path",newsData.get(position).getFilePath());
        intent.putExtra("singer",newsData.get(position).getFileName());
        setResult(RESULT_OK,intent);
        onBackPressed();
    /*    Intent intent = new Intent(this, H5ActivityForNewsDetical.class);
        intent.putExtra("url", String.format(Constant.URL.NewsDetailsLink,newsData.get(position).getId()+"", SharedPreferencesUtil.getUserId(this)));

        intent.putExtra("data", newsData.get(position));
        startActivity(intent);*/
    }

    private void dismissLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }

    }

    @Override
    public void onClick(View v) {

        newsData.get(lastPosition).setIsplay(0);
        lastPosition= (int) v.getTag();
        if (v.isSelected()){
            newsData.get(lastPosition).setIsplay(0);

            if (player!=null){
                player.stop();
            }

        } else {
            newsData.get(lastPosition).setIsplay(1);

            if (player!=null){
                play();
            }

        }

        getMusicListAdapter.notifyDataSetChanged();



    }

    private void play() {
        //下面开始实例化MediaPlayer对象
        //然后指定需要播放文件的路径，初始化MediaPlayer
        try {
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(Constant.URL.BaseH5+newsData.get(lastPosition).getFilePath());
             player.prepareAsync();
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        player.release();
        player = null;
        super.onDestroy();
    }
}

