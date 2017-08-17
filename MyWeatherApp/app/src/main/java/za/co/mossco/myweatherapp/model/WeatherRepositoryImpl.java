package za.co.mossco.myweatherapp.model;


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
    String APP_ID = "b1b15e88fa797225412429c1c50c122a1";

    @Override
    public void getWeatherByCityName(String cityName, final WeatherDataCallback weatherDataCallback) {
        weatherServiceApi
                .getWeatherByCity(cityName, APP_ID)
                .enqueue(new Callback<List<WeatherResponse>>() {
                    @Override
                    public void onResponse(Call<List<WeatherResponse>> call, Response<List<WeatherResponse>> response) {
                        weatherDataCallback.onWeatherDataLoaded(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<WeatherResponse>> call, Throwable t) {

                    }
                });
    }
}
