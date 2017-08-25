package za.co.mossco.myweatherapp.weather.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import za.co.mossco.myweatherapp.R;
import za.co.mossco.myweatherapp.weather.adapter.WeatherAdapter.WeatherItemClickListener;

public class WeatherViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ImageView weatherIconImageView;
    TextView dateTextView, weatherDescriptionTextView, highTemperatureTextView, lowTemperatureTextView;
    WeatherItemClickListener weatherItemClickListener;
    za.co.mossco.myweatherapp.model.bean.List currentWeatherList;

    public WeatherViewHolder(View itemView,WeatherItemClickListener weatherItemClickListener) {
        super(itemView);
        weatherIconImageView = (ImageView) itemView.findViewById(R.id.weather_icon);
        dateTextView = (TextView) itemView.findViewById(R.id.date);
        weatherDescriptionTextView = (TextView) itemView.findViewById(R.id.weather_description);
        highTemperatureTextView = (TextView) itemView.findViewById(R.id.high_temperature);
        lowTemperatureTextView = (TextView) itemView.findViewById(R.id.low_temperature);
        this.weatherItemClickListener = weatherItemClickListener;
        itemView.setOnClickListener(this);
    }

    public void setCurrentConsultant(za.co.mossco.myweatherapp.model.bean.List currentWeatherList) {
        this.currentWeatherList = currentWeatherList;
    }

    @Override
    public void onClick(View view) {
        weatherItemClickListener.onConsultantClicked(currentWeatherList);
    }
}
