package za.co.mossco.myweatherapp.weatherdetail;

import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import za.co.mossco.myweatherapp.R;
import za.co.mossco.myweatherapp.utility.Constants;
import za.co.mossco.myweatherapp.utility.DateUtil;

public class WeatherDetailActivity extends AppCompatActivity {

    private static String currentWeather = "CurrentWeather";
    za.co.mossco.myweatherapp.model.bean.List currentWeatherList;
    za.co.mossco.myweatherapp.model.bean.List currentWeatherSeleted;

    public static Intent getInstance(Context context, za.co.mossco.myweatherapp.model.bean.List list) {
        Intent detailIntent = new Intent(context, WeatherDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(currentWeather, new Gson().toJson(list));
        detailIntent.putExtras(bundle);
        return detailIntent;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putString(currentWeather, new Gson().toJson(currentWeatherSeleted));
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);
        String jsonWeather = getIntent().getStringExtra(currentWeather);
        initializeUI(jsonWeather);
    }


    void initializeUI(String jsonWeather) {
        currentWeatherSeleted = new Gson().fromJson(jsonWeather, za.co.mossco.myweatherapp.model.bean.List.class);
        TextView detailDay = (TextView) findViewById(R.id.detail_day);
        detailDay.setText(DateUtil.getCurrentDayOfWeek(currentWeatherSeleted.getDt()));
        TextView detailDate = (TextView) findViewById(R.id.detail_date);
        detailDate.setText(DateUtil.getDayAndMonth(currentWeatherSeleted.getDt()));
        TextView detailHighTemperature = (TextView) findViewById(R.id.detail_temperature_high);
        detailHighTemperature.setText(DateUtil.toCelsius(currentWeatherSeleted.getTemp().getMax()) + Constants.degreeSymbol);
        TextView detailLowTemperature = (TextView) findViewById(R.id.detail_temperature_low);
        detailLowTemperature.setText(DateUtil.toCelsius(currentWeatherSeleted.getTemp().getMin()) + Constants.degreeSymbol);
        TextView detailDescription = (TextView) findViewById(R.id.detail_description);
        detailDescription.setText(currentWeatherSeleted.getWeather().get(0).getDescription());
        TextView detailHumidity = (TextView) findViewById(R.id.detail_humidity);
        detailHumidity.setText(getString(R.string.humidity_text) + " " + String.valueOf(currentWeatherSeleted.getHumidity()));
        TextView detailPressure = (TextView) findViewById(R.id.detail_pressure);
        detailPressure.setText(getString(R.string.pressure_text) + " " + String.valueOf(currentWeatherSeleted.getPressure()));
        TextView detailWindSpeed = (TextView) findViewById(R.id.detail_wind);
        detailWindSpeed.setText(getString(R.string.wind_speed_text) + " " + String.valueOf(currentWeatherSeleted.getSpeed()));
        ImageView detailImage = (ImageView) findViewById(R.id.detailDescriptionImage);
        detailImage.setImageResource(DateUtil.setImageIcon(currentWeatherSeleted.getWeather().get(0).getMain()));

    }


}
