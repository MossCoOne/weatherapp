package za.co.mossco.myweatherapp.model;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import za.co.mossco.myweatherapp.model.bean.WeatherResponse;
import za.co.mossco.myweatherapp.network.WeatherServiceApi;
import za.co.mossco.myweatherapp.network.WeatherServiceApiClient;

public class WeatherRepositoryImpl implements WeatherRepository {
    //TODO inject WeatherServiceApiClient

    private WeatherServiceApi weatherServiceApi = WeatherServiceApiClient.getInstance();
    String APP_ID = "";

    @Override
    public void getWeatherByCityName(String cityName, final WeatherDataCallback weatherDataCallback) {
        weatherServiceApi
                .getWeatherByCity(cityName, APP_ID)
                .enqueue(new Callback<WeatherResponse>() {
                    @Override
                    public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                        java.util.List<WeatherResponse> responseList = new ArrayList<WeatherResponse>();
                        if (response.isSuccessful()){
                            responseList.add(0,response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherResponse> call, Throwable t) {

                    }
                });
    }

}
