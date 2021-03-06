package com.example.a11355.peoplescloudmedia.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.activity.TXTEditorActivity;
import com.example.a11355.peoplescloudmedia.model.EContent;
import com.example.a11355.peoplescloudmedia.model.ItemType;
import com.example.a11355.peoplescloudmedia.util.Constant;

import java.util.List;

import io.valuesfeng.picker.Picker;
import io.valuesfeng.picker.engine.GlideEngine;


/**
 * item适配器
 * Created by HDL on 2017/3/14.
 */

public class RichEditorAdapter extends RecyclerView.Adapter<RichEditorAdapter.MyViewHolder> implements ItemTouchHelperAdapter {
    private List<EContent> datas;
    private Activity context;
    private String enCode;
    private int type;
    private static final int REQUEST_CODE_CHOOSE_ITEM_IMG = 1002;//更改item图片
    private static final int REQUEST_CODE_EDIT_TXT = 1005;//编辑文本
    private int curClickItemIndex = 0;//当前点击的item
    private OnDownUpChangeListener onDownUpChangeListener;
    private OnChoiseVideoListener onChoiseVideoListener;
    private OnItemClickListener onItemClickListener;


    public RichEditorAdapter(Activity context, List<EContent> datas, String enCode) {
        this.datas = datas;
        this.context = context;
        this.enCode = enCode;
    }

    public RichEditorAdapter(Activity context, List<EContent> datas, String enCode, int type) {
        this.datas = datas;
        this.context = context;
        this.enCode = enCode;
        this.type = type;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recycleview_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        if (type == 1) {
            holder.iv_additem_insert.setVisibility(View.GONE);
        } else {
            holder.iv_additem_insert.setVisibility(View.VISIBLE);

        }
        final EContent eContent = datas.get(position);
        holder.iv_item_video.setVisibility(View.GONE);

        //设置内容
        if ("default".equals(eContent.getTexts())|| TextUtils.isEmpty(eContent.getTexts())) {
            holder.tvDesc.setText(context.getString(R.string.rich_click_add_txt));
        } else {
            holder.tvDesc.setText(Html.fromHtml(eContent.getTexts()));
        }
        //holder.tvDesc.setText(TextUtils.isEmpty(eContent.getContent()) ? context.getString(R.string.rich_click_add_txt): eContent.getContent());
        /**
         * 根据类型显示item的图片
         */
        switch (eContent.getMediaType()) {
            case ItemType.IMG:
                if ("default".equals(eContent.getFilePath())|| TextUtils.isEmpty(eContent.getFilePath())) {
                    holder.ivPic.setImageResource(R.drawable.img);
                } else {

                    RequestOptions options = new RequestOptions()
                            .placeholder(R.color.ucrop_color_grey)
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL);

                    Glide.with(context)
                            .load(Constant.URL.BaseImg + eContent.getFilePath())
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .apply(options)
                            .into(holder.ivPic);

                  /*
                    Glide.with(context)
                            .load(eContent.getUrl())
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(R.drawable.img)
                            .error(R.drawable.img)
                            .into(holder.ivPic);*/
                }
                break;
            case ItemType.TXT:
                holder.ivPic.setImageResource(R.drawable.txt_item);
                break;
            case ItemType.VIDEO:

                if ("default".equals(eContent.getVideoImg())|| TextUtils.isEmpty(eContent.getVideoImg())) {
                    holder.ivPic.setImageResource(R.drawable.video_item);


                } else {
                    RequestOptions options = new RequestOptions()
                            .placeholder(R.color.ucrop_color_grey)
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL);
                    Glide.with(context)
                            .load(Constant.URL.BaseImg + eContent.getVideoImg())
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .apply(options)
                            .into(holder.ivPic);

                    holder.iv_item_video.setVisibility(View.VISIBLE);
                }
                break;
        }
        /**
         * 选择item图片
         */
        holder.ivPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eContent.getMediaType().equals(ItemType.IMG)) {
                    curClickItemIndex = position;
                    toChoiseItemPic();
                } else if (eContent.getMediaType().equals(ItemType.VIDEO)) {
                    curClickItemIndex = position;
                    toChoiseItemVideo();
                }

            }
        });
        /**
         * 编辑文本
         */
        holder.tvDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curClickItemIndex = position;
                toTxtEditorPage(position);
            }
        });
        /**
         * 添加item监听
         */
        holder.ivAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddItemArea(holder);
            }
        });
        /**
         * 设置添加图片、文本、视频的监听
         */
        holder.ivAddImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideAddArea(holder);
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(ItemType.IMG, position);
                }
            }
        });
        holder.ivAddVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideAddArea(holder);
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(ItemType.VIDEO, position);
                }
            }
        });
        holder.ivAddTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideAddArea(holder);
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(ItemType.TXT, position);
                }
            }
        });

        holder.iv_additem_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideAddArea(holder);
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(ItemType.AD, position);
                }
            }
        });


        /**
         * 设置向下向上箭头、删除的单击事件监听
         *
         */

        /**
         * 隐藏第一个item的上箭头和最后一个item的下箭头
         */

        holder.ivDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (position == datas.size() - 1) {
                }else {
                    onDownUpChangeListener.onDown(v, position);

                }

            }
        });
        holder.ivUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == 0) {
                }  else {
                    onDownUpChangeListener.onUp(v, position);

                }
            }
        });
        holder.ivDrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDownUpChangeListener.onDrop(v, position);
            }
        });
    }

    /**
     * 显示增加图片、文字、视频区域，隐藏添加按钮
     *
     * @param holder
     */
    private void showAddItemArea(MyViewHolder holder) {
        holder.ivAddItem.setVisibility(View.GONE);
        holder.rvAddItemArea.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏增加图片、文字、视频区域，显示添加按钮
     *
     * @param holder
     */
    private void hideAddArea(MyViewHolder holder) {
        holder.rvAddItemArea.setVisibility(View.GONE);
        holder.ivAddItem.setVisibility(View.VISIBLE);
    }

    /**
     * 跳转到文本编辑页面
     *
     * @param index
     */
    private void toTxtEditorPage(int index) {
        Intent intent = new Intent(context, TXTEditorActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("eContent", datas.get(index));
        bundle.putParcelable("eContent", datas.get(index));


        intent.putExtras(bundle);
        context.startActivityForResult(intent, REQUEST_CODE_EDIT_TXT);
    }

    /**
     * 更换item的图片
     */
    private void toChoiseItemPic() {
        Picker.from(context)
                .count(1)
                .enableCamera(true)
                .setEngine(new GlideEngine())
                .forResult(REQUEST_CODE_CHOOSE_ITEM_IMG, enCode);
    }

    /**
     * 获取item的高度
     *
     * @return
     */
    public int getItemHight(LinearLayoutManager linearLayoutManager) {
        return linearLayoutManager.getChildAt(0).getMeasuredHeight();
    }

    /**
     * 更换item的视频
     */
    private void toChoiseItemVideo() {
        onChoiseVideoListener.onStart();
    }


    /**
     * 设置向上向下箭头的监听事件
     *
     * @param onDownUpChangeListener
     */
    public void setOnDownUpChangeListener(OnDownUpChangeListener onDownUpChangeListener) {
        this.onDownUpChangeListener = onDownUpChangeListener;
    }

    /**
     * 设置item的单击事件
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 设置选择视频的监听
     *
     * @param onChoiseVideoListener
     */
    public void setOnChoiseVideoListener(OnChoiseVideoListener onChoiseVideoListener) {
        this.onChoiseVideoListener = onChoiseVideoListener;
    }

    @Override
    public void onItemMove(RecyclerView.ViewHolder source, RecyclerView.ViewHolder target) {
       /* int fromPosition = source.getAdapterPosition();
        int toPosition = target.getAdapterPosition();
        if (fromPosition < datas.size() && toPosition < datas.size()) {
            //交换数据位置
            Collections.swap(datas, fromPosition, toPosition);
            //刷新位置交换
            notifyItemMoved(fromPosition, toPosition);
        }
        //移动过程中移除view的放大效果
        onItemClear(source);*/
    }

    @Override
    public void onItemDissmiss(RecyclerView.ViewHolder source) {
    }

    @Override
    public void onItemSelect(RecyclerView.ViewHolder viewHolder) {
        //当拖拽选中时放大选中的view
        viewHolder.itemView.setScaleX(1.2f);
        viewHolder.itemView.setScaleY(1.2f);
    }

    @Override
    public void onItemClear(RecyclerView.ViewHolder viewHolder) {
        //拖拽结束后恢复view的状态
        viewHolder.itemView.setScaleX(1.0f);
        viewHolder.itemView.setScaleY(1.0f);

    }

    /**
     * 向上向下监听器对象
     */
    public interface OnDownUpChangeListener {
        void onDown(View view, int postion);

        void onUp(View view, int postion);

        void onDrop(View view, int postion);
    }

    /**
     * 选择视频
     */
    public interface OnChoiseVideoListener {
        void onStart();
    }

    public interface OnItemClickListener {
        void onClick(String itemType, int index);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    /**
     * 获取当前单击的item的下标
     *
     * @return
     */
    public int getCurClickItemIndex() {
        return curClickItemIndex;
    }

    /**
     * 创建viewholder类
     */
    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPic, ivUp, ivDown, ivDrop, ivAddItem, ivAddTxt, ivAddImg, ivAddVideo, iv_item_video,iv_additem_insert;
        TextView tvDesc;
        RelativeLayout rvItem;
        LinearLayout rvAddItemArea;

        public MyViewHolder(View itemView) {
            super(itemView);
            ivPic = (ImageView) itemView.findViewById(R.id.iv_item_pic);
            rvItem = (RelativeLayout) itemView.findViewById(R.id.rl_item);
            ivAddItem = (ImageView) itemView.findViewById(R.id.iv_additem_add);

            ivAddTxt = (ImageView) itemView.findViewById(R.id.iv_additem_txt);
            ivAddImg = (ImageView) itemView.findViewById(R.id.iv_additem_img);
            ivAddVideo = (ImageView) itemView.findViewById(R.id.iv_additem_video);
            ivUp = (ImageView) itemView.findViewById(R.id.iv_item_up);
            ivDown = (ImageView) itemView.findViewById(R.id.iv_item_down);
            ivDrop = (ImageView) itemView.findViewById(R.id.iv_item_delete);
            tvDesc = (TextView) itemView.findViewById(R.id.tv_item_desc);
            rvAddItemArea = (LinearLayout) itemView.findViewById(R.id.ll_additem_addarea);
            iv_item_video = (ImageView) itemView.findViewById(R.id.iv_item_video);
             iv_additem_insert = (ImageView) itemView.findViewById(R.id.iv_additem_insert);

        }
    }
}
