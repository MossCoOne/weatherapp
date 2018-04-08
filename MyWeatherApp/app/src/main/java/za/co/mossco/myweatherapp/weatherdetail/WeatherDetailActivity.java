package za.co.mossco.myweatherapp.weatherdetail;

import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.Objects;

import za.co.mossco.myweatherapp.R;
import za.co.mossco.myweatherapp.utility.Constants;
import za.co.mossco.myweatherapp.utility.DateUtil;
import za.co.mossco.myweatherapp.weather.WeatherFragment;

public class WeatherDetailActivity extends AppCompatActivity {

    private static String currentWeather = "CurrentWeather";
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
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.daily_weather));
        String jsonWeather = getIntent().getStringExtra(currentWeather);
        initializeUI(jsonWeather);
    }


    void initializeUI(String jsonWeather) {
        currentWeatherSeleted = new Gson().fromJson(jsonWeather, za.co.mossco.myweatherapp.model.bean.List.class);
        TextView detailDay = findViewById(R.id.detail_day);
        detailDay.setText(String.format("%s - %s", DateUtil.getCurrentDayOfWeek(currentWeatherSeleted.getDt()), WeatherFragment.LOCATION));
        TextView detailDate = findViewById(R.id.detail_date);
        detailDate.setText(DateUtil.getDayAndMonth(currentWeatherSeleted.getDt()));
        TextView detailHighTemperature = findViewById(R.id.detail_temperature_high);
        detailHighTemperature.setText(String.format("%s%s", DateUtil.toCelsius(currentWeatherSeleted.getTemp().getMax()), Constants.degreeSymbol));
        TextView detailLowTemperature = findViewById(R.id.detail_temperature_low);
        detailLowTemperature.setText(String.format("%s%s", DateUtil.toCelsius(currentWeatherSeleted.getTemp().getMin()), Constants.degreeSymbol));
        TextView detailDescription = findViewById(R.id.detail_description);
        detailDescription.setText(currentWeatherSeleted.getWeather().get(0).getDescription());
        TextView detailHumidity = findViewById(R.id.detail_humidity);
        detailHumidity.setText(String.format("%s %s", getString(R.string.humidity_text), String.valueOf(currentWeatherSeleted.getHumidity())));
        TextView detailPressure = findViewById(R.id.detail_pressure);
        detailPressure.setText(String.format("%s %s", getString(R.string.pressure_text), String.valueOf(currentWeatherSeleted.getPressure())));
        TextView detailWindSpeed = findViewById(R.id.detail_wind);
        detailWindSpeed.setText(String.format("%s %s", getString(R.string.wind_speed_text), String.valueOf(currentWeatherSeleted.getSpeed())));
        ImageView detailImage = findViewById(R.id.detailDescriptionImage);
        detailImage.setImageResource(DateUtil.setImageIcon(currentWeatherSeleted.getWeather().get(0).getMain()));
    }
}
