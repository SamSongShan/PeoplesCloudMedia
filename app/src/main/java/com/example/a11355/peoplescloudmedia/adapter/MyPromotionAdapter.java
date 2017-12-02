package com.example.a11355.peoplescloudmedia.adapter;

import android.content.Context;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.AbsRecyclerViewAdapter;
import com.example.a11355.peoplescloudmedia.base.OnAdapterCallbackListener;
import com.example.a11355.peoplescloudmedia.model.GetRecommendUserListEntity;

/**
 * Created by 11355 on 2017/12/2.
 */

public class MyPromotionAdapter extends AbsRecyclerViewAdapter<GetRecommendUserListEntity.DataBean.UserListBean> {

    private OnAdapterCallbackListener onAdapterCallbackListener;

    public MyPromotionAdapter(Context context, OnAdapterCallbackListener onAdapterCallbackListener) {
        super(context, R.layout.item_mypromotion, R.layout.item_next_page_loading,
                R.layout.item_page_bottom);
        this.onAdapterCallbackListener = onAdapterCallbackListener;
    }

    @Override
    public int getItemType(GetRecommendUserListEntity.DataBean.UserListBean d) {
        return d.getType();
    }

    @Override
    public void onBindHolder(AbsRecyclerViewAdapter.RecyclerViewHolder holder, GetRecommendUserListEntity.DataBean.UserListBean d, int position) {
        switch (d.getType()) {
            case 0:
                holder.bindTextView(R.id.tv_phone, d.getMobile())
                        .bindTextView(R.id.tv_date, d.getCreateDate()) ;
                break;
            case 1:
                onAdapterCallbackListener.onCallback();
                break;
        }
    }
}
