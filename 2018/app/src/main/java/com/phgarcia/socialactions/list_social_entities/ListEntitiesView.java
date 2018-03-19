package com.phgarcia.socialactions.list_social_entities;

import com.phgarcia.socialactions.entities.SocialEntity;

import java.util.List;

interface ListEntitiesView {

    void updateList(List<SocialEntity> socialEntityList);
    void showMessage(String message);

}
