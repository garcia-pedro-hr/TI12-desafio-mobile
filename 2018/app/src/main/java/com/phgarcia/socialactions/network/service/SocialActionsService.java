package com.phgarcia.socialactions.network.service;

import com.phgarcia.socialactions.entities.SocialEntityList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SocialActionsService {

    @GET("s/f39meuzbspdlrhl/sociais")
    Call<SocialEntityList> getSocialEntities();

}
