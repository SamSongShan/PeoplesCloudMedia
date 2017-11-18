package com.example.a11355.peoplescloudmedia.adapter;

import android.content.Context;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.AbsRecyclerViewAdapter;


/**
 * Created by 11355 on 2017/11/14.
 */

public class MineRVAdapter extends AbsRecyclerViewAdapter<String> {
    public MineRVAdapter(Context context) {
        super(context, R.layout.recyeler_mine);
    }

    @Override
    public void onBindHolder(AbsRecyclerViewAdapter.RecyclerViewHolder holder, String d, int position) {
        holder.bindTextView(R.id.tv_name, d);
    }
}
