package com.phgarcia.socialactions.splash_screen;

import com.phgarcia.socialactions.R;
import com.phgarcia.socialactions.list_social_entities.ListEntitiesActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
    public void openListEntitiesActivity() {
        Intent openListEntitiesActivityIntent = new Intent(SplashScreenActivity.this, ListEntitiesActivity.class);
        startActivity(openListEntitiesActivityIntent);
        finish();
    }

}
