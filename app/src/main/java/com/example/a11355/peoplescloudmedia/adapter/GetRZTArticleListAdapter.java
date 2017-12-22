package com.example.a11355.peoplescloudmedia.adapter;

import android.content.Context;
import android.view.View;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.AbsRecyclerViewAdapter;
import com.example.a11355.peoplescloudmedia.base.OnAdapterCallbackListener;
import com.example.a11355.peoplescloudmedia.model.GetRZTArticleListEntity;

/**
 * Created by 11355 on 2017/11/27.
 */

public class GetRZTArticleListAdapter extends AbsRecyclerViewAdapter<GetRZTArticleListEntity.DataBean> {
    private final OnAdapterCallbackListener onAdapterCallbackListener;
    private View.OnClickListener onClickListener;

    public GetRZTArticleListAdapter(Context context, OnAdapterCallbackListener onAdapterCallbackListener, View.OnClickListener onClickListener) {
        super(context, R.layout.item_rzt_article, R.layout.item_next_page_loading,
                R.layout.item_page_bottom);
        this.onClickListener = onClickListener;
        this.context = context;
        this.onAdapterCallbackListener = onAdapterCallbackListener;
    }

    @Override
    public int getItemType(GetRZTArticleListEntity.DataBean d) {
        return d.getType();
    }

    @Override
    public void onBindHolder(AbsRecyclerViewAdapter.RecyclerViewHolder holder, GetRZTArticleListEntity.DataBean d, int position) {
        switch (d.getType()) {
            case 0:
                holder.bindSimpleDraweeViewBase(R.id.sdv, d.getImageUrl())
                        .bindTextView(R.id.tv_title, d.getTitle())
                        .bindTextView(R.id.tv_date, d.getCreateDate())
                        .bindTextView(R.id.tv_focus, d.getPV() + "")
                        .bindTextView(R.id.tv_share, d.getSharingPV() + "");

                break;
            case 1:
                onAdapterCallbackListener.onCallback();
                break;

        }
    }
}
