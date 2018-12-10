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
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class GeneratePlacesActivity extends AppCompatActivity {

    String result;
    //String url = "https://api.yelp.com/v3/businesses/search?";
    //String url = "https://api.yelp.com/v3/businesses/search?categories=restaurants&";
    String url = "https://api.yelp.com/v3/businesses/search?categories=";
    Button btn;
    private ArrayList<String> mBusinessNames = new ArrayList<>();
    private ArrayList<String> mImageURLs = new ArrayList<>();
    private  ArrayList<Business> mObjects = new ArrayList<Business>();
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

        String lat = globalInformation.getString("latitude");
        String lon = globalInformation.getString("longitude");
        String rad = globalInformation.getString("radius");
        String cuisines = globalInformation.getString("cuisines");
        String bud = globalInformation.getString("budget");
        String open = globalInformation.getString("open");

        Random rand = new Random();
        int n = rand.nextInt(4) + 1;
        String srt = "";

        if (n == 1){
            srt = "best_match";
        }
        else if(n == 2){
            srt = "distance";
        }
        else if (n == 3){
            srt = "review_count";
        }
        else{
            srt="rating";
        }



        //String url = "https://api.yelp.com/v3/businesses/search?categories=restaurants&";
        //url = url + "latitude="+ lat+ "&longitude="+ lon + "&radius=" + rad + "&sort_by=" + srt + "&term=food";
        if (cuisines.length() > 0)
            url = url + cuisines;
        else
            url = url + "restaurants";
        if (bud.length() > 0)
            url = url + "&price=" + bud;
        url = url + "&latitude="+ lat+ "&longitude="+ lon + "&radius=" + rad + "&sort_by=" + srt + "&term=food";
        Log.e("apicall" , url);

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



            Random rand = new Random();

            int n = rand.nextInt(2) + 1;

            ArrayDeque<String> sel = adapter.getTop();
            int i =0;

            while (i != n ){
                sel.remove();
                i++;
            }

            String p = sel.peek();

            Intent intent = new Intent( GeneratePlacesActivity.this, ChosenActivity.class);
            Business chosen = new Business();


            for(Business b: mObjects){
                if (b.getbName().equals(p)){
                    chosen = b;
                }
            }


            globalInformation.putString("chosenName", p);
            globalInformation.putString("chosenRating", chosen.getbRating());
            globalInformation.putString("chosenIMG", chosen.getbImageUrl());
            globalInformation.putString("chosenURL", chosen.getbUrl());
            globalInformation.putString("chosenCost", chosen.getbCost());
            globalInformation.putString("chosenPhone", chosen.getbPhone());
            globalInformation.putString("chosenCat", chosen.getbCategory());
            globalInformation.putString("chosenAdd", chosen.getbAdd());
            globalInformation.putString("chosenCity", chosen.getbCity());
            globalInformation.putString("chosenState", chosen.getbState());
            globalInformation.putString("chosenZip", chosen.getbZip());


            Log.i("CHOSEN: ", p);
            intent.putExtras(globalInformation);
            startActivity(intent);

        }
        else{
            Toast.makeText(this, "Need To Select Three Choices", Toast.LENGTH_SHORT).show();

        }

    }


    public void getAsyncCall(){
        OkHttpClient httpClient = new OkHttpClient();
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

                            JSONArray catArray = oneObject.getJSONArray("categories");
                            JSONObject catObj = catArray.getJSONObject(0);

                            JSONObject locObj = oneObject.getJSONObject("location");

                            // Pulling items from the array
                            final String businessName = oneObject.getString("name");
                            final String city = locObj.getString("city");
                            final String state = locObj.getString("state");
                            final String add1 = locObj.getString("address1");
                            final String zip = locObj.getString("zip_code");



                            final String category = catObj.getString("alias");
                            final String imageURL = oneObject.getString("image_url");
                            final String rating = oneObject.getString("rating");
                            final String cost = oneObject.getString("price");
                            final String url = oneObject.getString("url");
                            final String phone = oneObject.getString("phone");

                            final Business busObj = new Business();
                            busObj.setbImageUrl(imageURL);
                            busObj.setbName(businessName);
                            busObj.setbRating(rating);
                            busObj.setbCost(cost);
                            busObj.setbUrl(url);
                            busObj.setbPhone(phone);
                            busObj.setbCategory(category);
                            busObj.setbCity(city);
                            busObj.setbState(state);
                            busObj.setbZip(zip);
                            busObj.setbAdd(add1);




                            runOnUiThread (new Thread(new Runnable() {
                                public void run() {
                                    mBusinessNames.add(businessName);
                                    mCosts.add("Cost: " + cost);
                                    mRatings.add("Rating: " + rating + "/5");
                                    mImageURLs.add(imageURL);
                                    mObjects.add(busObj);
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
                            adapter = new RecyclerViewAdapter(mObjects, mBusinessNames, mCosts, mRatings, mImageURLs, c);
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
