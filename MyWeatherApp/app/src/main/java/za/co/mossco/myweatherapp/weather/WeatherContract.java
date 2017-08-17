package za.co.mossco.myweatherapp.weather;


import java.util.List;

import za.co.mossco.myweatherapp.model.bean.WeatherResponse;

public interface WeatherContract {

    interface View {
        void showWeather(List<WeatherResponse> weatherResponse);
    }

    interface UserActionsListener {
        void loadWeather(String cityName);
    }
}
