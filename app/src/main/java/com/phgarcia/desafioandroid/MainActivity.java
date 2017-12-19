package com.phgarcia.desafioandroid;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.phgarcia.desafioandroid.converter.DeedConverter;
import com.phgarcia.desafioandroid.dao.DeedDAO;
import com.phgarcia.desafioandroid.model.Deed;
import com.phgarcia.desafioandroid.tasks.FetchDeedsTask;
import com.phgarcia.desafioandroid.util.WebClient;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String WEBSERVICE_URL = "https://dl.dropboxusercontent.com/s/50vmlj7dhfaibpj/sociais.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_main_update:
                new FetchDeedsTask(this, WEBSERVICE_URL).execute();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
