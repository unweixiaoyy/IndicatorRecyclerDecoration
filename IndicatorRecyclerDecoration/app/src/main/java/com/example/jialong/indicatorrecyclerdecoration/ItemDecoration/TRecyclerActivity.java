package com.example.jialong.indicatorrecyclerdecoration.ItemDecoration;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.jialong.indicatorrecyclerdecoration.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenjialong on 2017/12/11.
 */

public class TRecyclerActivity extends Activity {

    RecyclerView recyclerView;
    ViewPager viewPager;
    TRecyclerAdapter tRecyclerAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trecyclerview_activity);
        viewPager = (ViewPager) findViewById(R.id.t_viewpager);
        recyclerView = (RecyclerView) findViewById(R.id.t_RecyclerView);
        final IndicatorItemDecoration recyclerDecoration = new IndicatorItemDecoration(this);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        tRecyclerAdapter = new TRecyclerAdapter(this);
        tRecyclerAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                scrollRecyclerViewToMiddle(recyclerView,position);
                recyclerDecoration.setSelected(position, false);
                viewPager.setCurrentItem(position, false);
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                recyclerDecoration.setMovedPercentage(position, positionOffset);
                tRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onPageSelected(int position) {
                scrollRecyclerViewToMiddle(recyclerView,position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                    recyclerDecoration.setOutScroll(true);

                }
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    recyclerDecoration.setOutScroll(false);
                }

            }
        });

        AdapterViewpager adapterViewpager = new AdapterViewpager();
        adapterViewpager.setData(createViewData());
        viewPager.setAdapter(adapterViewpager);

        tRecyclerAdapter.setData(createData());
        recyclerView.setAdapter(tRecyclerAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(recyclerDecoration);
    }

    private void scrollRecyclerViewToMiddle(RecyclerView recyclerView, int position){
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int left = recyclerView.getChildAt(position - layoutManager.findFirstVisibleItemPosition()).getLeft();
        int right = recyclerView.getChildAt(layoutManager.findLastVisibleItemPosition() - position).getLeft();
        recyclerView.scrollBy((left - right)/2,0);
    }

    public List<TRecyclerAdapter.Info> createData() {
        List<TRecyclerAdapter.Info> data = new ArrayList<>();
        String[] title = {"精选", "爱看", "吐槽大会", "电视剧", "电影", "游戏", "综艺", "动漫", "少儿", "蓝色星球", "NBA", "VIP会员"};
        for (int i = 0; i < title.length; i++) {
            data.add(new TRecyclerAdapter.Info(title[i]));
        }
        return data;
    }

    public List<View> createViewData() {
        List<View> data = new ArrayList<>();
        String[] color = {"#111111", "#222222", "#333333", "#444444", "#555555", "#666666",
                "#777777", "#888888", "#999999", "#AAAAAA", "#BBBBBB", "#CCCCCC"};
        for (int i = 0; i < color.length; i++) {
            TextView textView = new TextView(this);
            textView.setBackgroundColor(Color.parseColor(color[i]));
            textView.setLayoutParams(new ViewPager.LayoutParams());
            textView.setText("我说第：" + i + "页");
            data.add(textView);
        }
        return data;
    }

    public class AdapterViewpager extends PagerAdapter {
        private List<View> mViewList;

        public AdapterViewpager() {
        }

        public void setData(List<View> mViewList) {
            this.mViewList = mViewList;
        }

        @Override
        public int getCount() {//必须实现
            return mViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {//必须实现
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {//必须实现，实例化
            container.addView(mViewList.get(position));
            return mViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {//必须实现，销毁
            container.removeView(mViewList.get(position));
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return super.getPageTitle(position);
        }


    }


}
