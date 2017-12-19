package com.phgarcia.desafioandroid.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.phgarcia.desafioandroid.model.Deed;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by garci on 12/19/2017.
 */

public class DeedDAO extends SQLiteOpenHelper {

    public DeedDAO(Context context) {
        super(context, "DesafioAndroid", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Deeds (id INTEGER PRIMARY KEY, name TEXT NOT NULL, imageURL TEXT, description TEXT, site TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS Deeds;");
        onCreate(db);
    }

    public void insert(Deed deed) {
        SQLiteDatabase db = getWritableDatabase();
        db.insert("Deeds", null, getData(deed));
    }

    public void delete(Deed deed) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("Deeds", "id = ?", new String[] {deed.getId().toString()});
    }

    public void update(Deed deed) {
        SQLiteDatabase db = getWritableDatabase();
        db.update("Deeds", getData(deed), "id = ?", new String[] {deed.getId().toString()});
    }

    public List<Deed> list() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Deeds", null);
        List<Deed> deedsList = new ArrayList<Deed> ();

        while (c.moveToNext()) {
            Deed deed = new Deed();

            deed.setId(c.getLong(c.getColumnIndex("id")));
            deed.setName(c.getString(c.getColumnIndex("name")));
            deed.setName(c.getString(c.getColumnIndex("imageURL")));
            deed.setName(c.getString(c.getColumnIndex("description")));
            deed.setName(c.getString(c.getColumnIndex("site")));

            deedsList.add(deed);
        }
        c.close();

        return deedsList;
    }

    @NonNull
    private ContentValues getData(Deed deed) {
        ContentValues data = new ContentValues();

        data.put("id", deed.getId());
        data.put("name", deed.getName());
        data.put("imageURL", deed.getImageURL());
        data.put("description", deed.getDescription());
        data.put("site", deed.getSite());

        return data;
    }
}
