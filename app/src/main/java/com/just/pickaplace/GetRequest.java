package com.just.pickaplace;

import android.os.AsyncTask;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Gets the http request
 *
 * @author Ken Sodetz
 * @since 04/06/2018
 */

public class GetRequest extends AsyncTask<Void, Void, String> {

    public AsyncResponse delegate = null;

    /**
     * Default constructor
     */
    public GetRequest() {
    }

    /**
     * Get the HTTP request in the background
     * @param the result of the doRequest() method
     */
    @Override
    protected String doInBackground(Void... params) {
        return doRequest();
    }

    /**
     * Pass the result to the main activity once the thread has completed
     * @param result result of the HTTP get request
     */
    @Override
    protected void onPostExecute(String result){
        delegate.processFinish(result);
    }

    /**
     * Complete the http request
     * @return string of the response
     */
    public String doRequest() {
        String charset = "UTF-8";
        InputStream response = null;
        URLConnection connection;
        String url = "https://api.yelp.com/v3/businesses/search?location=New York City, NYC, 350 5th Ave, New York, NY 10118";
        try {
            connection = new URL(url).openConnection();
            connection.setRequestProperty("Accept-Charset", charset);
            response = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert response != null;
        try(Scanner scanner = new Scanner(response)) {
            String parsed = scanner.useDelimiter("\\A").next();
            return parsed;
        }
    }
}
