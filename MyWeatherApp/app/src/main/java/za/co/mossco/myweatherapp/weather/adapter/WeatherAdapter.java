package za.co.mossco.myweatherapp.weather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import za.co.mossco.myweatherapp.R;


public class WeatherAdapter extends RecyclerView.Adapter<WeatherViewHolder> {
    public java.util.List<za.co.mossco.myweatherapp.model.bean.List> dailyWeatherList;

    public WeatherAdapter(List<za.co.mossco.myweatherapp.model.bean.List> dailyWeatherList) {
        this.dailyWeatherList = dailyWeatherList;
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.weather_row, parent, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        final za.co.mossco.myweatherapp.model.bean.List currentWeather = dailyWeatherList.get(position);
        holder.dateTextView.setText(currentWeather.getDt().toString());
        holder.weatherDescriptionTextView.setText(currentWeather.getWeather().get(0).getDescription());
        holder.highTemperatureTextView.setText(toCelcius(currentWeather.getTemp().getMax()));
        holder.lowTemperatureTextView.setText(toCelcius(currentWeather.getTemp().getMin()));
        String main = currentWeather.getWeather().get(0).getMain();

        if (main.equalsIgnoreCase("Clear")) {
            holder.weatherIconImageView.setImageResource(R.drawable.art_clear);
        } else if (main.equalsIgnoreCase("Rainy")) {
            holder.weatherIconImageView.setImageResource(R.drawable.ic_light_rain);
        }

    }

    private String toCelcius(double degrees) {
        return String.valueOf((int) (degrees - 273.15D));
    }

    @Override
    public int getItemCount() {
        return dailyWeatherList.size();
    }
}
