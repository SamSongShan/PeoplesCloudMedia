package com.example.a11355.peoplescloudmedia.adapter;

import android.content.Context;
import android.net.Uri;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.AbsRecyclerViewAdapter;
import com.example.a11355.peoplescloudmedia.base.OnAdapterCallbackListener;
import com.example.a11355.peoplescloudmedia.model.GetNewsCommentListEntity;

/**
 * Created by 11355 on 2017/11/27.
 */

public class GetNewsReviewAdapter extends AbsRecyclerViewAdapter<GetNewsCommentListEntity.DataEntity.CommentsEntity> {
    private final OnAdapterCallbackListener onAdapterCallbackListener;

    public GetNewsReviewAdapter(Context context, OnAdapterCallbackListener onAdapterCallbackListener) {
        super(context, R.layout.item_review, R.layout.item_next_page_loading,
                R.layout.item_page_bottom);
        this.context = context;
        this.onAdapterCallbackListener = onAdapterCallbackListener;
    }

    @Override
    public int getItemType(GetNewsCommentListEntity.DataEntity.CommentsEntity d) {
        return d.getType();
    }

    @Override
    public void onBindHolder(AbsRecyclerViewAdapter.RecyclerViewHolder holder, GetNewsCommentListEntity.DataEntity.CommentsEntity d, int position) {
        switch (d.getType()) {
            case 0:
                holder.bindSimpleDraweeView(R.id.sdv_from,Uri.parse(d.getAvatar()) )
                        .bindTextView(R.id.tv_name, d.getName() + "")
                        .bindTextView(R.id.tv_date, d.getCreated_at() + "")
                        .bindTextView(R.id.tv_content, d.getText() + "");



                break;
            case 1:
                onAdapterCallbackListener.onCallback();
                break;

        }
    }
}
