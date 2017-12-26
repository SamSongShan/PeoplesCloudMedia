package com.example.a11355.peoplescloudmedia.adapter;

import android.content.Context;
import android.view.View;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.AbsRecyclerViewAdapter;
import com.example.a11355.peoplescloudmedia.base.OnAdapterCallbackListener;
import com.example.a11355.peoplescloudmedia.model.GetMusicListEntity;

/**
 * Created by 11355 on 2017/11/27.
 */

public class GetMusicListAdapter extends AbsRecyclerViewAdapter<GetMusicListEntity.DataBean> {
    private final OnAdapterCallbackListener onAdapterCallbackListener;
    private View.OnClickListener onClickListener;

    public GetMusicListAdapter(Context context, OnAdapterCallbackListener onAdapterCallbackListener, View.OnClickListener onClickListener) {
        super(context, R.layout.item_rv_music_search, R.layout.item_next_page_loading,
                R.layout.item_page_bottom);
        this.onClickListener = onClickListener;
        this.context = context;
        this.onAdapterCallbackListener = onAdapterCallbackListener;
    }


    @Override
    public void onBindHolder(AbsRecyclerViewAdapter.RecyclerViewHolder holder, GetMusicListEntity.DataBean d, int position) {

        holder.bindSimpleDraweeViewBase(R.id.sdv_Head, d.getFileSource())
                .bindTextView(R.id.tv_name, d.getCreateUserName() + "")
                .bindTextView(R.id.tv_singer, d.getFileName() + "")
                .setViewSelected(R.id.img_play, d.getIsplay() == 1)
                .setClickListenerAndTag(R.id.img_play, onClickListener,position);



                /*View view = holder.getView(R.id.tv_focus);
                View view1 = holder.getView(R.id.tv_review);
                View view2 = holder.getView(R.id.tv_share);

                view.setTag(position);
                view1.setTag(position);
                view2.setTag(position);

                view.setOnClickListener(onClickListener);
                view1.setOnClickListener(onClickListener);
                view2.setOnClickListener(onClickListener);*/


    }
}

