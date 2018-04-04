package za.co.mossco.myweatherapp.weather;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import za.co.mossco.myweatherapp.R;
import za.co.mossco.myweatherapp.model.bean.WeatherResponse;
import za.co.mossco.myweatherapp.weather.adapter.WeatherAdapter;
import za.co.mossco.myweatherapp.weather.adapter.WeatherAdapter.WeatherItemClickListener;
import za.co.mossco.myweatherapp.weatherdetail.WeatherDetailActivity;


public class WeatherFragment extends Fragment implements WeatherContract.View {
    protected WeatherPresenter weatherPresenter;
    private RecyclerView weaklyWeatherRecyclerView;
    private ProgressDialog weatherLoadingProgress;
    public static String LOCATION = "Johannesburg";

    public WeatherFragment() {
        // Required empty public constructor
    }


//    public static WeatherFragment newInstance() {
//        WeatherFragment fragment = new WeatherFragment();
//        return fragment;
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weatherPresenter = new WeatherPresenter(this);
        weatherPresenter.loadWeather(LOCATION);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        weaklyWeatherRecyclerView = view.findViewById(R.id.weekly_weather_recyclerview);

        return view;
    }


    @Override
    public void showWeather(java.util.List<WeatherResponse> weatherResponseList) {
        WeatherAdapter weatherAdapter = new WeatherAdapter(weatherResponseList.get(0).getList(), weatherItemClickListener);
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

    @Override
    public void showFailedToLoadWeatherErrorMessage() {
        //Failed to load weather screen
    }

    @Override
    public void showErrorOccurredMessage(String errorMessage) {

    }


    WeatherItemClickListener weatherItemClickListener = new WeatherItemClickListener() {
        @Override
        public void onConsultantClicked(za.co.mossco.myweatherapp.model.bean.List clickedWeather) {
            startActivity(WeatherDetailActivity.getInstance(getContext(), clickedWeather));
        }
    };

}
