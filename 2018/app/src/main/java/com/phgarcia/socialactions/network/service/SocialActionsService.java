package com.phgarcia.socialactions.network.service;

import com.phgarcia.socialactions.entities.SocialEntityList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SocialActionsService {

    @GET("s/f39meuzbspdlrhl/sociais.json")
    Call<SocialEntityList> getSocialEntities();

}
