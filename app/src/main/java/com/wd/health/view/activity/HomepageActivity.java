package com.wd.health.view.activity;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wd.health.R;
import com.wd.health.base.BaseActivity;
import com.wd.health.view.fragment.FirstFragment;
import com.wd.health.view.fragment.PatientFragment;
import com.wd.health.view.fragment.VideoFragment;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HomepageActivity extends BaseActivity {
    @BindView(R.id.vp)
    FrameLayout vp;
    @BindView(R.id.rb_shou_wei)
    ImageView rbShouWei;
    @BindView(R.id.rb_shou_xuan)
    ImageView rbShouXuan;

    @BindView(R.id.rb_movie_wei)
    ImageView rbMovieWei;
    @BindView(R.id.rb_movie_xuan)
    ImageView rbMovieXuan;
    @BindView(R.id.sick_circle_img)
    ImageView sickCircleImg;
    @BindView(R.id.comments_img)
    ImageView commentsImg;
    @BindView(R.id.home_lin)
    LinearLayout homeLin;
    @BindView(R.id.movie_lin)
    LinearLayout movieLin;
    @BindView(R.id.sick_lin)
    LinearLayout sickLin;
    private ArrayList<Fragment> list = new ArrayList<>();
    private long mExitTime;

    private FirstFragment homeFragment;
    private PatientFragment patientFragment;
    private VideoFragment mineFragment;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_homepage;
    }

    @Override
    protected void initView() {
        // 初始化Fragment
        homeFragment = new FirstFragment();
        patientFragment = new PatientFragment();
        mineFragment = new VideoFragment();
        // 显示隐藏
        getSupportFragmentManager().beginTransaction()
                .add(R.id.vp, homeFragment)
                .add(R.id.vp, patientFragment)
                .add(R.id.vp, mineFragment)
                .show(homeFragment)
                .hide(patientFragment)
                .hide(mineFragment)
                .commit();

        commentsImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomepageActivity.this,PostPatientActivity.class));
            }
        });
    }

    @OnClick({R.id.home_lin, R.id.movie_lin, R.id.sick_lin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_lin:
                getSupportFragmentManager()
                        .beginTransaction()
                        .show(homeFragment)
                        .hide(patientFragment)
                        .hide(mineFragment)
                        .commit();
                rbShouXuan.setVisibility(View.VISIBLE);
                rbShouWei.setVisibility(View.GONE);
                rbMovieXuan.setVisibility(View.GONE);
                rbMovieWei.setVisibility(View.VISIBLE);
                commentsImg.setVisibility(View.GONE);
                sickCircleImg.setVisibility(View.VISIBLE);

                break;
            case R.id.sick_lin:
                getSupportFragmentManager()
                        .beginTransaction()
                        .hide(homeFragment)
                        .show(patientFragment)
                        .hide(mineFragment)
                        .commit();
                rbMovieWei.setVisibility(View.VISIBLE);
                rbShouXuan.setVisibility(View.GONE);
                commentsImg.setVisibility(View.VISIBLE);
                sickCircleImg.setVisibility(View.GONE);
                rbShouWei.setVisibility(View.VISIBLE);
                rbMovieXuan.setVisibility(View.GONE);
                break;
            case R.id.movie_lin:
                getSupportFragmentManager()
                        .beginTransaction()
                        .hide(homeFragment)
                        .hide(patientFragment)
                        .show(mineFragment)
                        .commit();
                sickCircleImg.setVisibility(View.VISIBLE);
                rbMovieXuan.setVisibility(View.VISIBLE);
                rbShouXuan.setVisibility(View.GONE);
                rbShouWei.setVisibility(View.VISIBLE);
                commentsImg.setVisibility(View.GONE);
                rbMovieWei.setVisibility(View.GONE);
                break;
        }
    }
    boolean isState = true;

    public void onBackPressed() {
        if (isState) {
            isState = false;
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isState = true;
                }
            }, 2000);
        } else {
            finish();
        }
    }

}
