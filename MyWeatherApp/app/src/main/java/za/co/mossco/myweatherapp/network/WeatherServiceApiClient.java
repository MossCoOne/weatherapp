package za.co.mossco.myweatherapp.network;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import za.co.mossco.myweatherapp.utility.Constants;

public class WeatherServiceApiClient {
    private static WeatherServiceApi weatherServiceApi;

    private WeatherServiceApiClient() {

    }

    public static WeatherServiceApi getInstance() {
        Retrofit retrofit;
        if (weatherServiceApi == null) {
            Gson gson = new GsonBuilder()
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.OPEN_WEATHER_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            weatherServiceApi = retrofit.create(WeatherServiceApi.class);
        }
        return weatherServiceApi;
    }
}
