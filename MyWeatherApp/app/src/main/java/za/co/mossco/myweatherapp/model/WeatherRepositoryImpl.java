package za.co.mossco.myweatherapp.model;


import android.support.annotation.NonNull;

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
                    public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                        List<WeatherResponse> responseList = new ArrayList<>();
                        if (response.isSuccessful()) {
                            responseList.add(response.body());
                            weatherDataCallback.onSuccess(responseList);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                        weatherDataCallback.onErrorOccurred(t.getMessage());
                    }
                });
    }

}
