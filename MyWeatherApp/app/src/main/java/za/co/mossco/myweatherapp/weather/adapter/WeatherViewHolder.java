package za.co.mossco.myweatherapp.weather.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import za.co.mossco.myweatherapp.R;

public class WeatherViewHolder extends RecyclerView.ViewHolder {
    ImageView weatherIconImageView;
    TextView dateTextView, weatherDescriptionTextView, highTemperatureTextView, lowTemperatureTextView;

    public WeatherViewHolder(View itemView) {
        super(itemView);
        weatherIconImageView = (ImageView) itemView.findViewById(R.id.weather_icon);
        dateTextView = (TextView) itemView.findViewById(R.id.date);
        weatherDescriptionTextView = (TextView) itemView.findViewById(R.id.weather_description);
        highTemperatureTextView = (TextView) itemView.findViewById(R.id.high_temperature);
        lowTemperatureTextView = (TextView) itemView.findViewById(R.id.low_temperature);
    }
}
