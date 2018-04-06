package za.co.mossco.myweatherapp.model;

import java.util.List;

import za.co.mossco.myweatherapp.model.bean.WeatherResponse;

public interface WeatherRepository {

    interface WeatherDataCallback {
        void onSuccess(List<WeatherResponse> listList);

        void onErrorOccurred(String errorMessage);
    }

    void getWeatherByCityName(String cityName, WeatherDataCallback weatherDataCallback);
}
