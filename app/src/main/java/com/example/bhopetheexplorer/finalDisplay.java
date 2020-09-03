package com.example.bhopetheexplorer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.bhopetheexplorer.exploreInside.NASAID;
import static com.example.bhopetheexplorer.exploreInside.MEDIATYPE;

public class finalDisplay extends AppCompatActivity {
private String nasa_id;
private String mediatype;
private ImageView imageView;
private ProgressBar loader;
    private static final String TAG = "finalDisplay";

private RequestQueue mqueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_display);
        Intent intent=getIntent();
        nasa_id=intent.getStringExtra(NASAID);
        Log.i(TAG,nasa_id);

        mediatype=intent.getStringExtra(MEDIATYPE);
        Log.i(TAG,mediatype);
        getSupportActionBar().hide();
        imageView=findViewById(R.id.final_display_image);
        loader=findViewById(R.id.progress_bar_final);
        mqueue= Volley.newRequestQueue(this);
        jsonParse();


    }
    private void jsonParse(){
        String url="https://images-api.nasa.gov/asset/"+nasa_id;
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            loader.setVisibility(View.INVISIBLE);
                            JSONObject jsonObject=response.getJSONObject("collection");
                            JSONArray jsonArray=jsonObject.getJSONArray("items");
//                            JSONObject object=jsonArray.getJSONObject(1);
//                             String imageurl=object.getString("href").replace("http","https");
                            String imageurl="";
                             for(int i=0;i<jsonArray.length();i++){
                                 JSONObject object1=jsonArray.getJSONObject(i);
                                 String s=object1.getString("href").replace("http","https");
                                 if(s.contains(".jpg")){
                                     imageurl=s;
                                     break;
                                 }
                             }

                             Log.i(TAG,imageurl);



                                 Picasso.with(finalDisplay.this).load(imageurl).placeholder(R.drawable.ic_launcher_foreground)
                                         .fit().centerCrop().into(imageView);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mqueue.add(request);

    }
}
