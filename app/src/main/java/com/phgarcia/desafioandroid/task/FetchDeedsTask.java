package com.phgarcia.desafioandroid.task;

import android.app.AlertDialog;
import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.phgarcia.desafioandroid.R;
import com.phgarcia.desafioandroid.converter.DeedConverter;
import com.phgarcia.desafioandroid.dao.DeedDAO;
import com.phgarcia.desafioandroid.model.Deed;
import com.phgarcia.desafioandroid.util.Constants;
import com.phgarcia.desafioandroid.util.ImageUtil;
import com.phgarcia.desafioandroid.util.WebClient;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import dmax.dialog.SpotsDialog;

/**
 * Created by garci on 12/19/2017.
 */

public class FetchDeedsTask extends AsyncTask<Object, Object, List<Deed>> {
    private Context context;
    private AlertDialog dialog;
    private ListView deedsListView;

    public FetchDeedsTask(Context context, ListView listView) {
        this.context = context;
        this.deedsListView = listView;
        dialog = new SpotsDialog(context, R.style.CustomSpotsDialog);
    }

    @Override
    protected List<Deed> doInBackground(Object[] objects) {
        WebClient client = new WebClient();
        String response = client.get(Constants.FETCH_WEBSERVICE, "application/json");

        // Get Deeds list from JSON
        DeedConverter deedHelper = new DeedConverter();
        List<Deed> deedsList= deedHelper.getFromJSON(response);

        // Reset table because incoming data may be new
        DeedDAO dao = new DeedDAO(context);
        dao.resetTable();

        // For each deed
        for (Deed deed : deedsList) {
            // download it's image and save to disk
            String filename = deed.getId() + ".jpg";
            File file = ImageUtil.downloadJPGFromURL(context, deed.getImageURL(), filename);

            // set image path
            if (file != null) deed.setImagePath(file.getAbsolutePath());

            // Tell media scanner about new file so it is immediately available to the user
            MediaScannerConnection.scanFile(context, new String[] {file.toString()}, null,
                    new MediaScannerConnection.OnScanCompletedListener() {

                        @Override
                        public void onScanCompleted(String path, Uri uri) {
                            Log.i("ExternalStorage", "Scanned " + path + ":");
                            Log.i("ExternalStorage", "-> uri=" + uri);
                        }
                    }
            );

            // finally save it
            dao.save(deed);
        }
        dao.close();

        return deedsList;
    }

    @Override
    protected void onPreExecute() {
        dialog.show();
    }

    @Override
    protected void onPostExecute(List<Deed> response) {
        dialog.dismiss();

        // Update deedsListView from MainActivity
        ArrayAdapter<Deed> adapter = new ArrayAdapter<Deed>(context, android.R.layout.simple_list_item_1, response);
        this.deedsListView.setAdapter(adapter);

        Toast.makeText(context, "Atualizado!", Toast.LENGTH_LONG).show();
    }

}
