package com.example.bhopetheexplorer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class exploreInside extends AppCompatActivity implements exploreInsideAdapter.OnIemClickListener {

    private static final String TAG = "exploreInside";
private RecyclerView recyclerView;
private exploreInsideAdapter adapter;
private RequestQueue mqueue;
private ArrayList<recyclerview_item> mList;
public static final String NASAID="nasaid";
public static final String MEDIATYPE="mediatype";
EditText SearchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_inside);
        recyclerView=findViewById(R.id.recycler_view);


        recyclerView.setLayoutManager(new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false));
        SearchText=findViewById(R.id.edit_text);
        mqueue= Volley.newRequestQueue(this);
        mList=new ArrayList<>();
        jsonParse();
        SearchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i(TAG,s.toString());


            }
        });

    }
    private void jsonParse(){
        String url="https://images-api.nasa.gov/search?q=moon";
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject jsonObject=response.getJSONObject("collection");
                            JSONArray jsonArray=jsonObject.getJSONArray("items");

                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject items =jsonArray.getJSONObject(i);
                                JSONArray data=items.getJSONArray("data");
                                JSONObject obj=data.getJSONObject(0);
                                String title=obj.getString("title");


                                String nasa_id=obj.getString("nasa_id");
                                String media_type=obj.getString("media_type");
                                JSONArray links=items.getJSONArray("links");
                                JSONObject obj2=links.getJSONObject(0);
                                String href=obj2.getString("href");
                                Log.i(TAG,nasa_id);
                                Log.i(TAG,title);

                                Log.i(TAG,media_type);
                                Log.i(TAG,href);

                                mList.add(new recyclerview_item(nasa_id,title,media_type,href));


                            }

                            adapter=new exploreInsideAdapter(exploreInside.this,mList);
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemClickListener(exploreInside.this);




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

    @Override
    public void onItemClick(int position) {
Toast.makeText(this,"touched",Toast.LENGTH_SHORT).show();
    }
}
