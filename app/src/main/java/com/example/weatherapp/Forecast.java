package com.example.weatherapp;

import java.util.Date;

public class Forecast {
    public Date date;
    public double temp;
    public String description;
    public double windSpeed;

    public Forecast(Date date, double temp, String description, double windSpeed) {
        this.date = date;
        this.temp = temp;
        this.description = description;
        this.windSpeed = windSpeed;
    }

}
