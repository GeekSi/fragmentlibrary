package com.kinstalk.android.model.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by siqing on 17/2/20.
 */

public class RetrofitClient {
    private static final String BASE_API_URL = "http://bobo.yimwing.com";

    public static Retrofit getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_API_URL)
                .client(getOkhttpClient().build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }


    public static OkHttpClient.Builder getOkhttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor());
    }
}
