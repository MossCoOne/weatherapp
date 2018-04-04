package za.co.mossco.myweatherapp.network;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import za.co.mossco.myweatherapp.model.bean.WeatherResponse;

public interface WeatherServiceApi {
    @GET("daily?")
    Call<WeatherResponse> getWeatherByCity(@Query("q") String cityName,
                                           @Query("appid") String appId);
}

