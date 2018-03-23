package com.phgarcia.socialactions.splash_screen;

import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;
import com.phgarcia.socialactions.R;
import com.phgarcia.socialactions.entities.SocialEntity;
import com.phgarcia.socialactions.entities.SocialEntityList;
import com.phgarcia.socialactions.network.api.SocialActionsApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenPresenter {

    SplashScreenView view;
    private SocialEntityList socialEntityList;
    private List<SocialEntity> entityList;
    private String entityInformationJson;

    public SplashScreenPresenter(SplashScreenView view) {
        this.view = view;
    }

    public void openApp() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                downloadEntitiesInformation();
            }
        }, 1000);
    }

    void downloadEntitiesInformation() {
        final SocialActionsApi api = SocialActionsApi.getInstance();
        api.getSocialEntitiesJson().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body() != null) {
                    view.saveEntitiesInformationInSharedPreferences(response.body());
                    view.openListEntitiesActivity(entityInformationJson);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                entityInformationJson = view.loadEntitiesInformationFromSharedPreferences();
                view.openListEntitiesActivity(entityInformationJson);
            }
        });
    }

}
