package com.phgarcia.socialactions.list_social_entities;

import android.util.Log;

import com.google.gson.Gson;
import com.phgarcia.socialactions.entities.SocialEntity;
import com.phgarcia.socialactions.entities.SocialEntityList;
import com.phgarcia.socialactions.network.api.SocialActionsApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListEntitiesPresenter {

    private ListEntitiesView view;
    private SocialEntityList socialEntityList;
    private List<SocialEntity> entityList;

    ListEntitiesPresenter(ListEntitiesView view) {
        this.view = view;
    }

    void updateList(String json) {
        // Check if there is information on the JSON provided
        if (json != null) {
            socialEntityList = new Gson().fromJson(json, SocialEntityList.class);
            entityList = socialEntityList.getEntities();
            view.updateList(entityList);
        } else {
            final SocialActionsApi api = SocialActionsApi.getInstance();
            view.showLoading();
            api.getSocialEntities().enqueue(new Callback<SocialEntityList>() {
                @Override
                public void onResponse(Call<SocialEntityList> call, Response<SocialEntityList> response) {
                    view.hideLoading();
                    socialEntityList = response.body();

                    if (socialEntityList != null) {
                        entityList = socialEntityList.getEntities();
                        view.updateList(entityList);
                    } else {
                        view.showMessage("Falha ao carregar a lista de Entidades Sociais");
                    }
                }

                @Override
                public void onFailure(Call<SocialEntityList> call, Throwable t) {
                    view.hideLoading();
                    view.showMessage("Falha no acesso ao servidor");
                }
            });
        }
    }

    SocialEntity getEntity(int position) throws IndexOutOfBoundsException {
        return entityList.get(position);
    }
}
