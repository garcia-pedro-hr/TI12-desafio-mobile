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

    private final String TABLE_NAME = "deeds";

    private SQLiteDatabase writable;
    private SQLiteDatabase readable;

    public DeedDAO(Context context) {
        super(context, "DesafioAndroid", null, 1);
        this.writable = getWritableDatabase();
        this.readable = getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE" + TABLE_NAME + "(id INTEGER PRIMARY KEY, name TEXT NOT NULL, imageURL TEXT, description TEXT, site TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME + ";");
        onCreate(db);
    }

    public void resetTable() {
        this.writable.execSQL("delete from " + TABLE_NAME + ";");
    }

    public void insert(Deed deed) {
        this.writable.insert(TABLE_NAME, null, getData(deed));
    }

    public void delete(Deed deed) {
        this.writable.delete(TABLE_NAME, "id = ?", new String[] {deed.getId().toString()});
    }

    public void update(Deed deed) {
        this.writable.update(TABLE_NAME, getData(deed), "id = ?", new String[] {deed.getId().toString()});
    }

    public void save(Deed deed) {
        // Update if row with same id already exists
        // Insert otherwise
        Cursor cursor = this.readable.rawQuery("SELECT id FROM " + TABLE_NAME + " WHERE id = ?", new String[] {deed.getId().toString()});

        if (cursor.getCount() > 0) update(deed);
        else insert(deed);

        cursor.close();
    }

    public List<Deed> list() {
        Cursor c = this.readable.rawQuery("SELECT * FROM Deeds", null);
        List<Deed> deedsList = new ArrayList<Deed> ();

        while (c.moveToNext()) {
            Deed deed = new Deed();

            deed.setId(c.getLong(c.getColumnIndex("id")));
            deed.setName(c.getString(c.getColumnIndex("name")));
            deed.setImageURL(c.getString(c.getColumnIndex("imageURL")));
            deed.setDescription(c.getString(c.getColumnIndex("description")));
            deed.setSite(c.getString(c.getColumnIndex("site")));

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
