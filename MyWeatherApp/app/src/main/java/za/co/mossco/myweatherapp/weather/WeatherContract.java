package za.co.mossco.myweatherapp.weather;


import za.co.mossco.myweatherapp.model.bean.WeatherResponse;

public interface WeatherContract {

    interface View {
        void showWeather(java.util.List<WeatherResponse> weatherResponseList);

        void showProgressDialog();

        void dismissProgressDialog();
    }

    interface UserActionsListener {
        void loadWeather(String cityName);
    }
}
