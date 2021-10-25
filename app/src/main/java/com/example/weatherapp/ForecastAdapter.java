package com.example.weatherapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
// adapter used to fill data from the rec
public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.MyViewHolder> {
    public List<Forecast> items;
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date, weather, description, speed;
        public MyViewHolder(View view) {
            super(view);
            date = (TextView) view.findViewById(R.id.date_forecast);
            weather = (TextView) view.findViewById(R.id.weather_forecast);
            description = (TextView) view.findViewById(R.id.description_forecast);
            speed = (TextView) view.findViewById(R.id.speed_wind);
        }
    }


    public ForecastAdapter(List<Forecast> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Forecast forecast = items.get(position);
        final String temp = "" + forecast.temp;
        holder.weather.setText(temp);
        holder.date.setText(forecast.date.toString());
        holder.description.setText(forecast.description);
        final String speed = "" + forecast.windSpeed;
        holder.speed.setText(speed);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
