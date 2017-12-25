package com.example.a11355.peoplescloudmedia.adapter;

import android.content.Context;
import android.view.View;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.AbsRecyclerViewAdapter;
import com.example.a11355.peoplescloudmedia.base.OnAdapterCallbackListener;
import com.example.a11355.peoplescloudmedia.model.GetNewsListEntity;

/**
 * Created by 11355 on 2017/11/27.
 */

public class GetNewsListSearchAdapter extends AbsRecyclerViewAdapter<GetNewsListEntity.DataBean.ArtilesBean> {
    private final OnAdapterCallbackListener onAdapterCallbackListener;
    private View.OnClickListener onClickListener;

    public GetNewsListSearchAdapter(Context context, OnAdapterCallbackListener onAdapterCallbackListener, View.OnClickListener onClickListener) {
        super(context, R.layout.item_rv_news_search, R.layout.item_next_page_loading,
                R.layout.item_page_bottom);
        this.onClickListener = onClickListener;
        this.context = context;
        this.onAdapterCallbackListener = onAdapterCallbackListener;
    }

    @Override
    public int getItemType(GetNewsListEntity.DataBean.ArtilesBean d) {
        return d.getType();
    }

    @Override
    public void onBindHolder(AbsRecyclerViewAdapter.RecyclerViewHolder holder, GetNewsListEntity.DataBean.ArtilesBean d, int position) {
        switch (d.getType()) {
            case 0:
                holder.bindTextView(R.id.tv_title, d.getTitle())
                        .bindTextView(R.id.tv_focus, d.getReads_count() + "")
                        .bindTextView(R.id.tv_review, d.getComments_count() + "")
                        .bindTextView(R.id.tv_share, d.getReposts_count() + "")
                        .bindTextView(R.id.tv_from, d.getAuthor());


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
