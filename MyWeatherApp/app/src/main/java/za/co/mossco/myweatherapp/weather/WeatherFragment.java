package za.co.mossco.myweatherapp.weather;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import za.co.mossco.myweatherapp.R;
import za.co.mossco.myweatherapp.model.WeatherRepository;
import za.co.mossco.myweatherapp.model.WeatherRepositoryImpl;
import za.co.mossco.myweatherapp.model.bean.WeatherResponse;


public class WeatherFragment extends Fragment implements WeatherContract.View {
    WeatherRepository weatherRepository;
    private WeatherPresenter weatherPresenter;

    public WeatherFragment() {
        // Required empty public constructor
    }


    public static WeatherFragment newInstance() {
        WeatherFragment fragment = new WeatherFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        weatherRepository = new WeatherRepositoryImpl();

        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        weatherPresenter = new WeatherPresenter(this, new WeatherRepositoryImpl());
        weatherPresenter.loadWeather("Johannesburg");
        return view;
    }




    @Override
    public void showWeather(java.util.List<WeatherResponse> weatherResponseList) {

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void dismissProgressDialog() {

    }
}
