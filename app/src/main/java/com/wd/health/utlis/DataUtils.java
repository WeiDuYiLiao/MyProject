package com.wd.health.utlis;

import com.wd.health.model.Bean;

import java.util.ArrayList;

public class DataUtils {
    public static ArrayList<Bean> getDatas() {
        ArrayList<Bean> videoList = new ArrayList<>();
        videoList.add(new Bean("http://172.17.8.100/video/health/original/ek/ek1.mp4"));
        videoList.add(new Bean("http://172.17.8.100/video/health/original/ek/ek6.mp4"));
        videoList.add(new Bean("http://172.17.8.100/video/health/original/ek/ek4.mp4"));
        videoList.add(new Bean("http://172.17.8.100/video/health/original/ek/ek5.mp4"));
        videoList.add(new Bean("http://172.17.8.100/video/health/original/ek/ek7.mp4"));
        return videoList;

        
    }
}
