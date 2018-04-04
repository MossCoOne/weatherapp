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
import za.co.mossco.myweatherapp.utility.Constants;

public class WeatherRepositoryImpl implements WeatherRepository {
    //TODO inject WeatherServiceApiClient

    private WeatherServiceApi weatherServiceApi = WeatherServiceApiClient.getInstance();

    @Override
    public void getWeatherByCityName(String cityName, final WeatherDataCallback weatherDataCallback) {
        weatherServiceApi.getWeatherByCity(cityName, Constants.APP_ID)
                .enqueue(new Callback<WeatherResponse>() {
                    @Override
                    public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                        List<WeatherResponse> responseList = new ArrayList<>();
                        if (response.isSuccessful() ) {
                            responseList.add(response.body());
                            weatherDataCallback.displayLoadedWeather(responseList);
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherResponse> call, Throwable t) {

                    }
                });
    }

}
