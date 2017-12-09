package com.example.a11355.peoplescloudmedia.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.AbsRecyclerViewAdapter;
import com.example.a11355.peoplescloudmedia.base.OnAdapterCallbackListener;
import com.example.a11355.peoplescloudmedia.model.GetAttentionToListEntity;

/**
 * Created by 11355 on 2017/11/27.
 */

public class GetAttentionToListAdapter extends AbsRecyclerViewAdapter<GetAttentionToListEntity.DataBean> {
    private final OnAdapterCallbackListener onAdapterCallbackListener;
    private View.OnClickListener onClickListener;

    public GetAttentionToListAdapter(Context context, OnAdapterCallbackListener onAdapterCallbackListener, View.OnClickListener onClickListener) {
        super(context, R.layout.item_getattention, R.layout.item_next_page_loading,
                R.layout.item_page_bottom);
        this.onClickListener = onClickListener;
        this.context = context;
        this.onAdapterCallbackListener = onAdapterCallbackListener;
    }

    @Override
    public int getItemType(GetAttentionToListEntity.DataBean d) {
        return d.getType();
    }

    @Override
    public void onBindHolder(AbsRecyclerViewAdapter.RecyclerViewHolder holder, GetAttentionToListEntity.DataBean d, int position) {
        switch (d.getType()) {
            case 0:
                holder.bindSimpleDraweeViewBase(R.id.sdv, d.getHeadIcon())
                        .bindTextView(R.id.tv_name, d.getNickName())
                        .bindTextView(R.id.tv_signature, TextUtils.isEmpty(d.getSignature()) ? "暂未设置" : d.getSignature());

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
