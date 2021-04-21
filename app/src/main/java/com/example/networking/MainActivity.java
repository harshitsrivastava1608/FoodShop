package com.example.networking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    List<Shop> shopList=new ArrayList<>();
    private RecyclerView recyclerViewH,recyclerViewV;ShopAdapter shopAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewH=findViewById(R.id.rvH);
        recyclerViewV=findViewById(R.id.rvV);
        setTitle("FoodShop");
        LinearLayoutManager horizontalLayout=new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());

        Shop shop=new Shop("12","hs","5","100","io");
        Log.v("myshop",shop.id);

        RequestQueue requestQueue;
        requestQueue=Volley.newRequestQueue(this);
        JsonObjectRequest jsonArrayRequest=new JsonObjectRequest(Request.Method.GET,
                "http://13.235.250.119/v2/restaurants/fetch_result/",
                null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("tring123","response");
                        try {
                            Log.d("tring123",response.toString());
                            if(response.getJSONObject("data").getBoolean("success")){
                                JSONArray shopObject=response.getJSONObject("data").getJSONArray("data");
                                for (int i=0;i<shopObject.length();i++){
                                    Shop shop=new Shop("","","","","");
                                    shop.setId(shopObject.getJSONObject(i).getString("id"));
                                    shop.setName(shopObject.getJSONObject(i).getString("name"));
                                    shop.setRating(shopObject.getJSONObject(i).getString("rating"));
                                    shop.setCost(shopObject.getJSONObject(i).getString("cost_for_one"));
                                    shop.setImage(shopObject.getJSONObject(i).getString("image_url"));

                                    try {

                                        shopList.add(new Shop(shop.getId(), shop.getName(), shop.getRating(), shop.getCost(), shop.getImage()));
                                       //Log.d("answer", shopList);

                                    }catch (Exception e){
                                        Log.d("error123",e.getMessage());
                                    }
                                }
                                shopAdapter=new ShopAdapter(shopList);
                                recyclerViewH.setLayoutManager(horizontalLayout);
                                recyclerViewH.setAdapter(shopAdapter);
                                recyclerViewV.setLayoutManager(layoutManager);
                                recyclerViewV.setAdapter(shopAdapter);
                            }
                                Log.d("tring456","**"+response.getJSONObject("data").getBoolean("success"));
                        } catch (Exception e) {
                            Log.d("tring123","error");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tring123","**"+error.toString());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String,String> headers=new HashMap<String, String>();
                headers.put("Content-type","application/json");
                headers.put("token","1cdb9aa872f874");
                return headers;
            }
        };

        requestQueue.add(jsonArrayRequest);


    }
}