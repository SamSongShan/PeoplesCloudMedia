package com.example.a11355.peoplescloudmedia.fragement;


import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.activity.FindSearchActivity;
import com.example.a11355.peoplescloudmedia.base.BaseFragment;
import com.example.a11355.peoplescloudmedia.custom.AutoScrollViewPager;
import com.example.a11355.peoplescloudmedia.custom.LoadingDialog;
import com.example.a11355.peoplescloudmedia.custom.NavView;
import com.example.a11355.peoplescloudmedia.model.GetHomeRotateListEntity;
import com.example.a11355.peoplescloudmedia.model.GetNewsTypeListEntity;
import com.example.a11355.peoplescloudmedia.util.Constant;
import com.example.a11355.peoplescloudmedia.util.DesUtil;
import com.example.a11355.peoplescloudmedia.util.DisplayUtil;
import com.example.a11355.peoplescloudmedia.util.LogUtils;
import com.example.a11355.peoplescloudmedia.util.OkHttpUtil;
import com.example.a11355.peoplescloudmedia.util.PhoneUtil;
import com.example.a11355.peoplescloudmedia.util.TabLayoutUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 发现
 */
public class FindFragment extends BaseFragment implements OkHttpUtil.OnDataListener, ViewPager.OnPageChangeListener {


    @BindView(R.id.tab_find)
    TabLayout tabFind;
    @BindView(R.id.vp_find)
    ViewPager vpFind;
    @BindView(R.id.vp_homeAd)
    AutoScrollViewPager viewPager;
    @BindView(R.id.nv_homeAd)
    NavView navView;









    private Gson gson = new GsonBuilder().create();
    private LoadingDialog loadingDialog;
    private HomeAdAdapter viewPagerAdapter;
    private List<GetHomeRotateListEntity.DataEntity> imageList=new ArrayList<>();//轮播图数据

    public FindFragment() {
        // Required empty public constructor
    }

    @Override
    protected void init(View v) {
        loadingDialog = LoadingDialog.newInstance("加载中...");
        loadingDialog.show(getActivity().getFragmentManager());
        initViewPager();
    }

    private void initViewPager() {
        viewPager.setLayoutParams(new RelativeLayout.LayoutParams(PhoneUtil.getPhoneWidth(getActivity()),
                DisplayUtil.dip2px(getContext(),100)));
        viewPagerAdapter = new HomeAdAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setSlideBorderMode(AutoScrollViewPager.SLIDE_BORDER_MODE_CYCLE);
        viewPager.setInterval(2300);
        viewPager.setAutoScrollDurationFactor(4);
        viewPager.addOnPageChangeListener(this);
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        break;
                }
                return false;
            }
        });



    }

    @Override
    protected void loadData() {
        OkHttpUtil.postJson(Constant.URL.GetNewsTypeList, "0", this);

        OkHttpUtil.postJson(Constant.URL.GetHomeRotateList, "0", this);//首页轮播


    }

    @Override
    protected int getViewResId() {
        return R.layout.fragment_find;
    }


    @Override
    public void onResponse(String url, String json) {
        dismissLoading();
        if (!TextUtils.isEmpty(json)) {

            String decrypt = DesUtil.decrypt(json);
            if (Constant.URL.GetNewsTypeList.equals(url)) {
                LogUtils.e("GetNewsTypeList", decrypt);
                GetNewsTypeListEntity getNewsTypeListEntity = gson.fromJson(decrypt, GetNewsTypeListEntity.class);
                initVP(getNewsTypeListEntity.getData());
            } else if (Constant.URL.GetHomeRotateList.equals(url)) {
                LogUtils.e("GetHomeRotateList", decrypt);
                GetHomeRotateListEntity getHomeRotateListEntity = gson.fromJson(decrypt, GetHomeRotateListEntity.class);
                viewPagerAdapter.setData(getHomeRotateListEntity.getData());
                viewPager.setCurrentItem(1);
                viewPager.startAutoScroll();
            }
        }

    }

    private void initVP(final List<GetNewsTypeListEntity.DataBean> data) {
        vpFind.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return FindArticleFragment.instanceFragment(data.get(position).getId() + "", position);
            }

            @Override
            public int getCount() {
                return data.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return data.get(position).getName();
            }
        });

        tabFind.setupWithViewPager(vpFind);

        TabLayoutUtils.reflex(tabFind, 10);
    }

    @Override
    public void onFailure(String url, String error) {

    }

    private void dismissLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }

    }

    @OnClick({R.id.tv_search_bg, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_search_bg://搜索
            case R.id.tv_search:
                startActivity(new Intent(getContext(), FindSearchActivity.class));
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (navView == null) {
            return;
        }
        if (position == 0) {
            navView.setCurrentItem(imageList.size() - 1);
        } else if (position == imageList.size() + 1) {
            navView.setCurrentItem(0);
        } else {
            navView.setCurrentItem(position - 1);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (viewPager == null) {
            return;
        }
        if (state == 0) {
            if (viewPager.getCurrentItem() == imageList.size() + 1) {
                viewPager.setCurrentItem(1, false);
            } else if (viewPager.getCurrentItem() == 0) {
                viewPager.setCurrentItem(imageList.size(), false);
            }
        }
    }

    /**
     * 首页 viewPager适配器
     */
    class HomeAdAdapter extends PagerAdapter {

        private List<SimpleDraweeView> imgData;

        public void setData(List<GetHomeRotateListEntity.DataEntity> data) {
            if (imgData == null) {
                imgData = new ArrayList<>();
            } else {
                imgData.clear();
            }
            if (navView == null) {
                return;
            }
            navView.setCount(data.size());
            if (data.size() > 1) {
                imgData.add(initSDV(data.size() - 1, data.get(data.size() - 1).getImageFilePath()));
            }
            for (int i = 0; i < data.size(); i++) {
                imgData.add(initSDV(i, data.get(i).getImageFilePath()));
            }
            if (data.size() > 1) {
                imgData.add(initSDV(0, data.get(0).getImageFilePath()));
            }
            this.notifyDataSetChanged();
        }


        public SimpleDraweeView initSDV(final int index, String url) {
            SimpleDraweeView sdv = (SimpleDraweeView) LayoutInflater.from(getActivity())
                    .inflate(R.layout.sdv_home, null, false);
            sdv.setAspectRatio(2.0F);
            sdv.setImageURI(Uri.parse(Constant.URL.BaseImg + url));
            sdv.setTag(index);
            // sdv.setOnClickListener(HomeFragment.this);
            sdv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            return sdv;
        }

        @Override
        public int getCount() {
            if (imgData == null) {
                return 0;
            }
            return imgData.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imgData.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imgData.get(position));
            return imgData.get(position);
        }
    }
}
