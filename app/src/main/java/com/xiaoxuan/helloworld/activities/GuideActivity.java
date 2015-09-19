package com.xiaoxuan.helloworld.activities;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xiaoxuan.helloworld.R;
import com.xiaoxuan.helloworld.adapter.ViewPagerAdapter;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by xiaoxuan on 2015/9/18.
 */
public class GuideActivity extends ActionBarActivity {
    @InjectView(R.id.guide_view_pager)
    ViewPager viewPager;
    @InjectView(R.id.guide_dots)
    LinearLayout dots;

    private ViewPagerAdapter adapter;

    private ArrayList<View> views;

    private Button startBtn;

    private int count;

    // 图片资源
    private final int images[] = {
            R.mipmap.guide_page1, R.mipmap.guide_page2, R.mipmap.guide_page3
    };

    private ImageView[] guideDots;

    // 记录当前选中的图片
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_view);
        ButterKnife.inject(this);

        initView();
        initDot();

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                setCurrentDot(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // 开始按钮的点击事件监听
        startBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuideActivity.this, SplashActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void initView() {
        views = new ArrayList<>();
        for (int i = 0; i < images.length; i++) {
            // 新建一个ImageView容器来放置我们的图片。
            ImageView iv = new ImageView(GuideActivity.this);
            iv.setBackgroundResource(images[i]);

            // 将容器添加到图片列表中
            views.add(iv);
        }
        View view = LayoutInflater.from(GuideActivity.this).inflate(R.layout.guide_content_view,
                null);
        views.add(view);

        startBtn = (Button)view.findViewById(R.id.start_btn);

        adapter = new ViewPagerAdapter(views);
        viewPager.setAdapter(adapter);
    }

    private void initDot() {
        // 找到放置小点的布局
        guideDots = new ImageView[views.size()];

        // 循环取得小点图片，让每个小点都处于正常状态
        for (int i = 0; i < views.size(); i++) {
            guideDots[i] = (ImageView) dots.getChildAt(i);
            guideDots[i].setSelected(false);
        }

        // 初始化第一个小点为选中状态
        currentIndex = 0;
        guideDots[currentIndex].setSelected(true);
    }

    // 页面更换时，更新小点状态
    private void setCurrentDot(int position) {
        if (position < 0 || position > views.size() - 1 || currentIndex == position) {
            return;
        }

        guideDots[position].setSelected(true);
        guideDots[currentIndex].setSelected(false);

        currentIndex = position;
    }
}