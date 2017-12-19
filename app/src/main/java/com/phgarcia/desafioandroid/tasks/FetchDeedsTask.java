package com.phgarcia.desafioandroid.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.phgarcia.desafioandroid.MainActivity;
import com.phgarcia.desafioandroid.util.WebClient;

/**
 * Created by garci on 12/19/2017.
 */

public class FetchDeedsTask extends AsyncTask<Object, Object, String> {

    private Context context;
    private String  webserviceURL;

    public FetchDeedsTask(Context context, String webserviceURL) {
        this.context = context;
        this.webserviceURL = webserviceURL;
    }

    @Override
    protected String doInBackground(Object[] objects) {
        WebClient client = new WebClient();
        String response = client.get(webserviceURL, "application/json");

        return response;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(context, "Baixando...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(String response) {
        System.out.println(response);
        Toast.makeText(context, response, Toast.LENGTH_LONG).show();
    }

}
