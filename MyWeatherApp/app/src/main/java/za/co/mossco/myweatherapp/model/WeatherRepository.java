package za.co.mossco.myweatherapp.model;

import java.util.List;

import za.co.mossco.myweatherapp.model.bean.WeatherResponse;

public interface WeatherRepository {

    interface WeatherDataCallback {
        void onWeatherDataLoaded(List<WeatherResponse> listList);

        void onErrorOccurred(int errorCode);
    }

    void getWeatherByCityName(String cityName, WeatherDataCallback weatherDataCallback);
}
