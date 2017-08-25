package za.co.mossco.myweatherapp.weather;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

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
import za.co.mossco.myweatherapp.model.bean.List;
import za.co.mossco.myweatherapp.model.bean.WeatherResponse;
import za.co.mossco.myweatherapp.weather.adapter.WeatherAdapter;
import za.co.mossco.myweatherapp.weather.adapter.WeatherAdapter.WeatherItemClickListener;
import za.co.mossco.myweatherapp.weatherdetail.WeatherDetailActivity;


public class WeatherFragment extends Fragment implements WeatherContract.View {
    WeatherRepository weatherRepository;
    private WeatherPresenter weatherPresenter;
    private RecyclerView weaklyWeatherRecyclerView;
    private WeatherAdapter weatherAdapter;
    private ProgressDialog weatherLoadingProgress;

    public WeatherFragment() {
        // Required empty public constructor
    }


    public static WeatherFragment newInstance() {
        WeatherFragment fragment = new WeatherFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weatherRepository = new WeatherRepositoryImpl();
        weatherPresenter = new WeatherPresenter(this, new WeatherRepositoryImpl());
        weatherPresenter.loadWeather("Johannesburg");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        weaklyWeatherRecyclerView = (RecyclerView) view.findViewById(R.id.weekly_weather_recyclerview);

        return view;
    }


    @Override
    public void showWeather(java.util.List<WeatherResponse> weatherResponseList) {
        weatherAdapter = new WeatherAdapter(weatherResponseList.get(0).getList(),weatherItemClickListener);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        weaklyWeatherRecyclerView.setLayoutManager(linearLayoutManager);
        weaklyWeatherRecyclerView.setItemAnimator(new DefaultItemAnimator());
        weaklyWeatherRecyclerView.setAdapter(weatherAdapter);
    }

    @Override
    public void showProgressDialog() {
        weatherLoadingProgress = new ProgressDialog(getContext());
        weatherLoadingProgress.setTitle(getString(R.string.weather_loading));
        weatherLoadingProgress.setMessage(getString(R.string.please_wait_message));
        weatherLoadingProgress.setIndeterminate(true);
        weatherLoadingProgress.show();
    }

    @Override
    public void dismissProgressDialog() {
        weatherLoadingProgress.dismiss();
    }


    WeatherItemClickListener weatherItemClickListener = new WeatherItemClickListener() {
        @Override
        public void onConsultantClicked(za.co.mossco.myweatherapp.model.bean.List clickedWeather) {
            startActivity(WeatherDetailActivity.getInstance( getContext(),clickedWeather));
        }
    };
}
