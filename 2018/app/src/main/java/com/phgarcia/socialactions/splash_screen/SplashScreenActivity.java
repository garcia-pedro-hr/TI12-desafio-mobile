package com.phgarcia.socialactions.splash_screen;

import com.phgarcia.socialactions.R;
import com.phgarcia.socialactions.list_social_entities.ListEntitiesActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SplashScreenActivity extends AppCompatActivity implements SplashScreenView {

    SplashScreenPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        presenter = new SplashScreenPresenter(SplashScreenActivity.this);

        presenter.openApp();
    }

    @Override
    public void openListEntitiesActivity(String json) {
        Intent openListEntitiesActivityIntent = new Intent(SplashScreenActivity.this, ListEntitiesActivity.class);
        openListEntitiesActivityIntent.putExtra(getString(R.string.entities_json), json);
        startActivity(openListEntitiesActivityIntent);
        finish();
    }

    @Override
    public void saveEntitiesInformationInSharedPreferences(String json) {
        SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.entities), MODE_PRIVATE).edit();
        editor.putString(getString(R.string.entities_json), json);
        editor.apply();
    }

    @Override
    public String loadEntitiesInformationFromSharedPreferences() {
        SharedPreferences preferences = getSharedPreferences(getString(R.string.entities), MODE_PRIVATE);
        String json = preferences.getString(getString(R.string.entities_json), null);
        return json;
    }

}
