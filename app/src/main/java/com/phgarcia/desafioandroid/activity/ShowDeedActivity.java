package com.phgarcia.desafioandroid.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

        Intent intent = getIntent();
        this.deed = (Deed) intent.getSerializableExtra("deed");

        helper = new ShowDeedHelper(this, deed);
        helper.fillInfo();
    }
}
