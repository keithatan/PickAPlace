package com.just.pickaplace;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class GeneratePlacesActivity extends AppCompatActivity {

    String result;
    Button btn;
    private ArrayList<String> mBusinessNames = new ArrayList<>();
    private ArrayList<String> mImageURLs = new ArrayList<>();
    private ArrayList<String> mCosts = new ArrayList<>();
    RecyclerViewAdapter adapter;

    private ArrayList<String> mRatings = new ArrayList<>();
    final Context c = this;
    Bundle globalInformation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_places);

        Intent i = getIntent();

        globalInformation = i.getExtras();



        btn = findViewById(R.id.button2);
        getAsyncCall();

        result = "";

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickButton();
            }
        });
    }

    private void clickButton(){

        if (adapter.getTop().size() == 3){
            //Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();

            Intent

        }
        else{
            Toast.makeText(this, "Need To Select Three Choices", Toast.LENGTH_SHORT).show();

        }

        //Intent intent = new Intent( GrabLocationActivity.this, GetWalkingDistance.class);
    /*
        Bundle extras = new Bundle();
        extras.putString("latitude", "" + latitude);
        extras.putString("longitude", "" + longitude);
        extras.putString("location", location);
        intent.putExtras(extras);
        startActivity(intent);
        */
        /*startActivity(new Intent(ACTIVITYNAME,)).WithBundle(mAdapter.GetSelectedItems);

         */


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
                result = response.body().string();
                Log.i("SUCCESS: ", result);

                try {
                    JSONObject jsonResponse = new JSONObject(result);

                    Log.i("RESULT: " , result);
                    JSONArray jArray = jsonResponse.getJSONArray("businesses");

                    Log.i("ARRAY: ", jArray.toString());


                    //textView.setText(jArray.toString());

                    System.out.print(jArray.toString());
                    for (int i=0; i < 5; i++)
                    {
                        try {
                            JSONObject oneObject = jArray.getJSONObject(i);
                            Log.i("Object: ", oneObject.toString());

                            // Pulling items from the array
                            final String businessName = oneObject.getString("name");
                            final String imageURL = oneObject.getString("image_url");
                            final String rating = oneObject.getString("rating");
                            final String cost = oneObject.getString("price");

                            runOnUiThread (new Thread(new Runnable() {
                                public void run() {
                                    mBusinessNames.add(businessName);
                                    mCosts.add("Cost: " + cost);
                                    mRatings.add("Rating: " + rating + "/5");
                                    mImageURLs.add(imageURL);
                                    //textView.setText(textView.getText() + " " + businessName);
                                }
                            }));

                            Log.i("Business: ", businessName);
                            // String  = oneObject.getString("anotherSTRINGNAMEINtheARRAY");
                        } catch (JSONException e) {
                            e.printStackTrace();
                            // Oops
                        }
                    }

                    runOnUiThread (new Thread(new Runnable() {
                        public void run() {
                            RecyclerView rcView = findViewById(R.id.recycler_v);
                            adapter = new RecyclerViewAdapter(mBusinessNames, mCosts, mRatings, mImageURLs, c);
                            rcView.setAdapter(adapter);
                            rcView.setLayoutManager(new LinearLayoutManager(c));
                            //textView.setText(textView.getText() + " " + businessName);
                        }
                    }));


                }
                catch (Exception e){
                    Log.i("Oh ", "No");

                }


            }
        });
    }


}
