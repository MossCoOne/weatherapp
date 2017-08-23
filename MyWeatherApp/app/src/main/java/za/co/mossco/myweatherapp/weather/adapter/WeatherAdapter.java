package za.co.mossco.myweatherapp.weather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import za.co.mossco.myweatherapp.R;
import za.co.mossco.myweatherapp.utility.DateUtil;


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
        if (position == 0) {
            holder.dateTextView.setText("Today");
        } else {
            holder.dateTextView.setText(DateUtil.getCurrentDate(currentWeather.getDt()));
        }
        holder.weatherDescriptionTextView.setText(currentWeather.getWeather().get(0).getDescription());
        holder.highTemperatureTextView.setText(DateUtil.toCelsius(currentWeather.getTemp().getMax()));
        holder.lowTemperatureTextView.setText(DateUtil.toCelsius(currentWeather.getTemp().getMin()));
        String main = currentWeather.getWeather().get(0).getMain();

        if (main.equalsIgnoreCase("Clear")) {
            holder.weatherIconImageView.setImageResource(R.drawable.art_clear);
        } else if (main.equalsIgnoreCase("Rainy")) {
            holder.weatherIconImageView.setImageResource(R.drawable.ic_light_rain);
        }

    }

    @Override
    public int getItemCount() {
        return dailyWeatherList.size();
    }


}
