package za.co.mossco.myweatherapp.weather;


import za.co.mossco.myweatherapp.model.bean.WeatherResponse;

public interface WeatherContract {

    interface View {
        void showWeather(java.util.List<WeatherResponse> weatherResponseList);

        void showProgressDialog();

        void dismissProgressDialog();

        void showFailedToLoadWeatherErrorMessage();

        void showErrorOccurredMessage(String errorMessage);
    }

    interface UserActionsListener {
        void loadWeather(String cityName);
    }
}
