package com.terasoltechnologies.inshortdemo.services;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("photos")
    Call<List<ApiModel>> apiResponse();
}
