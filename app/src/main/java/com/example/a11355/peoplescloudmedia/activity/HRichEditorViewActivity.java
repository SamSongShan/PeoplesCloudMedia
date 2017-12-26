package com.example.a11355.peoplescloudmedia.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.adapter.RichEditorAdapter;
import com.example.a11355.peoplescloudmedia.adapter.SimpleItemTouchHelperCallback;
import com.example.a11355.peoplescloudmedia.base.BaseActivity;
import com.example.a11355.peoplescloudmedia.base.EditorResultBean;
import com.example.a11355.peoplescloudmedia.custom.SectorProgressBar;
import com.example.a11355.peoplescloudmedia.model.EContent;
import com.example.a11355.peoplescloudmedia.model.ItemType;
import com.example.a11355.peoplescloudmedia.util.LogUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.valuesfeng.picker.Picker;
import io.valuesfeng.picker.engine.GlideEngine;
import io.valuesfeng.picker.utils.PicturePickerUtils;



public class HRichEditorViewActivity extends BaseActivity {


    /**
     * 整形区
     */
    private static final int ANIMATION_DURATION = 300;//移动时间
    private static final int REQUEST_CODE_CHOOSE_BG = 1001;//选择背景
    private static final int REQUEST_CODE_CHOOSE_ITEM_IMG = 1002;//更改item图片
    private static final int REQUEST_CODE_SET_TITLE = 1003;
    private static final int REQUEST_CODE_CHOOSE_IMGS = 1004;//多选图片
    private static final int REQUEST_CODE_EDIT_TXT = 1005;//编辑文本
    private static final int REQUEST_CODE_SET_TITLE_MY = 1006;//设置标题

    @BindView(R.id.tv_addImg)
    TextView tvAddImg;
    @BindView(R.id.sdv)
    SimpleDraweeView sdv;
    @BindView(R.id.spb)
    SectorProgressBar spb;
    @BindView(R.id.tv_addMusic)
    TextView tvAddMusic;
    @BindView(R.id.tv_addedMusic)
    TextView tvAddedMusic;
    @BindView(R.id.img_change)
    ImageView imgChange;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.ll_additem_addarea)
    LinearLayout llAdditemAddarea;
    private float translateDistance = 0;//移动的距离
    /**
     * 字符区
     */
    private String articleTitle;

    /**
     * 组件区
     */
    private RecyclerView rvItemList;
    private LinearLayoutManager linearLayoutManager;
    private RichEditorAdapter adapter;
    private TextView tvArtTitle;
    private ImageView ivArtBGImg;

    /**
     * 数据区
     */
    private List<EContent> datas;
    private Uri bgUri;//背景图片的uri

  /*  *//**
     * 视频回调方法
     *//*
    private PictureConfig.OnSelectResultCallback videoResultCallback = new PictureConfig.OnSelectResultCallback() {
        @Override
        public void onSelectSuccess(List<LocalMedia> resultList) {
            datas.get(adapter.getCurClickItemIndex()).setUrl(resultList.get(0).toString());
            adapter.notifyDataSetChanged();
        }
    };*/


    @Override
    protected int getViewResId() {
        return R.layout.activity_hrich_editor_view;
    }

    @Override
    protected void init() {
        initView();
        //defaultChoiceIMG();
    }

    public void onSubmit(View view) {
        EditorResultBean resultBean = new EditorResultBean();
        resultBean.setContents(datas);
        String html = "";
        for (EContent data : datas) {
            html += data.getHtml();
        }
        LogUtils.e("mylog", html);
        Intent intent = getIntent();
        intent.putExtra("contents", resultBean);
        this.setResult(Activity.RESULT_OK, intent);
        this.finish();
    }

    /**
     * 第一次进入页面，选择图片最多20张
     */
    private void defaultChoiceIMG() {
        Picker.from(this)
                .count(1)
                .enableCamera(true)
                .setEngine(new GlideEngine())
                .forResult(REQUEST_CODE_CHOOSE_IMGS);
    }


    /**
     * 初始化视图
     */
    private void initView() {
        /**
         * 初始化RecyclerView
         */
        rvItemList = (RecyclerView) findViewById(R.id.rv_itemlist);

        linearLayoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {//禁止滑动
                return false;
            }
        };
        rvItemList.setLayoutManager(linearLayoutManager);

        rvItemList.setItemAnimator(new DefaultItemAnimator());
        datas = new ArrayList<>();

//        rvItemList.setHasFixedSize(true);//最重要的这句，不然recycleview不显示
        adapter = new RichEditorAdapter(this, datas);
        rvItemList.setAdapter(adapter);

        adapter.setOnDownUpChangeListener(new RichEditorAdapter.OnDownUpChangeListener() {
            @Override
            public void onDown(final View view, int postion) {
                swapDown(postion);
            }

            @Override
            public void onUp(View view, int postion) {
                swapUp(postion);
            }

            @Override
            public void onDrop(View view, int postion) {
                dropItem(postion);
            }
        });
        adapter.setOnChoiseVideoListener(new RichEditorAdapter.OnChoiseVideoListener() {
            @Override
            public void onStart() {
                getVideo();
            }
        });
        adapter.setOnItemClickListener(new RichEditorAdapter.OnItemClickListener() {
            @Override
            public void onClick(String itemType, int index) {
                EContent eContent = new EContent();
                switch (itemType) {
                    case ItemType.IMG:
                        eContent.setType(ItemType.IMG);
                        break;
                    case ItemType.VIDEO:
                        eContent.setType(ItemType.VIDEO);
                        break;
                    case ItemType.TXT:
                        eContent.setType(ItemType.TXT);
                        break;
                }
                datas.add(index, eContent);
                adapter.notifyDataSetChanged();
            }
        });

        //创建SimpleItemTouchHelperCallback
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter, rvItemList);
        //用Callback构造ItemtouchHelper
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        //调用ItemTouchHelper的attachToRecyclerView方法建立联系
        touchHelper.attachToRecyclerView(rvItemList);

        /**
         * 初始化文章标题
         */
        tvArtTitle = (TextView) findViewById(R.id.tv_richeditor_title);
        /**
         * 初始化封面图片
         */
        ivArtBGImg = (ImageView) findViewById(R.id.iv_richeditor_bg);


    }


    /**
     * 删除item
     *
     * @param postion
     */
    private void dropItem(int postion) {
        datas.remove(postion);
        adapter.notifyDataSetChanged();

        if (datas.size() == 0) {
            llAdditemAddarea.setVisibility(View.VISIBLE);

        }
    }


    /**
     * 向下交换
     *
     * @param postion
     */
    private void swapDown(final int postion) {

        if (datas.size() == 1) {
            return;
        }
        if (translateDistance == 0) {
            translateDistance = adapter.getItemHight(linearLayoutManager) + 10;
        }
        ObjectAnimator animatorDown = ObjectAnimator.ofFloat(linearLayoutManager.getChildAt(postion), "TranslationY", 0, translateDistance);
        animatorDown.setDuration(ANIMATION_DURATION);
        animatorDown.start();
        animatorDown.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                if (animatedValue == translateDistance) {
                    Collections.swap(datas, postion, postion + 1);
                    adapter.notifyDataSetChanged();
                    rvItemList.setAdapter(adapter);//需要重新设置适配器才有效果
                }
            }
        });
        ObjectAnimator animatorUp = ObjectAnimator.ofFloat(linearLayoutManager.getChildAt(postion + 1), "TranslationY", 0, -translateDistance);
        animatorUp.setDuration(ANIMATION_DURATION);
        animatorUp.start();
    }

    /**
     * 向上交换
     *
     * @param postion
     */
    private void swapUp(final int postion) {
        if (translateDistance == 0) {
            translateDistance = adapter.getItemHight(linearLayoutManager) + 10;
        }
        ObjectAnimator animatorUp = ObjectAnimator.ofFloat(linearLayoutManager.getChildAt(postion), "TranslationY", 0, -translateDistance);
        animatorUp.setDuration(ANIMATION_DURATION);
        animatorUp.start();
        animatorUp.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                if (animatedValue == -translateDistance) {
                    Collections.swap(datas, postion, postion - 1);
                    adapter.notifyDataSetChanged();
                    rvItemList.setAdapter(adapter);
                }
            }
        });
        ObjectAnimator animatorDown = ObjectAnimator.ofFloat(linearLayoutManager.getChildAt(postion - 1), "TranslationY", 0, translateDistance);
        animatorDown.setDuration(ANIMATION_DURATION);
        animatorDown.start();
    }

    /**
     * 更换背景
     *
     * @param view
     */
    public void onChangeBG(View view) {
        Picker.from(this)
                .count(1)
                .enableCamera(true)
                .setEngine(new GlideEngine())
                .forResult(REQUEST_CODE_CHOOSE_BG);
    }

    /**
     * 获取视频
     */
    private void getVideo() {
        /*FunctionConfig config = new FunctionConfig();
        config.setType(LocalMediaLoader.TYPE_VIDEO);
        config.setCompress(true);
        config.setMaxSelectNum(1);
        config.setSelectMode(MODE_MULTIPLE);
        config.setShowCamera(true);
        config.setEnablePreview(true);
        config.setPreviewVideo(true);
        config.setRecordVideoSecond(60 * 60);// 视频秒数
        config.setCompressFlag(1);
        config.setCheckNumMode(true);
        PictureConfig.init(config);
        PictureConfig.getPictureConfig().openPhoto(this, videoResultCallback);*/

        // 进入相册 以下是例子：不需要的api可以不写
        PictureSelector.create(HRichEditorViewActivity.this)
                .openGallery(PictureMimeType.ofVideo())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                .maxSelectNum(1)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(4)// 每行显示个数
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                .previewImage(true)// 是否可预览图片
                .previewVideo(true)// 是否可预览视频
                .enablePreviewAudio(true) // 是否可播放音频
                .isCamera(true)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                .enableCrop(true)// 是否裁剪
                .compress(false)// 是否压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                //.compressSavePath(getPath())//压缩图片保存地址
                //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .withAspectRatio(16, 9)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示
                .isGif(false)// 是否显示gif图片
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .circleDimmedLayer(false)// 是否圆形裁剪
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .openClickSound(false)// 是否开启点击声音
                //.selectionMedia(selectList)// 是否传入已选图片
//                        .videoMaxSecond(15)
//                        .videoMinSecond(10)
                //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                //.cropCompressQuality(90)// 裁剪压缩质量 默认100
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                //.rotateEnabled() // 裁剪是否可旋转图片
                //.scaleEnabled()// 裁剪是否可放大缩小图片
                //.videoQuality()// 视频录制质量 0 or 1
                //.videoSecond()//显示多少秒以内的视频or音频也可适用
                //.recordVideoSecond()//录制视频秒数 默认60s
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    public void onBack(View view) {
        this.finish();
    }

    /**
     * 设置标题
     *
     * @param view
     */
    public void onSetTitle(View view) {
        startActivityForResult(new Intent(this, TitleEidtorActivity.class).putExtra("title", TextUtils.isEmpty(articleTitle) ? "" : articleTitle), REQUEST_CODE_SET_TITLE);
    }

    /**
     * 页面跳转回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SET_TITLE && resultCode == REQUEST_CODE_SET_TITLE) {//设置标题回调
            articleTitle = data.getStringExtra("title");//记录文章标题
            tvArtTitle.setText(articleTitle);
        } else if (requestCode == REQUEST_CODE_CHOOSE_BG && resultCode == RESULT_OK) {//选择背景
            bgUri = PicturePickerUtils.obtainResult(data).get(0);
            tvAddImg.setVisibility(View.GONE);
            sdv.setVisibility(View.VISIBLE);
            imgChange.setVisibility(View.VISIBLE);
            sdv.setImageURI(bgUri);


            /*Glide.with(this)
                    .load(bgUri)
                    .placeholder(R.drawable.default_adv)
                    .error(R.drawable.default_adv)
                    .into(sdv);*/

        } else if (requestCode == REQUEST_CODE_CHOOSE_ITEM_IMG && resultCode == RESULT_OK) {//选择item的图片
            datas.get(adapter.getCurClickItemIndex()).setUrl(PicturePickerUtils.obtainResult(data).get(0).toString());
            adapter.notifyDataSetChanged();
        } else if (requestCode == REQUEST_CODE_EDIT_TXT && resultCode == REQUEST_CODE_EDIT_TXT) {//编辑文字
            EContent eContent = (EContent) data.getSerializableExtra("eContent");
            datas.get(adapter.getCurClickItemIndex()).setContent(eContent.getContent());
            datas.get(adapter.getCurClickItemIndex()).setStyle(eContent.getStyle());
            adapter.notifyDataSetChanged();
        } else if (requestCode == REQUEST_CODE_CHOOSE_IMGS && resultCode == RESULT_OK) {//第一次进入页面需要选择图片
            llAdditemAddarea.setVisibility(View.GONE);
            llContent.setVisibility(View.GONE);

            List<Uri> uris = PicturePickerUtils.obtainResult(data);
            if (uris != null && uris.size() > 0) {
                EContent eContent;
                for (Uri uri : uris) {
                    eContent = new EContent();
                    eContent.setUrl(uri.toString());
                    eContent.setType(ItemType.IMG);
                    datas.add(eContent);
                }
                adapter.notifyDataSetChanged();
            }

        } else if (requestCode == REQUEST_CODE_SET_TITLE_MY && resultCode == RESULT_OK) { //设置标题
            tvTitle.setText(data.getStringExtra("title"));
        }
    }

    @OnClick({R.id.tv_addImg, R.id.sdv, R.id.tv_addMusic, R.id.tv_addedMusic, R.id.img_change, R.id.tv_title, R.id.tv_content, R.id.iv_additem_txt, R.id.iv_additem_img, R.id.iv_additem_video, R.id.iv_additem_insert})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_addImg:
                onChangeBG(view);
                break;
            case R.id.sdv:
                break;
            case R.id.tv_addMusic:
                break;
            case R.id.tv_addedMusic:
                break;
            case R.id.img_change:
                onChangeBG(view);
                break;
            case R.id.tv_title:

                startActivityForResult(new Intent(this, EditTitleActivity.class), REQUEST_CODE_SET_TITLE_MY);
                break;
            case R.id.tv_content:
                break;
            case R.id.iv_additem_txt:
                addTextType();
                break;
            case R.id.iv_additem_img:
                defaultChoiceIMG();
                break;
            case R.id.iv_additem_video:
                getVideo();
                break;
            case R.id.iv_additem_insert:
                break;
        }
    }

    //新建一个text类型的东西
    private void addTextType() {
        EContent eContent = new EContent();

        eContent.setType(ItemType.TXT);
        datas.add(0, eContent);
        adapter.notifyDataSetChanged();
    }

}

