package com.wd.health.utlis;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*时间:2020/1/8
创建人:yang 
创建人:杨靖宇*/
public class HttpUtlis {
    private static HttpUtlis httpUtlis;
    private Retrofit retrofit;

    private HttpUtlis() {

        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client=new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .client(client)
                //.baseUrl("http://172.17.8.100/movieApi/")
               // .baseUrl("http://172.17.8.100/health/")
                .baseUrl("http://mobile.bwstudent.com/health/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static HttpUtlis getInstance() {
        if (httpUtlis==null) {
            httpUtlis=new HttpUtlis();
        }
        return httpUtlis;
    }
    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }
}
