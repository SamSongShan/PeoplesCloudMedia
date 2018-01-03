package com.example.a11355.peoplescloudmedia.adapter;

import android.content.Context;
import android.view.View;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.AbsRecyclerViewAdapter;
import com.example.a11355.peoplescloudmedia.base.OnAdapterCallbackListener;
import com.example.a11355.peoplescloudmedia.model.GetGraphicEditorListEntity;

/**
 * Created by 11355 on 2017/11/27.
 */

public class GetGraphicEditorAdapter extends AbsRecyclerViewAdapter<GetGraphicEditorListEntity.DataEntity> {
    private final OnAdapterCallbackListener onAdapterCallbackListener;
    private View.OnClickListener onClickListener;

    public GetGraphicEditorAdapter(Context context, OnAdapterCallbackListener onAdapterCallbackListener, View.OnClickListener onClickListener) {
        super(context, R.layout.item_rv_newscollect, R.layout.item_next_page_loading,
                R.layout.item_page_bottom);
        this.onClickListener = onClickListener;
        this.context = context;
        this.onAdapterCallbackListener = onAdapterCallbackListener;
    }

    @Override
    public int getItemType(GetGraphicEditorListEntity.DataEntity d) {
        return d.getType();
    }

    @Override
    public void onBindHolder(AbsRecyclerViewAdapter.RecyclerViewHolder holder, GetGraphicEditorListEntity.DataEntity d, int position) {
        switch (d.getType()) {
            case 0:
                holder.bindSimpleDraweeViewBase(R.id.sdv, d.getImageUrl()+"")
                        .bindTextView(R.id.tv_title, d.getTitle()+"")
                        .bindTextView(R.id.tv_focus, d.getPV() + "")
                        .bindTextView(R.id.tv_review, d.getCommentPV() + "")
                        .bindTextView(R.id.tv_share, d.getSharingPV() + "")
                        .bindTextView(R.id.tv_time, d.getCreateDate());
                        /*.bindTextView(R.id.tv_from, d.getAuthor());*/


                /*View view = holder.getView(R.id.tv_focus);
                View view1 = holder.getView(R.id.tv_review);
                View view2 = holder.getView(R.id.tv_share);

                view.setTag(position);
                view1.setTag(position);
                view2.setTag(position);

                view.setOnClickListener(onClickListener);
                view1.setOnClickListener(onClickListener);
                view2.setOnClickListener(onClickListener);*/
                break;
            case 1:
                onAdapterCallbackListener.onCallback();
                break;

        }
    }
}
