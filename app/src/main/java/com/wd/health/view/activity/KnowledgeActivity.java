package com.wd.health.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;
import com.wd.health.R;
import com.wd.health.base.BaseActivity;
import com.wd.health.view.fragment.DiseaseFragment;
import com.wd.health.view.fragment.DrugFragment;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class KnowledgeActivity extends BaseActivity {

    @BindView(R.id.show_tab)
    TabLayout showTab;
    @BindView(R.id.shwo_vp)
    ViewPager shwo_vp;
    private int yaopin;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_knowledge;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        yaopin = intent.getIntExtra("yaopin", 0);
        final ArrayList<Fragment> list = new ArrayList<>();
        final ArrayList<String> tablist = new ArrayList<>();
        list.add(new DiseaseFragment());
        list.add(new DrugFragment());
        tablist.add("常见病症");
        tablist.add("常用药品");
        shwo_vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return tablist.get(position);
            }
        });

        if (yaopin!=0){
            shwo_vp.setCurrentItem(2);
        }
        showTab.setupWithViewPager(shwo_vp);
        showTab.setSelectedTabIndicatorHeight(0);
    }


}
