package com.phgarcia.desafioandroid.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by garci on 12/19/2017.
 */

public class WebClient {
    public String post(String json, String urlString, String responseAcceptType) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            PrintStream output = new PrintStream(connection.getOutputStream());
            output.println(json);

            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", responseAcceptType);
            connection.setDoOutput(true); // post
            connection.connect();

            Scanner scanner = new Scanner(connection.getInputStream());
            String response = scanner.next();

            return response;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String get(String urlString, String responseAcceptType) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("Accept", responseAcceptType);
            connection.setDoInput(true);
            connection.connect();

            String response;

            if (responseAcceptType.equals("application/json")) {
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();

                String line;
                while ((line = br.readLine()) != null) sb.append(line + "\n");
                connection.getInputStream().close();
                response = sb.toString();
            } else {
                Scanner scanner = new Scanner(connection.getInputStream());
                response = scanner.next();
            }

            return response;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
