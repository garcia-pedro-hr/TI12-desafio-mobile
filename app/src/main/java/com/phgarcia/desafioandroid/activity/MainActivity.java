package com.phgarcia.desafioandroid.activity;

import android.content.Intent;
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
                new FetchDeedsTask(this, deedsListView).execute();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadDeedListView() {
        DeedDAO dao = new DeedDAO(this);
        List<Deed> deeds = dao.list();
        dao.close();

        ArrayAdapter<Deed> adapter = new ArrayAdapter<Deed>(this, android.R.layout.simple_list_item_1, deeds);
        deedsListView.setAdapter(adapter);
    }
}
