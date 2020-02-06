package com.wd.health.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.wd.health.R;

import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity {

    private SharedPreferences medical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_activity);
        medical = getSharedPreferences("medical", MODE_PRIVATE);
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(StartActivity.this, MainActivity.class));
                finish();
            }
        };


        TimerTask timerTaskhome =new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(StartActivity.this, HomepageActivity.class));
                finish();
            }
        };
        if (medical.getBoolean("isFirsthome",false)){
            timer.schedule(timerTaskhome,2000);
        }else{
            timer.schedule(timerTask,2000);
        }
    }
}
