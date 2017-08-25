package za.co.mossco.myweatherapp.weatherdetail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;

import za.co.mossco.myweatherapp.R;

public class WeatherDetailActivity extends AppCompatActivity {


    public static Intent getInstance(Context context,za.co.mossco.myweatherapp.model.bean.List currentWeatherList) {
        Intent detailIntent = new Intent(context, WeatherDetailActivity.class);

//        Bundle bundle = new Bundle();
//        bundle.putString(CONSULTANT_KEY, new Gson().toJson(consultant));
//        detailIntent.putExtras(bundle);
        return detailIntent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);
    }
}
