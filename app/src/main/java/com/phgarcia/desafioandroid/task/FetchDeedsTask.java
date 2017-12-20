package com.phgarcia.desafioandroid.task;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.phgarcia.desafioandroid.R;
import com.phgarcia.desafioandroid.converter.DeedConverter;
import com.phgarcia.desafioandroid.dao.DeedDAO;
import com.phgarcia.desafioandroid.model.Deed;
import com.phgarcia.desafioandroid.util.Constants;
import com.phgarcia.desafioandroid.util.WebClient;

import java.util.List;

import dmax.dialog.SpotsDialog;

/**
 * Created by garci on 12/19/2017.
 */

public class FetchDeedsTask extends AsyncTask<Object, Object, String> {
    private Context context;
    private AlertDialog dialog;
    private ListView listView;

    public FetchDeedsTask(Context context, ListView listView) {
        this.context = context;
        this.listView = listView;
        dialog = new SpotsDialog(context, R.style.CustomSpotsDialog);
    }

    @Override
    protected String doInBackground(Object[] objects) {
        WebClient client = new WebClient();
        return client.get(Constants.FETCH_WEBSERVICE, "application/json");
    }

    @Override
    protected void onPreExecute() {
        dialog.show();
    }

    @Override
    protected void onPostExecute(String response) {
        DeedConverter dc = new DeedConverter();
        List<Deed> deedsList= dc.getFromJSON(response);

        DeedDAO dao = new DeedDAO(context);
        dao.resetTable();

        for (Deed deed : deedsList) {
            dao.save(deed);
        }

        dialog.dismiss();

        ArrayAdapter<Deed> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, deedsList);
        this.listView.setAdapter(adapter);

        Toast.makeText(context, "Atualizado!", Toast.LENGTH_LONG).show();
    }

}
