package za.co.mossco.myweatherapp.weatherdetail;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import za.co.mossco.myweatherapp.R;
import za.co.mossco.myweatherapp.databinding.ActivityWeatherDetailBinding;
import za.co.mossco.myweatherapp.utility.Constants;
import za.co.mossco.myweatherapp.utility.DateUtil;
import za.co.mossco.myweatherapp.utility.StringsUtil;

public class WeatherDetailActivity extends AppCompatActivity {

    private static String currentWeather = "current_weather";
    private static String currentLocation = "current_location";
    za.co.mossco.myweatherapp.model.bean.List currentWeatherSeleted;
    // private WeatherDetailActivityB
    private ActivityWeatherDetailBinding activityWeatherDetailBinding;

    public static Intent getInstance(Context context, za.co.mossco.myweatherapp.model.bean.List list, String location) {
        Intent detailIntent = new Intent(context, WeatherDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(currentWeather, new Gson().toJson(list));
        bundle.putString(currentLocation, location);
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
        activityWeatherDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_weather_detail);
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        String jsonWeather = getIntent().getStringExtra(currentWeather);
        String location = getIntent().getStringExtra(currentLocation);
        TextView toolBarTitle = findViewById(R.id.tv_tool_bar_title);
        toolBarTitle.setText(getString(R.string.daily_weather));
        initializeUI(jsonWeather, location);
    }


    void initializeUI(String jsonWeather, String location) {
        currentWeatherSeleted = new Gson().fromJson(jsonWeather, za.co.mossco.myweatherapp.model.bean.List.class);
        activityWeatherDetailBinding.detailDay.setText(String.format("%s - %s", DateUtil.getCurrentDayOfWeek(currentWeatherSeleted.getDt()), location));
        activityWeatherDetailBinding.detailDate.setText(DateUtil.getDayAndMonth(currentWeatherSeleted.getDt()));
        activityWeatherDetailBinding.detailTemperatureHigh.setText(String.format("%s%s", DateUtil.toCelsius(currentWeatherSeleted.getTemp().getMax()), Constants.degreeSymbol));
        activityWeatherDetailBinding.detailTemperatureLow.setText(String.format("%s%s", DateUtil.toCelsius(currentWeatherSeleted.getTemp().getMin()), Constants.degreeSymbol));
        activityWeatherDetailBinding.detailDescription.setText(currentWeatherSeleted.getWeather().get(0).getDescription());
        activityWeatherDetailBinding.detailHumidity.setText(String.format("%s %s", getString(R.string.humidity_text), String.valueOf(currentWeatherSeleted.getHumidity())));
        activityWeatherDetailBinding.detailPressure.setText(String.format("%s %s", getString(R.string.pressure_text), String.valueOf(currentWeatherSeleted.getPressure())));
        activityWeatherDetailBinding.detailWind.setText(String.format("%s %s", getString(R.string.wind_speed_text), String.valueOf(currentWeatherSeleted.getSpeed())));
        Picasso.get()
                .load(StringsUtil.getIconUrl(currentWeatherSeleted.getWeather().get(0).getIcon()))
                .error(R.drawable.ic_error)
                .placeholder(R.drawable.ic_image_placeholder)
                .into(activityWeatherDetailBinding.imDetailDescriptionImage);
    }
}
