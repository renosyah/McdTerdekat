package com.ocha.mcdterdekat.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ocha.mcdterdekat.BuildConfig;
import com.ocha.mcdterdekat.model.location.LocationModel;
import com.ocha.mcdterdekat.model.location.RequestLocation;
import com.ocha.mcdterdekat.model.response.ResponseModel;
import com.ocha.mcdterdekat.model.user.UserModel;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface RetrofitService {

    @POST("/api/location/list_closes.php")
    public Observable<ResponseModel<ArrayList<LocationModel>>> locations(@Body RequestLocation requestLocation);

    @POST("/api/user/login.php")
    public Observable<ResponseModel<UserModel>> login(@Body UserModel user);

    @POST("/api/user/add.php")
    public Observable<ResponseModel<String>> add(@Body UserModel user);

    public static RetrofitService create()  {

        // deklarasi gson builder
        // fungsinya agar dapat
        // melakukan parsing json
        // meskipun json kurang valid
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        // membuat instance retrofit
        // yg nantinya ini yg akan digunakan untuk
        // melakukan request ke api
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BuildConfig.SERVER_URL)
                .build();

        // balikan instance
        // sebagai nilai balik
        return retrofit.create(RetrofitService.class);
    }
}
