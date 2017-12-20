package com.phgarcia.desafioandroid.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by garci on 12/19/2017.
 */

public class ImageDownloader {

    public boolean downloadFromURL(String urlString, String path) {
        try {
            URL url = new URL(urlString);
            InputStream in = new BufferedInputStream(url.openStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];

            int n = 0;
            while ((n=in.read(buf)) != -1) out.write(buf, 0, n);

            out.close();
            in.close();

            byte[] response = out.toByteArray();

            FileOutputStream fos = new FileOutputStream(path);
            fos.write(response);
            fos.close();

            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
