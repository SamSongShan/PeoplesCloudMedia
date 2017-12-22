package com.example.a11355.peoplescloudmedia.adapter;

import android.content.Context;
import android.view.View;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.AbsRecyclerViewAdapter;
import com.example.a11355.peoplescloudmedia.base.OnAdapterCallbackListener;
import com.example.a11355.peoplescloudmedia.model.GetAdvertisingListEntity;

/**
 * Created by 11355 on 2017/11/27.
 */

public class GetAdvertisingListAdapter extends AbsRecyclerViewAdapter<GetAdvertisingListEntity.DataEntity> {
    private final OnAdapterCallbackListener onAdapterCallbackListener;
    private View.OnClickListener onClickListener;

    public GetAdvertisingListAdapter(Context context, OnAdapterCallbackListener onAdapterCallbackListener, View.OnClickListener onClickListener) {
        super(context, R.layout.item_rzt_ad, R.layout.item_next_page_loading,
                R.layout.item_page_bottom);
        this.onClickListener = onClickListener;
        this.context = context;
        this.onAdapterCallbackListener = onAdapterCallbackListener;
    }

    @Override
    public int getItemType(GetAdvertisingListEntity.DataEntity d) {
        return d.getType();
    }

    @Override
    public void onBindHolder(AbsRecyclerViewAdapter.RecyclerViewHolder holder, GetAdvertisingListEntity.DataEntity d, int position) {
        switch (d.getType()) {
            case 0:
                holder.bindSimpleDraweeViewBase(R.id.sdv, d.getFilePath())
                        .bindTextView(R.id.tv_title, d.getFullHead())
                        .bindTextView(R.id.tv_date, d.getCreateDate());

                break;
            case 1:
                onAdapterCallbackListener.onCallback();
                break;

        }
    }
}
