package com.example.a11355.peoplescloudmedia.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.AbsRecyclerViewAdapter;
import com.example.a11355.peoplescloudmedia.base.OnAdapterCallbackListener;
import com.example.a11355.peoplescloudmedia.model.GetFindNewsCollectListEntity;

/**
 * Created by 11355 on 2017/11/27.
 */

public class NewsCollectListAdapter extends AbsRecyclerViewAdapter<GetFindNewsCollectListEntity.DataBean.ArtilesBean> {
    private final OnAdapterCallbackListener onAdapterCallbackListener;
    private View.OnClickListener onClickListener;

    public NewsCollectListAdapter(Context context, OnAdapterCallbackListener onAdapterCallbackListener, View.OnClickListener onClickListener) {
        super(context, R.layout.item_rv_newscollect, R.layout.item_next_page_loading,
                R.layout.item_page_bottom);
        this.onClickListener = onClickListener;
        this.context = context;
        this.onAdapterCallbackListener = onAdapterCallbackListener;
    }

    @Override
    public int getItemType(GetFindNewsCollectListEntity.DataBean.ArtilesBean d) {
        return d.getType();
    }

    @Override
    public void onBindHolder(AbsRecyclerViewAdapter.RecyclerViewHolder holder, GetFindNewsCollectListEntity.DataBean.ArtilesBean d, int position) {
        switch (d.getType()) {
            case 0:
                holder.bindSimpleDraweeView(R.id.sdv, Uri.parse(d.getThumb()+""))
                        .bindTextView(R.id.tv_title, d.getTitle())
                        .bindTextView(R.id.tv_focus, d.getReads_count() + "")
                        .bindTextView(R.id.tv_review, d.getComments_count() + "")
                        .bindTextView(R.id.tv_share, d.getReposts_count() + "")
                        .bindTextView(R.id.tv_time, d.getCreated_at());
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
