package com.phgarcia.desafioandroid.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.phgarcia.desafioandroid.R;
import com.phgarcia.desafioandroid.dao.DeedDAO;
import com.phgarcia.desafioandroid.model.Deed;
import com.phgarcia.desafioandroid.task.FetchDeedsTask;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_WRITE_EXTERNAL_STORAGE = 112;

    private ListView deedsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        deedsListView = (ListView) findViewById(R.id.deeds_list);
        deedsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Deed deed = (Deed) deedsListView.getItemAtPosition(position);

                Intent intentGoToShowDeed = new Intent(MainActivity.this, ShowDeedActivity.class);
                intentGoToShowDeed.putExtra("deed", deed);
                startActivity(intentGoToShowDeed);
            }
        });

        loadDeedListView();
        registerForContextMenu(deedsListView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Deed deed = (Deed) deedsListView.getItemAtPosition(info.position);

        // Context Menu Delete Button
        MenuItem deleteItem = menu.add("Deletar");
        deleteItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                DeedDAO dao = new DeedDAO(MainActivity.this);
                dao.delete(deed);
                dao.close();

                loadDeedListView();
                return false;
            }
        });
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
                // check permission to write to external storage
                boolean hasExternalWritePermission = (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED);

                if (!hasExternalWritePermission) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL_STORAGE);
                } else {
                    new FetchDeedsTask(this, deedsListView).execute();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case REQUEST_WRITE_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    new FetchDeedsTask(this, deedsListView).execute();
                } else {
                    Toast.makeText(MainActivity.this, "O aplicativo não foi permitido escrever no armazenamento de seu dispositivo. Desta forma, não funcionando corretamente. Por favor considere conceder a permissão.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void loadDeedListView() {
        DeedDAO dao = new DeedDAO(this);
        List<Deed> deeds = dao.list();
        dao.close();

        ArrayAdapter<Deed> adapter = new ArrayAdapter<Deed>(this, android.R.layout.simple_list_item_1, deeds);
        deedsListView.setAdapter(adapter);
    }
}
