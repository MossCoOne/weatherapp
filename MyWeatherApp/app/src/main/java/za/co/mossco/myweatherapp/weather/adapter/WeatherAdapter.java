package za.co.mossco.myweatherapp.weather.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import za.co.mossco.myweatherapp.R;
import za.co.mossco.myweatherapp.utility.Constants;
import za.co.mossco.myweatherapp.utility.DateUtil;


public class WeatherAdapter extends RecyclerView.Adapter<WeatherViewHolder> {
    private java.util.List<za.co.mossco.myweatherapp.model.bean.List> dailyWeatherList;
    private WeatherItemClickListener weatherItemClickListener;

    public WeatherAdapter(List<za.co.mossco.myweatherapp.model.bean.List> dailyWeatherList, WeatherItemClickListener weatherItemClickListener) {
        this.dailyWeatherList = dailyWeatherList;
        this.weatherItemClickListener = weatherItemClickListener;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.weather_item, parent, false);
        return new WeatherViewHolder(view, weatherItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        final za.co.mossco.myweatherapp.model.bean.List currentWeather = dailyWeatherList.get(position);
        if (position == 0) {
            holder.dateTextView.setText(R.string.today_text);
        } else if (position == 1) {
            holder.dateTextView.setText(R.string.tomorrow_text);
        } else {
            holder.dateTextView.setText(DateUtil.getCurrentDayOfWeek(currentWeather.getDt()));
        }
        holder.weatherDescriptionTextView.setText(currentWeather.getWeather().get(0).getDescription());
        holder.highTemperatureTextView.setText(String.format("%s%s", DateUtil.toCelsius(currentWeather.getTemp().getMax()), Constants.degreeSymbol));
        holder.lowTemperatureTextView.setText(String.format("%s%s", DateUtil.toCelsius(currentWeather.getTemp().getMin()), Constants.degreeSymbol));
        holder.weatherIconImageView.setImageResource(DateUtil.setImageIcon(currentWeather.getWeather().get(0).getMain()));
        holder.setCurrentConsultant(currentWeather);
    }

    @Override
    public int getItemCount() {
        return dailyWeatherList.size();
    }

    public interface WeatherItemClickListener {
        void onConsultantClicked(za.co.mossco.myweatherapp.model.bean.List clickedConsultant);
    }

}
