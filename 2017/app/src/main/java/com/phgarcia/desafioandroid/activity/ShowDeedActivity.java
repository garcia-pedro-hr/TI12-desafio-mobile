package com.phgarcia.desafioandroid.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.phgarcia.desafioandroid.R;
import com.phgarcia.desafioandroid.helper.ShowDeedHelper;
import com.phgarcia.desafioandroid.model.Deed;

public class ShowDeedActivity extends AppCompatActivity {

    private ShowDeedHelper helper;
    private Deed deed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_deed);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Intent intent = getIntent();
        this.deed = (Deed) intent.getSerializableExtra("deed");

        helper = new ShowDeedHelper(this, deed);
        helper.fillInfo(this);

        findViewById(R.id.deed_website_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uriString = deed.getSite();
                if (!uriString.startsWith("http://")) {
                    uriString = "http://" + uriString;
                }

                Intent intentVisitWebsite = new Intent(Intent.ACTION_VIEW);
                intentVisitWebsite.setData(Uri.parse(uriString));
                startActivity(intentVisitWebsite);
            }
        });
    }
}
