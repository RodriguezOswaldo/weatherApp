package com.example.weatherapp;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ForecastAdapter adapter;
    RequestQueue requestQueue;
    List<Forecast> items =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            String city = bundle.getString("city");
            getForecast(city);
        }

        recyclerView = (RecyclerView) findViewById(R.id.todos_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }


    public void setForecastAdapter() {
        adapter = new ForecastAdapter(items);
        recyclerView.setAdapter(adapter);
    }

    public void getForecast(String city) {
        String url = "https://api.openweathermap.org/data/2.5/forecast?q=" + city + "&APPID=8d7178b81fed26ae734b0d4364f47036";
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest(
                Request.Method.GET, url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.w("REST", response.toString());
                        JsonParser(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("JSON ERROR : ", "Error making the JSON Request: " + error.getMessage());
                    }
                }
        );
        requestQueue.add(jsArrayRequest);
    }
    public void JsonParser(JSONObject response){
        items.clear();
        try {
            JSONArray list = response.getJSONArray("list");

            for (int i = 0; i < list.length(); i++) {
                JSONObject item = list.getJSONObject(i);

                int unixDate = item.getInt("dt");

                JSONObject main = item.getJSONObject("main");
                double temp = main.getDouble("temp");

                JSONArray weather = item.getJSONArray("weather");
                String description = weather.getJSONObject(0).getString("description");

                JSONObject wind = item.getJSONObject("wind");
                double speed = wind.getDouble("speed");

                Date date = new java.util.Date(unixDate * 1000L);
                Forecast forecast = new Forecast(date, temp, description, speed);
                items.add(forecast);
            }

            setForecastAdapter();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
