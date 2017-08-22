package za.co.mossco.myweatherapp.weather;


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
        weatherView.showProgressDialog();
        weatherRepository.getWeatherByCityName(cityName, new WeatherRepository.WeatherDataCallback() {
            @Override
            public void onWeatherDataLoaded(java.util.List<WeatherResponse> responseList) {
                weatherView.showWeather(responseList);
                weatherView.dismissProgressDialog();
            }

            @Override
            public void onErrorOccurred(int errorCode) {

            }
        });
    }
}
