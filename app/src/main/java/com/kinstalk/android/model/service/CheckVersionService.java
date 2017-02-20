package com.kinstalk.android.model.service;

import com.kinstalk.android.model.model.BaseModel;
import com.kinstalk.android.model.model.VersionBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by siqing on 17/2/20.
 */

public interface CheckVersionService {

    @GET("/api/version/android_new")
    Call<BaseModel<VersionBean>> checkVersion();
}
