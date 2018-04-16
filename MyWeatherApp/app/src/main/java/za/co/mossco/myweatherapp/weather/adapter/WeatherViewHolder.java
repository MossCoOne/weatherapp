package za.co.mossco.myweatherapp.weather.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import za.co.mossco.myweatherapp.databinding.WeatherItemBinding;
import za.co.mossco.myweatherapp.weather.adapter.WeatherAdapter.WeatherItemClickListener;

public class WeatherViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ImageView weatherIconImageView;
    TextView dateTextView, weatherDescriptionTextView, highTemperatureTextView, lowTemperatureTextView;
    private WeatherItemClickListener weatherItemClickListener;
    private za.co.mossco.myweatherapp.model.bean.List currentWeatherList;


    WeatherViewHolder(WeatherItemBinding weatherItemBinding, WeatherItemClickListener weatherItemClickListener) {
        super(weatherItemBinding.getRoot());
        weatherIconImageView = weatherItemBinding.weatherIcon;
        dateTextView = weatherItemBinding.date;
        weatherDescriptionTextView = weatherItemBinding.weatherDescription;
        highTemperatureTextView = weatherItemBinding.highTemperature;
        lowTemperatureTextView = weatherItemBinding.lowTemperature;
        this.weatherItemClickListener = weatherItemClickListener;
        weatherItemBinding.getRoot().setOnClickListener(this);
    }

    public void setCurrentConsultant(za.co.mossco.myweatherapp.model.bean.List currentWeatherList) {
        this.currentWeatherList = currentWeatherList;
    }

    @Override
    public void onClick(View view) {
        weatherItemClickListener.onConsultantClicked(currentWeatherList);
    }
}
