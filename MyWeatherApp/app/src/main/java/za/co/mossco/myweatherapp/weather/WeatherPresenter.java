package za.co.mossco.myweatherapp.weather;


import za.co.mossco.myweatherapp.model.WeatherRepository;
import za.co.mossco.myweatherapp.model.WeatherRepositoryImpl;
import za.co.mossco.myweatherapp.model.bean.WeatherResponse;

public class WeatherPresenter implements WeatherContract.UserActionsListener {
    //TODO Inject this with Dagger
    private WeatherRepository weatherRepository;
    private WeatherContract.View weatherView;

    WeatherPresenter(WeatherContract.View weatherView) {
        weatherRepository = new WeatherRepositoryImpl();
        this.weatherView = weatherView;
    }

    @Override
    public void loadWeather(String cityName) {
        weatherView.showProgressDialog();
        weatherRepository.getWeatherByCityName(cityName, new WeatherRepository.WeatherDataCallback() {
            @Override
            public void displayLoadedWeather(java.util.List<WeatherResponse> responseList) {
                if (!responseList.isEmpty()) {
                    weatherView.showWeather(responseList);
                    weatherView.dismissProgressDialog();
                }else {
                    weatherView.showFailedToLoadWeatherErrorMessage();
                }
            }

            @Override
            public void onErrorOccurred(String errorMessage) {
                weatherView.showErrorOccuredMessage(errorMessage);
            }
        });
    }
}
