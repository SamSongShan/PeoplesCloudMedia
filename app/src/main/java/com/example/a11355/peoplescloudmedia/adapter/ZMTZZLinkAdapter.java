package com.example.a11355.peoplescloudmedia.adapter;

import android.content.Context;
import android.view.View;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.AbsRecyclerViewAdapter;
import com.example.a11355.peoplescloudmedia.model.ZMTZZLink;

/**
 * Created by 11355 on 2017/11/27.
 */

public class ZMTZZLinkAdapter extends AbsRecyclerViewAdapter<ZMTZZLink> {
    private View.OnClickListener onClickListener;

    public ZMTZZLinkAdapter(Context context, View.OnClickListener onClickListener) {
        super(context, R.layout.item_zmtzz_link);
        this.onClickListener = onClickListener;
        this.context = context;
    }


    @Override
    public void onBindHolder(AbsRecyclerViewAdapter.RecyclerViewHolder holder, ZMTZZLink d, int position) {

            holder.bindTextView(R.id.tv_title, d.getLinkName())
                    .setClickListenerAndTag(R.id.img_close_item, onClickListener, position);

                if ("添加链接".equals(d.getLinkName())&&"def12354555568852225333".equals(d.getToLink())){
                    holder.getView(R.id.img_Add_item).setVisibility(View.VISIBLE);
                    holder.getView(R.id.img_close_item).setVisibility(View.GONE);
                } else {
                    holder.getView(R.id.img_Add_item).setVisibility(View.GONE);
                    holder.getView(R.id.img_close_item).setVisibility(View.VISIBLE);

                }
    }
    
}

