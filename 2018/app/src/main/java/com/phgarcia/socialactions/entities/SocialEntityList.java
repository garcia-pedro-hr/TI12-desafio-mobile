package com.phgarcia.socialactions.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SocialEntityList {

    @Expose @SerializedName("entidade_social_list") private List<SocialEntity> entities;

    public List<SocialEntity> getEntities() {
        return entities;
    }
}
