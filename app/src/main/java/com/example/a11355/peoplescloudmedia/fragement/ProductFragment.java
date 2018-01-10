package com.example.a11355.peoplescloudmedia.fragement;


import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.activity.EditTitleActivity;
import com.example.a11355.peoplescloudmedia.activity.GetMusicListActivity;
import com.example.a11355.peoplescloudmedia.activity.H5ActivityForZMTZZPreview;
import com.example.a11355.peoplescloudmedia.activity.LoginActivity;
import com.example.a11355.peoplescloudmedia.activity.ProdectEditActivity;
import com.example.a11355.peoplescloudmedia.activity.TitleEidtorActivity;
import com.example.a11355.peoplescloudmedia.activity.ZMTZZAddLinkActivity;
import com.example.a11355.peoplescloudmedia.adapter.RichEditorAdapter;
import com.example.a11355.peoplescloudmedia.adapter.SimpleItemTouchHelperCallback;
import com.example.a11355.peoplescloudmedia.adapter.ZMTZZLinkAdapter;
import com.example.a11355.peoplescloudmedia.base.AbsRecyclerViewAdapter;
import com.example.a11355.peoplescloudmedia.base.BaseFragment;
import com.example.a11355.peoplescloudmedia.base.EditorResultBean;
import com.example.a11355.peoplescloudmedia.custom.GridDividerItemDecoration;
import com.example.a11355.peoplescloudmedia.custom.LoadingDialog;
import com.example.a11355.peoplescloudmedia.model.EContent;
import com.example.a11355.peoplescloudmedia.model.GetEntityUser;
import com.example.a11355.peoplescloudmedia.model.GetEntityUserEntity;
import com.example.a11355.peoplescloudmedia.model.GetUserProductInfo;
import com.example.a11355.peoplescloudmedia.model.GetUserProductInfoEntity;
import com.example.a11355.peoplescloudmedia.model.ItemType;
import com.example.a11355.peoplescloudmedia.model.UpdateUserProductEntity;
import com.example.a11355.peoplescloudmedia.model.ZMTZZLink;
import com.example.a11355.peoplescloudmedia.util.Constant;
import com.example.a11355.peoplescloudmedia.util.DesUtil;
import com.example.a11355.peoplescloudmedia.util.LogUtils;
import com.example.a11355.peoplescloudmedia.util.OkHttpUtil;
import com.example.a11355.peoplescloudmedia.util.PreferencesUtil;
import com.example.a11355.peoplescloudmedia.util.ToastUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.valuesfeng.picker.Picker;
import io.valuesfeng.picker.engine.GlideEngine;
import io.valuesfeng.picker.model.UploadImgEntity;
import io.valuesfeng.picker.utils.PicturePickerUtils;

import static android.app.Activity.RESULT_OK;
import static com.example.a11355.peoplescloudmedia.custom.GridDividerItemDecoration.ATTRS_B;

/**
 * 自媒体制作   产品
 */
public class ProductFragment extends BaseFragment implements OkHttpUtil.OnDataListener, View.OnClickListener, AbsRecyclerViewAdapter.OnItemClickListener {
    private static final int ANIMATION_DURATION = 300;//移动时间
    private static final int REQUEST_CODE_CHOOSE_BG = 1001;//选择背景
    private static final int REQUEST_CODE_CHOOSE_ITEM_IMG = 1002;//更改item图片
    private static final int REQUEST_CODE_SET_TITLE = 1003;
    private static final int REQUEST_CODE_CHOOSE_IMGS = 1004;//多选图片
    private static final int REQUEST_CODE_EDIT_TXT = 1005;//编辑文本
    private static final int REQUEST_CODE_SET_TITLE_MY = 1006;//设置标题
    private static final int REQUEST_CODE_SET_Music = 1007;//设置标题


    @BindView(R.id.tv_addmsg)
    TextView tvAddMsg;

    @BindView(R.id.tv_addMusic)
    TextView tvAddMusic;

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.ll_additem_addarea)
    LinearLayout llAdditemAddarea;

    @BindView(R.id.iv_additem_txt)
    ImageView ivAdditemTxt;
    @BindView(R.id.iv_additem_img)
    ImageView ivAdditemImg;
    @BindView(R.id.iv_additem_video)
    ImageView ivAdditemVideo;
    @BindView(R.id.iv_additem_insert)
    ImageView ivAdditemInsert;
    @BindView(R.id.rv_itemlist)
    RecyclerView rvItemlist;
    @BindView(R.id.rv_link)
    RecyclerView rvLink;
    @BindView(R.id.sdv)
    SimpleDraweeView sdv;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    @BindView(R.id.rl_show)
    RelativeLayout rlShow;
    Unbinder unbinder;


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

    private Gson gson = new GsonBuilder().create();

    /**
     * 数据区
     */
    private List<EContent> datas = new ArrayList<>();
    ;
    private Uri bgUri;//背景图片的uri
    private String enCode = "";
    private String GraphicEditorId = "default";//:图文编辑实体主键（默认传default）
    private String ImageUrl = "";//:封面（默认传default）
    private String IsUseMusic = "0";//:是否使用音乐（默认传0,不使用）
    private String MusicUrl = "default";//:音乐路径（默认传default）
    private String MusicName = "default";//:音乐名称（默认传default）
    private String Title = "";//:标题
    private LoadingDialog loadingDialog;
    private String ProductImg;
    private String ProductName;
    private String ProductPrice;
    private String Description;
    private String MusicPath;
    private ZMTZZLinkAdapter zmtzzLinkAdapter;
    private List<ZMTZZLink> zmtzzLinks = new ArrayList<>();

    private List<EContent> datasCopy = new ArrayList<>();
    private List<ZMTZZLink> zmtzzLinksCopy = new ArrayList<>();

    private boolean isShowSave = true;
    private ShowSave showSave;
    private GetUserProductInfoEntity getUserProductInfoEntity;

  /*  */

    /**
     * 视频回调方法
     *//*
    private PictureConfig.OnSelectResultCallback videoResultCallback = new PictureConfig.OnSelectResultCallback() {
        @Override
        public void onSelectSuccess(List<LocalMedia> resultList) {
            datas.get(adapter.getCurClickItemIndex()).setUrl(resultList.get(0).toString());
            adapter.notifyDataSetChanged();
        }
    };*/
    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getViewResId() {
        return R.layout.fragment_product;
    }

    @Override
    protected void init(View v) {
       /* ToolBarUtil.initToolBar(toolbarText, "图文编辑", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        }, "完成", new View.OnClickListener() {   //完成
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(ImageUrl)) {
                    ToastUtil.initToast(getContext(), "请选择封面图");
                } else if (TextUtils.isEmpty(Title)) {
                    ToastUtil.initToast(getContext(), "请填写标题");

                } else if (datas.size() == 0) {
                    ToastUtil.initToast(getContext(), "请编辑正文");

                } else {
                    //更新网络数据
                    upData();
                }

            }
        });*/

        GetEntityUserEntity.DataBean userInfo = PreferencesUtil.getUserInfo(getContext());
        if (userInfo == null) {
            String jsonUser = gson.toJson(new GetEntityUser(PreferencesUtil.getToken(getContext()), PreferencesUtil.getUserId(getContext())));
            OkHttpUtil.postJson(Constant.URL.GetEntityUser, DesUtil.encrypt(jsonUser), this);
        } else {
            enCode = userInfo.getEnCode();

        }
        rvLink.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rvLink.addItemDecoration(new GridDividerItemDecoration(getContext(), ATTRS_B));

        zmtzzLinkAdapter = new ZMTZZLinkAdapter(getContext(), this);
        rvLink.setAdapter(zmtzzLinkAdapter);


        zmtzzLinkAdapter.setData(zmtzzLinks);
        zmtzzLinkAdapter.setOnItemClickListener(this);
        initView(v);
        //defaultChoiceIMG();
    }

    //更新网络数据
    public void upData() {


        if (TextUtils.isEmpty(ProductImg)) {
            ToastUtil.initToast(getContext(), "请添加产品图片");
        } else if (TextUtils.isEmpty(ProductName)) {
            ToastUtil.initToast(getContext(), "请添加产品名称");
        } else if (TextUtils.isEmpty(ProductPrice)) {
            ToastUtil.initToast(getContext(), "请添加产品价格");
        } else if (TextUtils.isEmpty(Description)) {
            ToastUtil.initToast(getContext(), "请添加产品描述");
        } else if (TextUtils.isEmpty(Title)) {
            ToastUtil.initToast(getContext(), "请添加产品标题");
        } else if (datas.size() == 0) {
            ToastUtil.initToast(getContext(), "请编辑正文");
        } else if (zmtzzLinks.size() == 0) {
            ToastUtil.initToast(getContext(), "请编辑链接");
        } else {
            loadingDialog = LoadingDialog.newInstance("提交中...");
            loadingDialog.show(getActivity().getFragmentManager());
            datasCopy = datas;
            zmtzzLinksCopy = zmtzzLinks;
            UpdateUserProductEntity updateGraphicEditor = new UpdateUserProductEntity(PreferencesUtil.getUserId(getContext()), PreferencesUtil.getToken(getContext()), ProductImg, ProductName, ProductPrice, Description, IsUseMusic, MusicPath, MusicName, Title, datasCopy, zmtzzLinksCopy);
            OkHttpUtil.postJson(Constant.URL.UpdateUserProductEntity, DesUtil.encrypt(gson.toJson(updateGraphicEditor)), this);
        }


    }

    public void onSubmit(View view) {
        EditorResultBean resultBean = new EditorResultBean();
        resultBean.setContents(datas);


        Intent intent = new Intent();
        intent.putExtra("contents", resultBean);
        getActivity().setResult(RESULT_OK, intent);
        getActivity().onBackPressed();
    }

    /**
     * 第一次进入页面，选择图片最多20张
     */
    private void defaultChoiceIMG() {
        Picker.from(this)
                .count(1)
                .enableCamera(true)
                .setEngine(new GlideEngine())
                .forResult(REQUEST_CODE_CHOOSE_IMGS, enCode);
    }


    /**
     * 初始化视图
     */
    private void initView(View v) {
        /**
         * 初始化RecyclerView
         */
        rvItemList = (RecyclerView) v.findViewById(R.id.rv_itemlist);

        linearLayoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {//禁止滑动
                return false;
            }
        };
        rvItemList.setLayoutManager(linearLayoutManager);

        rvItemList.setItemAnimator(new DefaultItemAnimator());


//        rvItemList.setHasFixedSize(true);//最重要的这句，不然recycleview不显示
        adapter = new RichEditorAdapter(getActivity(), datas, enCode, 1);
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
                eContent.setMediaBlockId("default");
                eContent.setFilePath("default");
                eContent.setTexts("default");
                eContent.setVideoImg("default");
                eContent.setSortCode("1");
                eContent.setIsDelete("0");
                switch (itemType) {
                    case ItemType.IMG:
                        eContent.setMediaType(ItemType.IMG);
                        break;
                    case ItemType.VIDEO:
                        eContent.setMediaType(ItemType.VIDEO);
                        break;
                    case ItemType.TXT:
                        eContent.setMediaType(ItemType.TXT);
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
        tvArtTitle = (TextView) v.findViewById(R.id.tv_richeditor_title);
        /**
         * 初始化封面图片
         */
        ivArtBGImg = (ImageView) v.findViewById(R.id.iv_richeditor_bg);


    }


    /**
     * 删除item
     *
     * @param postion
     */
    private void dropItem(int postion) {
        datas.remove(postion);
        adapter.notifyDataSetChanged();

        isShowSave = true;

        isShowSave();
        if (datas.size() == 0) {
            llAdditemAddarea.setVisibility(View.VISIBLE);
            llContent.setVisibility(View.VISIBLE);
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
        for (int i = 0; i < datas.size(); i++) {
            if (!datasCopy.get(i).getMediaBlockId().equals(datas.get(i).getMediaBlockId())) {
                isShowSave = false;
                isShowSave();
                return;

            } else {
                isShowSave = true;
                isShowSave();
            }
        }

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
        for (int i = 0; i < datas.size(); i++) {
            if (!datasCopy.get(i).getMediaBlockId().equals(datas.get(i).getMediaBlockId())) {
                isShowSave = false;
                isShowSave();
                return;

            } else {
                isShowSave = true;
                isShowSave();
            }
        }
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
                .forResult(REQUEST_CODE_CHOOSE_BG, enCode);
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
        PictureSelector.create(getActivity())
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
                .forResult(PictureConfig.CHOOSE_REQUEST, enCode);//结果回调onActivityResult code
    }

    public void onBack(View view) {
        getActivity().onBackPressed();
    }

    /**
     * 设置标题
     *
     * @param view
     */
    public void onSetTitle(View view) {
        startActivityForResult(new Intent(getContext(), TitleEidtorActivity.class).putExtra("title", TextUtils.isEmpty(articleTitle) ? "" : articleTitle), REQUEST_CODE_SET_TITLE);
    }

    /**
     * 页面跳转回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SET_TITLE && resultCode == REQUEST_CODE_SET_TITLE) {//设置标题回调
            articleTitle = data.getStringExtra("title");//记录文章标题
            tvArtTitle.setText(articleTitle);
            Title = articleTitle;//文章记录赋值
            isShowSave = true;
            isShowSave();
        } else if (requestCode == REQUEST_CODE_CHOOSE_BG && resultCode == RESULT_OK) {//选择背景
            bgUri = PicturePickerUtils.obtainResult(data).get(0);
            tvAddMsg.setVisibility(View.GONE);

            ImageUrl = data.getStringExtra("photoUrl");

            /*Glide.with(this)
                    .load(bgUri)
                    .placeholder(R.drawable.default_adv)
                    .error(R.drawable.default_adv)
                    .into(sdv);*/
            isShowSave = true;
            isShowSave();
        } else if (requestCode == REQUEST_CODE_CHOOSE_ITEM_IMG && resultCode == RESULT_OK) {//选择item的图片
            datas.get(adapter.getCurClickItemIndex()).setFilePath(data.getStringExtra("photoUrl"));
            adapter.notifyDataSetChanged();
            isShowSave = true;
            isShowSave();
        } else if (requestCode == REQUEST_CODE_EDIT_TXT && resultCode == REQUEST_CODE_EDIT_TXT) {//编辑文字
            EContent eContent = (EContent) data.getSerializableExtra("eContent");
            datas.get(adapter.getCurClickItemIndex()).setTexts(eContent.getTexts());
            adapter.notifyDataSetChanged();
            isShowSave = true;
            isShowSave();
        } else if (requestCode == REQUEST_CODE_CHOOSE_IMGS && resultCode == RESULT_OK) {//第一次进入页面需要选择图片
            llAdditemAddarea.setVisibility(View.GONE);
            llContent.setVisibility(View.GONE);

            List<Uri> uris = PicturePickerUtils.obtainResult(data);
            if (uris != null && uris.size() > 0) {
                EContent eContent;
                for (Uri uri : uris) {
                    eContent = new EContent();
                    eContent.setFilePath(uri.toString());
                    eContent.setMediaBlockId(ItemType.IMG);
                    datas.add(eContent);
                }
                isShowSave = true;
                isShowSave();
                adapter.notifyDataSetChanged();
            }

        } else if (requestCode == REQUEST_CODE_SET_TITLE_MY && resultCode == RESULT_OK) { //设置标题
            tvTitle.setText(data.getStringExtra("title"));
            Title = data.getStringExtra("title");//文章记录赋值
            isShowSave = true;
            isShowSave();
        } else if (requestCode == REQUEST_CODE_SET_Music && resultCode == RESULT_OK) {  //音乐
            MusicPath = data.getStringExtra("path");
            MusicName = data.getStringExtra("singer");
            IsUseMusic = "0";
            tvAddMusic.setText(MusicName);
            isShowSave = true;
            isShowSave();

        } else if (requestCode == PictureConfig.CHOOSE_REQUEST && resultCode == RESULT_OK) {//视频


            datas.get(adapter.getCurClickItemIndex()).setFilePath(data.getStringExtra("VideoUrl"));
            datas.get(adapter.getCurClickItemIndex()).setVideoImg(data.getStringExtra("photoUrl"));
            adapter.notifyDataSetChanged();
            isShowSave = true;
            isShowSave();
        } else if (requestCode == Constant.Code.ZMTZZ_prodect && resultCode == RESULT_OK) {  //产品信息


            ProductImg = data.getStringExtra("imgUrl");
            ProductName = data.getStringExtra("name");
            ProductPrice = data.getStringExtra("price");
            Description = data.getStringExtra("description");

            tvAddMsg.setVisibility(View.GONE);
            rlShow.setVisibility(View.VISIBLE);

            sdv.setImageURI(Constant.URL.BaseImg + ProductImg);
            tvName.setText(ProductName);
            tvPrice.setText(ProductPrice);
            tvDescription.setText(Description);
            isShowSave = true;
            isShowSave();
        } else if (requestCode == Constant.Code.Link && resultCode == RESULT_OK) {  //网址信息


            zmtzzLinks.add(0, new ZMTZZLink(data.getStringExtra("linkName"), data.getStringExtra("link")));
            zmtzzLinkAdapter.setData(zmtzzLinks);
            isShowSave = true;
            isShowSave();
        }


    }


    @OnClick({R.id.tv_addLink, R.id.rl, R.id.tv_addmsg, R.id.tv_addMusic, R.id.tv_title, R.id.tv_content, R.id.iv_additem_txt, R.id.iv_additem_img, R.id.iv_additem_video, R.id.iv_additem_insert})
    public void onViewClicked(View view) {
        if (TextUtils.isEmpty(enCode)) {
            ToastUtil.initToast(getContext(), "数据加载中");
            return;
        }
        switch (view.getId()) {
            case R.id.tv_addmsg:
            case R.id.rl:
                Intent intent = new Intent(getContext(), ProdectEditActivity.class);
                if (getUserProductInfoEntity!=null){
                    intent.putExtra("data",getUserProductInfoEntity.getData());

                }

                startActivityForResult(intent, Constant.Code.ZMTZZ_prodect);
                //onChangeBG(view);
                break;

            case R.id.tv_addMusic:

                startActivityForResult(new Intent(getContext(), GetMusicListActivity.class), REQUEST_CODE_SET_Music);
                break;

            case R.id.tv_title:

                startActivityForResult(new Intent(getContext(), EditTitleActivity.class), REQUEST_CODE_SET_TITLE_MY);
                break;
            case R.id.tv_content:
                break;
            case R.id.iv_additem_txt:
                addTextType();
                llAdditemAddarea.setVisibility(View.GONE);
                llContent.setVisibility(View.GONE);
                break;
            case R.id.iv_additem_img:
                addImgType();
                llAdditemAddarea.setVisibility(View.GONE);
                llContent.setVisibility(View.GONE);
                //defaultChoiceIMG();
                break;
            case R.id.iv_additem_video:
                addVideoType();
                llAdditemAddarea.setVisibility(View.GONE);
                llContent.setVisibility(View.GONE);
                break;
            case R.id.iv_additem_insert:
                break;
            case R.id.tv_addLink: {

            }
            break;
        }
    }

    //新建一个IMG类型的东西
    private void addImgType() {
        EContent eContent = new EContent();
        eContent.setMediaBlockId("default");
        eContent.setFilePath("default");
        eContent.setTexts("default");
        eContent.setVideoImg("default");
        eContent.setSortCode("1");
        eContent.setIsDelete("0");
        eContent.setMediaType(ItemType.IMG);
        datas.add(0, eContent);
        adapter.notifyDataSetChanged();
    }

    //新建一个VIDEO类型的东西
    private void addVideoType() {
        EContent eContent = new EContent();
        eContent.setMediaBlockId("default");
        eContent.setFilePath("default");
        eContent.setTexts("default");
        eContent.setVideoImg("default");
        eContent.setSortCode("1");
        eContent.setIsDelete("0");
        eContent.setMediaType(ItemType.VIDEO);
        datas.add(0, eContent);
        adapter.notifyDataSetChanged();
    }

    //新建一个text类型的东西
    private void addTextType() {
        EContent eContent = new EContent();
        eContent.setMediaBlockId("default");
        eContent.setFilePath("default");
        eContent.setTexts("default");
        eContent.setVideoImg("default");
        eContent.setSortCode("1");
        eContent.setIsDelete("0");

        eContent.setMediaType(ItemType.TXT);
        datas.add(0, eContent);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onResponse(String url, String json) {
        if (!TextUtils.isEmpty(json)) {
            String decrypt = DesUtil.decrypt(json);
            switch (url) {

                case Constant.URL.GetEntityUser: { //个人信息
                    LogUtils.e("loge", "GetEntityUser: " + decrypt);
                    GetEntityUserEntity getEntityUserEntity = gson.fromJson(decrypt, GetEntityUserEntity.class);

                    if (getEntityUserEntity.getCode() == Constant.Integers.SUC) { //成功
                        PreferencesUtil.saveUserInfo(getContext(), DesUtil.encrypt(decrypt, DesUtil.LOCAL_KEY));
                        enCode = getEntityUserEntity.getData().getEnCode();

                    } else if (getEntityUserEntity.getCode() == Constant.Integers.TOKEN_OUT_OF) { //token过期
                        ToastUtil.initToast(getContext(), getEntityUserEntity.getMessage());
                        startActivityForResult(new Intent(getContext(), LoginActivity.class), Constant.Code.LoginCode);

                    } else {//其他
                        ToastUtil.initToast(getContext(), getEntityUserEntity.getMessage());
                    }

                }
                break;
                case Constant.URL.UpdateUserProductEntity: {//提交
                    LogUtils.e("loge", "UpdateGraphicEditor: " + decrypt);
                    dismissLoading();
                    LogUtils.e("UploadImg", decrypt);
                    UploadImgEntity img = new Gson().fromJson(decrypt, UploadImgEntity.class);
                    Toast.makeText(getContext(), img.getMessage(), Toast.LENGTH_LONG).show();
                    if (img.getCode() == Constant.Integers.SUC) {
                        getActivity().onBackPressed();

                    } else {
                    }


                }
                break;
                case Constant.URL.GetUserProductInfo://获取产品信息
                    dismissLoading();
                    LogUtils.e("GetUserProductInfo", decrypt);
                    getUserProductInfoEntity = gson.fromJson(decrypt, GetUserProductInfoEntity.class);
                    if (getUserProductInfoEntity.getCode() == Constant.Integers.SUC) {
                        datasCopy.clear();
                        datas.clear();
                        zmtzzLinksCopy.clear();
                        zmtzzLinks.clear();


                        isShowSave = false;
                        showSave.showSave(isShowSave);

                        tvAddMsg.setVisibility(View.GONE);
                        rlShow.setVisibility(View.VISIBLE);
                        GetUserProductInfoEntity.DataEntity data = getUserProductInfoEntity.getData();
                        sdv.setImageURI(Constant.URL.BaseImg + data.getProductImg());
                        tvName.setText(data.getProductName());
                        tvPrice.setText(data.getProductPrice());
                        tvDescription.setText(data.getDescription());
                        tvTitle.setText(data.getTitle());
                        llContent.setVisibility(View.GONE);
                        llAdditemAddarea.setVisibility(View.GONE);

                        for (int i = 0; i < data.getAttachLinkList().size(); i++) {
                            zmtzzLinks.add(new ZMTZZLink(data.getAttachLinkList().get(i)));
                        }
                        zmtzzLinkAdapter.setData(zmtzzLinks);
                        for (int i = 0; i < data.getMediaBlockList().size(); i++) {
                            datas.add(new EContent(data.getMediaBlockList().get(i)));

                        }
                        adapter.notifyDataSetChanged();
                        datasCopy.addAll(datas) ;
                        zmtzzLinksCopy.addAll(zmtzzLinks);
                        if ("0".equals(data.getIsUseMusic())) {
                            tvAddMusic.setText(data.getMusicName());

                        }

                        ProductImg=data.getProductImg();
                                ProductName=data.getProductName();
                                ProductPrice=data.getProductPrice();
                                Description=data.getDescription();
                                IsUseMusic=data.getIsUseMusic();
                                MusicPath=data.getMusicPath();
                                MusicName=data.getMusicName();
                                Title=data.getTitle();
                    } else {
                        isShowSave = true;
                        showSave.showSave(isShowSave);
                        ZMTZZLink zmtzzLink = new ZMTZZLink("添加链接", "def12354555568852225333");

                        zmtzzLinks.add(zmtzzLink);
                    }

                    break;
            }
        }
    }

    @Override
    public void onFailure(String url, String error) {

    }

    private void dismissLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.img_close_item:
                zmtzzLinks.remove((int) v.getTag());
                zmtzzLinkAdapter.notifyDataSetChanged();

                if (zmtzzLinksCopy.size() == zmtzzLinks.size() && zmtzzLinks.contains(zmtzzLinksCopy)) {
                    isShowSave = false;

                } else {
                    isShowSave = true;

                }
                isShowSave();
                break;


        }

    }

    @Override
    public void onItemClick(View v, int position) {
        if (position == zmtzzLinks.size() - 1) {
            startActivityForResult(new Intent(getContext(), ZMTZZAddLinkActivity.class), Constant.Code.Link);
        }
    }


    @OnClick(R.id.rl_show)
    public void onViewClicked() {
        startActivityForResult(new Intent(getContext(), ProdectEditActivity.class), Constant.Code.ZMTZZ_prodect);

    }

    //是否显示确定
    public void isShowSave() {

        showSave.showSave(isShowSave);


    }


    public interface ShowSave {
        void showSave(boolean isShowSave);
    }

    public void setActivity(ShowSave showSave) {


        this.showSave = showSave;
    }

    public void reFlesh() {

        loadingDialog = LoadingDialog.newInstance("加载...");
        loadingDialog.show(getActivity().getFragmentManager());
        GetUserProductInfo getUserProductInfo = new GetUserProductInfo(PreferencesUtil.getToken(getContext()), PreferencesUtil.getUserId(getContext()));
        OkHttpUtil.postJson(Constant.URL.GetUserProductInfo, DesUtil.encrypt(gson.toJson(getUserProductInfo)), this);
    }

    public void setPreview() {

        if (getUserProductInfoEntity == null || getUserProductInfoEntity.getData() == null) {
            ToastUtil.initToast(getContext(), "暂无名片信息");
        } else {
            Intent intent = new Intent(getContext(), H5ActivityForZMTZZPreview.class);

            intent.putExtra("url", Constant.URL.ProdectPreview + getUserProductInfoEntity.getData().getUserProductInfoId());

            intent.putExtra("title", "我的产品");
            startActivity(intent);
        }
    }

}
