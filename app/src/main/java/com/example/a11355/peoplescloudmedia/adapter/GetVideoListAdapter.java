package com.example.a11355.peoplescloudmedia.adapter;

import android.content.Context;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.AbsRecyclerViewAdapter;
import com.example.a11355.peoplescloudmedia.base.OnAdapterCallbackListener;
import com.example.a11355.peoplescloudmedia.model.GetVideoListEntity;

/**
 * Created by 11355 on 2017/11/27.
 */

public class GetVideoListAdapter extends AbsRecyclerViewAdapter<GetVideoListEntity.DataBean> {
    private final OnAdapterCallbackListener onAdapterCallbackListener;

    public GetVideoListAdapter(Context context, OnAdapterCallbackListener onAdapterCallbackListener) {
        super(context, R.layout.item_rv_videofragement, R.layout.item_next_page_loading,
                R.layout.item_page_bottom);
        this.context = context;
        this.onAdapterCallbackListener = onAdapterCallbackListener;
    }

    @Override
    public int getItemType(GetVideoListEntity.DataBean d) {
        return d.getType();
    }

    @Override
    public void onBindHolder(AbsRecyclerViewAdapter.RecyclerViewHolder holder, GetVideoListEntity.DataBean d, int position) {
        switch (d.getType()) {
            case 0:
                holder.bindSimpleDraweeViewBase(R.id.sdv, d.getFilePath())
                        .bindTextView(R.id.tv_title, d.getFullHead())
                        .bindSimpleDraweeViewBase(R.id.sdv_from, d.getUserHeadImg())
                        .bindTextView(R.id.tv_from, d.getPV() + "");
                break;
            case 1:
                onAdapterCallbackListener.onCallback();
                break;

        }
    }
}
