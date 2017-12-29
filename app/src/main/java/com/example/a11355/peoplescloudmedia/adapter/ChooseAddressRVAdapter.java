package com.example.a11355.peoplescloudmedia.adapter;

import android.content.Context;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.AbsRecyclerViewAdapter;
import com.example.a11355.peoplescloudmedia.model.AreaListEntity;

import java.util.List;




public class ChooseAddressRVAdapter extends AbsRecyclerViewAdapter<AreaListEntity.DataEntity> {

    public ChooseAddressRVAdapter(Context context, List<AreaListEntity.DataEntity> data) {
        super(context, data, R.layout.item_text_view);
    }

    @Override
    public void onBindHolder(AbsRecyclerViewAdapter.RecyclerViewHolder holder, AreaListEntity.DataEntity d, int position) {
        holder.bindTextView(R.id.tv_addressItem, d.getAreaName());
    }
}
