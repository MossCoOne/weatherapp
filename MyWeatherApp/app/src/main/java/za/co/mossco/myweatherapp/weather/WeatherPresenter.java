package za.co.mossco.myweatherapp.weather;


import java.util.List;

import za.co.mossco.myweatherapp.model.WeatherRepository;
import za.co.mossco.myweatherapp.model.bean.WeatherResponse;

public class WeatherPresenter implements WeatherContract.UserActionsListener {
    //TODO Inject this with Dagger
    WeatherRepository weatherRepository;
    WeatherContract.View weatherView;

    public WeatherPresenter(WeatherContract.View weatherView, WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
        this.weatherView = weatherView;
    }

    @Override
    public void loadWeather(String cityName) {
        weatherRepository.getWeatherByCityName(cityName, new WeatherRepository.WeatherDataCallback() {
            @Override
            public void onWeatherDataLoaded(List<WeatherResponse> weatherResponseList) {
                weatherView.showWeather(weatherResponseList);
            }

            @Override
            public void onErrorOccurred(int errorCode) {

            }
        });
    }
}
