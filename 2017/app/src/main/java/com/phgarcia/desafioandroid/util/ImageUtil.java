package com.phgarcia.desafioandroid.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by garci on 12/19/2017.
 */

public class ImageUtil {

    private static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();

            InputStream input = connection.getInputStream();
            Bitmap bmp = BitmapFactory.decodeStream(input);
            return bmp;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File downloadJPGFromURL(Context context, String src, String filename) {
        File packageDirectory = new File(Environment.getExternalStorageDirectory(), context.getPackageName());
        packageDirectory.mkdirs();

        File directory = new File(packageDirectory, Constants.FETCH_SAVE_DIRECTORY);
        directory.mkdirs();

        File file = new File(directory, filename);
        if (file.exists())
            file.delete();   // delete file if it's comming from a previous state of the database

        try {
            // Download file, compress it to JPEG and save
            FileOutputStream out = new FileOutputStream(file);
            Bitmap bmp = getBitmapFromURL(src);
            bmp.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

            return file;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
