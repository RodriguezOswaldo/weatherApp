package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeatherActivity extends AppCompatActivity {
    RequestQueue requestQueue;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cityName) TextView cityName;
    @BindView(R.id.results) TextView results;

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.temperature) void getTemperature(View view){
         getTempCity();
    }
    @BindView(R.id.forecast) Button forecastButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    public void getTempCity() {
        if (cityName.getText().toString().isEmpty()) {
            Toast.makeText(this,"field must not be empty!", Toast.LENGTH_SHORT).show();
        } else {
            String c = cityName.getText().toString();
            String url = "https://api.openweathermap.org/data/2.5/weather?q=" + c + "&APPID=8d7178b81fed26ae734b0d4364f47036";
            requestQueue = Volley.newRequestQueue(this);
            // new JSONObject call
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
                            Log.d("JSON ERROR : ", "Error Respuesta en JSON: " + error.getMessage());
                        }
                    }
            );
            //add json array to the queue instead of using thread
            requestQueue.add(jsArrayRequest);
        }
    }

    public void JsonParser(JSONObject response){
        Toast.makeText(WeatherActivity.this, "Getting the temperature for that city ", Toast.LENGTH_LONG).show();
        // for para recorrer el response y extraer los datos
        try {
            // sacando el objeto main
            JSONObject main = response.getJSONObject("main");
            // obteniendo el valor de temp dentro de main : "temp": 300.15 ...
            String temperature = main.getString("temp");
            // cambiando el valor del EditText
            results.setText(temperature);
            forecastButton.setEnabled(true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.forecast)
    void getForecast(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("city", cityName.getText().toString());
        startActivity(intent);

        //Toast.makeText(this, "I\'m alive as well", Toast.LENGTH_SHORT).show();
    }


}