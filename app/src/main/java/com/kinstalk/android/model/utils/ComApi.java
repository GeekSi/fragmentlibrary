package com.kinstalk.android.model.utils;

import com.kinstalk.android.model.service.CheckVersionService;

import retrofit2.Retrofit;

/**
 * Created by siqing on 17/2/20.
 */

public class ComApi {
    private static ComApi comApi = new ComApi();

    private Retrofit retrofit;

    private ComApi() {
        retrofit = RetrofitClient.getRetrofit();
    }

    public static ComApi getInstance() {
        return comApi;
    }

    public CheckVersionService getCheckVersionService(){

        return retrofit.create(CheckVersionService.class);
    }

}
