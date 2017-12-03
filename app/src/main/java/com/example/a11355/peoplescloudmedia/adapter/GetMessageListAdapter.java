package com.example.a11355.peoplescloudmedia.adapter;

import android.content.Context;
import android.view.View;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.AbsRecyclerViewAdapter;
import com.example.a11355.peoplescloudmedia.base.OnAdapterCallbackListener;
import com.example.a11355.peoplescloudmedia.model.GetMessageListEntity;

/**
 * Created by 11355 on 2017/11/27.
 */

public class GetMessageListAdapter extends AbsRecyclerViewAdapter<GetMessageListEntity.DataBean> {
    private final OnAdapterCallbackListener onAdapterCallbackListener;
    private View.OnClickListener onClickListener;

    public GetMessageListAdapter(Context context, OnAdapterCallbackListener onAdapterCallbackListener, View.OnClickListener onClickListener) {
        super(context, R.layout.item_rv_messagefragement, R.layout.item_next_page_loading,
                R.layout.item_page_bottom);
        this.onClickListener = onClickListener;
        this.context = context;
        this.onAdapterCallbackListener = onAdapterCallbackListener;
    }

    @Override
    public int getItemType(GetMessageListEntity.DataBean d) {
        return d.getType();
    }

    @Override
    public void onBindHolder(AbsRecyclerViewAdapter.RecyclerViewHolder holder, GetMessageListEntity.DataBean d, int position) {
        switch (d.getType()) {
            case 0:
                holder.bindSimpleDraweeViewBase(R.id.sdv, d.getFilePath())
                        .bindTextView(R.id.tv_title,d.getFullHead())
                        .bindTextView(R.id.tv_description, d.getBriefHead() + "");

                View view = holder.getView(R.id.tv_check);
                view.setTag(position);
                view.setOnClickListener(onClickListener);
                break;
            case 1:
                onAdapterCallbackListener.onCallback();
                break;

        }
    }
}
