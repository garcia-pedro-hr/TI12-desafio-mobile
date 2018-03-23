package com.phgarcia.socialactions.splash_screen;


interface SplashScreenView {

    void openListEntitiesActivity(String json);
    void saveEntitiesInformationInSharedPreferences(String json);
    String loadEntitiesInformationFromSharedPreferences();

}
