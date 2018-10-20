package com.just.pickaplace;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class GeneratePlacesActivity extends AppCompatActivity {

    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getAsyncCall();

        result = "";

        try {
            JSONObject jsonResponse = new JSONObject(result);
            JSONArray jArray = jsonResponse.getJSONArray("businesses");
            for (int i=0; i < 5; i++)
            {
                try {
                    JSONObject oneObject = jArray.getJSONObject(i);
                    // Pulling items from the array
                    String businessName = oneObject.getString("name");

                    Log.i("Business: ", businessName);

                    // String  = oneObject.getString("anotherSTRINGNAMEINtheARRAY");
                } catch (JSONException e) {
                    // Oops
                }
            }
        }
        catch (Exception e){

        }

    }


    public void getAsyncCall(){
        OkHttpClient httpClient = new OkHttpClient();
        String url = "https://api.yelp.com/v3/businesses/search?location=New York City, NYC, 350 5th Ave, New York, NY 10118";
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer qTudr1OHb2yp_BLjG5-Ql3FtZUTLIGgOZZSCGt9ckkQkiB_h1-djmLJXusaPhZrR2FIHrNAsnhzg2oJZMHjNMmS_fpM4mmrjh88VmX5nNeSI3AXu5DI_2v372JbKW3Yx")
                .build();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                Log.e("FAIL: ", "error in getting response using async okhttp call");
                e.printStackTrace();
            }
            @Override public void onResponse(Call call, Response response) throws IOException {
                ResponseBody responseBody = response.body();
                if (!response.isSuccessful()) {
                    throw new IOException("Error response " + response);
                }

                result = responseBody.toString();
                Log.i("SUCCESS: ", responseBody.string());
            }
        });
    }


}
