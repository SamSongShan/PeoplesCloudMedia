package com.example.a11355.peoplescloudmedia.adapter;

import android.content.Context;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.AbsRecyclerViewAdapter;
import com.example.a11355.peoplescloudmedia.base.OnAdapterCallbackListener;
import com.example.a11355.peoplescloudmedia.model.GetUserEvaluationeListEntity;

/**
 * Created by 11355 on 2017/11/27.
 */

public class GetUserEvaluationeListAdapter extends AbsRecyclerViewAdapter<GetUserEvaluationeListEntity.DataEntity.EvaluationListEntity> {
    private final OnAdapterCallbackListener onAdapterCallbackListener;

    public GetUserEvaluationeListAdapter(Context context, OnAdapterCallbackListener onAdapterCallbackListener) {
        super(context, R.layout.item_review, R.layout.item_next_page_loading,
                R.layout.item_page_bottom);
        this.context = context;
        this.onAdapterCallbackListener = onAdapterCallbackListener;
    }

    @Override
    public int getItemType(GetUserEvaluationeListEntity.DataEntity.EvaluationListEntity d) {
        return d.getType();
    }

    @Override
    public void onBindHolder(AbsRecyclerViewAdapter.RecyclerViewHolder holder, GetUserEvaluationeListEntity.DataEntity.EvaluationListEntity d, int position) {
        switch (d.getType()) {
            case 0:
                holder.bindSimpleDraweeViewBase(R.id.sdv_from, d.getHeadIcon())
                        .bindTextView(R.id.tv_name, d.getNickName() + "")
                        .bindTextView(R.id.tv_date, d.getCreateDate() + "")
                        .bindTextView(R.id.tv_content, d.getContent() + "");


                break;
            case 1:
                onAdapterCallbackListener.onCallback();
                break;

        }
    }
}
