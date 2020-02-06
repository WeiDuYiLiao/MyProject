package com.wd.health.view.activity;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.wd.health.R;
import com.wd.health.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.vp_guide)
    ViewPager mVpGuide;

    @BindView(R.id.click)
    Button mClick;
    int page;
    List<ImageView> list;
    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initViewPager();
        mVpGuide.setAdapter(new MyPagerAdapter());
        mVpGuide.setOffscreenPageLimit(5);
        mClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "成功跳转", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,HomepageActivity.class);
                SharedPreferences medical = getSharedPreferences("medical", MODE_PRIVATE);
                SharedPreferences.Editor edit = medical.edit();
                edit.putBoolean("isFirsthome",true);
                edit.commit();
                startActivity(intent);
            }
        });

    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        list = new ArrayList<ImageView>();

        ImageView iv1 = new ImageView(this);
        //为ImageView添加图片资源
        iv1.setImageResource(R.mipmap.guide_page1);
        //设置图片充满屏幕
        iv1.setScaleType(ImageView.ScaleType.FIT_XY);

        ImageView iv2 = new ImageView(this);
        iv2.setImageResource(R.mipmap.guide_page2);
        iv2.setScaleType(ImageView.ScaleType.FIT_XY);

        ImageView iv3 = new ImageView(this);
        iv3.setImageResource(R.mipmap.guide_page3);
        iv3.setScaleType(ImageView.ScaleType.FIT_XY);

        ImageView iv4 = new ImageView(this);
        iv4.setImageResource(R.mipmap.guide_page4);
        iv4.setScaleType(ImageView.ScaleType.FIT_XY);

        ImageView iv5 = new ImageView(this);
        iv5.setImageResource(R.mipmap.guide_page5);
        iv5.setScaleType(ImageView.ScaleType.FIT_XY);


        //把ImageView添加到list集合
        list.add(iv1);
        list.add(iv2);
        list.add(iv3);
        list.add(iv4);
        list.add(iv5);

        //监听ViewPager滑动的效果
        mVpGuide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            //选中的页卡
            @Override
            public void onPageSelected(int position) {
                //如果是第三个页面就显示按钮，反之不显示
                if (position == 4) {
                    mClick.setVisibility(View.VISIBLE);
                } else {
                    mClick.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }



    /**
     * ViewPager的适配器
     */
    class MyPagerAdapter extends PagerAdapter {

        //计算item个数
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }

        //item的销毁方法
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }
    }
}
