package com.example.bhopetheexplorer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.bhopetheexplorer.MainActivity.EXTRADATE;

public class apod_detail extends AppCompatActivity {
    private TextView apod_title;
    private TextView apod_description;
    private ImageView apod_image;
    private VideoView apod_video;
    private RequestQueue requestQueue;
    private String date;
    private ProgressBar loader;
    String title;
    String desc;
    String media_type;
    String mediaurl;
    private static final String TAG = "apod_detail";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apod_detail);
        apod_title = findViewById(R.id.apod_title);
        apod_description = findViewById(R.id.apod_description);
        apod_image = findViewById(R.id.apod_image);
        apod_video = findViewById(R.id.apod_video);
        loader=findViewById(R.id.progress_bar);
        requestQueue = Volley.newRequestQueue(this);
getSupportActionBar().hide();

        Intent i = getIntent();

        date = i.getStringExtra(EXTRADATE);
        Jsonparse();


    }

    public void Jsonparse() {
        final String url = "https://api.nasa.gov/planetary/apod?api_key=1ukpztvNawRagRW1CaCb91ZAY52IstUvjkkoy7z6&date=" + date;
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            loader.setVisibility(View.GONE);
                             title=response.getString("title");
                             desc=response.getString("explanation");
                             media_type=response.getString("media_type");
                             mediaurl =response.getString("url");
                            Log.i(TAG,title);
                            Log.i(TAG,desc);
                            Log.i(TAG,media_type);
                            Log.i(TAG,mediaurl);
                            apod_title.setText(title);
                            apod_description.setText(desc);
                            if(media_type.contentEquals("image")){
                                        Log.i(TAG,"yes");
                                apod_video.setVisibility(View.GONE);
                                Picasso.with(apod_detail.this).load(mediaurl).fit().placeholder(R.drawable.ic_launcher_foreground).centerCrop().into(apod_image);


                            }else if(media_type.contentEquals("video")){
                                apod_image.setVisibility(View.GONE);
                                apod_video.setVisibility(View.VISIBLE);
                                Uri uri=Uri.parse(mediaurl);
                                apod_video.setVideoURI(uri);
                                MediaController mediaController=new MediaController(apod_detail.this);
                                apod_video.setMediaController(mediaController);
                                mediaController.setAnchorView(apod_video);

                            }
                            else {
                                Toast.makeText(apod_detail.this,"no image ",Toast.LENGTH_SHORT);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
requestQueue.add(request);

    }


}
