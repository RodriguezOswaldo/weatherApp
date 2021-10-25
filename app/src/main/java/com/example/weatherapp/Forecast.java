package com.example.weatherapp;

import java.util.Date;

public class Forecast {
    public Date date;
    public float temp;
    public String description;
    public float windSpeed;

    public Forecast(Date date, float temp, String description, float windSpeed) {
        this.date = date;
        this.temp = temp;
        this.description = description;
        this.windSpeed = windSpeed;
    }

}
