package com.phgarcia.socialactions.network.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.phgarcia.socialactions.entities.SocialEntityList;
import com.phgarcia.socialactions.network.service.SocialActionsService;

import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SocialActionsApi {

    private static SocialActionsApi instance;
    private SocialActionsService service;

    public static SocialActionsApi getInstance() {
        if (instance == null) {
            instance = new SocialActionsApi();
        }
        return instance;
    }

    private SocialActionsApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dl.dropboxusercontent.com/")
                .addConverterFactory(defaultConverterFactory())
                .build();
        service = retrofit.create(SocialActionsService.class);
    }

    private Converter.Factory defaultConverterFactory() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        return GsonConverterFactory.create(gson);
    }

    public Call<SocialEntityList> getSocialEntities() {
        return service.getSocialEntities();
    }

}
