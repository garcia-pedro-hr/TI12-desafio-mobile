package com.phgarcia.socialactions.splash_screen;

import android.os.Handler;
import java.util.logging.LogRecord;

public class SplashScreenPresenter {

    SplashScreenView view;

    public SplashScreenPresenter(SplashScreenView view) {
        this.view = view;
    }

    public void openApp() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.openListEntitiesActivity();
            }
        }, 2000);
    }

}
