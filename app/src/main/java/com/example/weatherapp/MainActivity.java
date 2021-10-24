package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cityName) TextView cityName;
    @BindView(R.id.testText) TextView test;

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.temperature) void getTemperature(View view){
        Toast.makeText(this,"I'm alive!", Toast.LENGTH_SHORT).show();
        test.setText((cityName.getText().toString()));
        System.out.println("It was clicked!");
    }

    @OnClick(R.id.forecast) void getForecast(View view){
        test.setText((cityName.getText().toString()));
        Toast.makeText(this, "I\'m alive as well", Toast.LENGTH_SHORT).show();
    }
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}