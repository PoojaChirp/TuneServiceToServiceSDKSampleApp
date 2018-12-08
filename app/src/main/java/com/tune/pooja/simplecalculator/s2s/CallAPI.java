package com.tune.pooja.simplecalculator.s2s;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

public class CallAPI extends AsyncTask<Object, Integer, Boolean> {

    public CallAPI() {
        //set context variables if required
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(Object... params) {

        Map<String, String> headers = (Map<String, String>) params[0];
        String data = "";//data to post
        OutputStream out = null;

        try {
            String urlString = (String) params[1];
            URL url = new URL(urlString);
            // Create the urlConnection
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");

            Iterator it = headers.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                urlConnection.setRequestProperty((String) pair.getKey(), (String) pair.getValue());
            }

            // Send the post body
            if (data != null) {
                OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
                writer.write(data.toString());
                writer.flush();
            }

            int statusCode = urlConnection.getResponseCode();


                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());

                String response = convertStreamToString(inputStream);
                System.out.println(response);


            return true;
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;

    }

     String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}