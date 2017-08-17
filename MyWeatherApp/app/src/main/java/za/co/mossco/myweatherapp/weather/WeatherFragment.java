package za.co.mossco.myweatherapp.weather;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import za.co.mossco.myweatherapp.R;
import za.co.mossco.myweatherapp.model.WeatherRepository;
import za.co.mossco.myweatherapp.model.WeatherRepositoryImpl;
import za.co.mossco.myweatherapp.model.bean.WeatherResponse;


public class WeatherFragment extends Fragment implements WeatherContract.View {
    WeatherRepository weatherRepository;
    private WeatherPresenter weatherPresenter;

    public WeatherFragment() {
        // Required empty public constructor
    }

    String CityName = "London";

    public static WeatherFragment newInstance() {
        WeatherFragment fragment = new WeatherFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        weatherRepository = new WeatherRepositoryImpl();
//        // Inflate the layout for this fragment
//        try {
//            if (weatherResponseList() != null) {
//                try {
//                    weatherResponseList();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
//        weatherPresenter = new WeatherPresenter();
//        weatherPresenter.loadWeather(CityName);

        weatherPresenter = new WeatherPresenter(this, new WeatherRepositoryImpl());
        weatherPresenter.loadWeather("Mosscow");

        //weatherRepository.getWeatherByCityName();
        return view;
    }


//    List<WeatherResponse> weatherResponseList() throws JSONException {
//        Gson gson = new Gson();
//        String jsonStr = getWeatherResponse();
//        JSONObject response = new JSONObject(jsonStr);
//        JSONObject city = response.getJSONObject("city");
//        String cityName = city.getString("name");
//        JSONArray jsonArray = response.getJSONArray("list");
//        String aa = jsonArray.toString();
//        WeatherResponse weatherResponse = gson.fromJson(aa, WeatherResponse.class);
//        List<WeatherResponse> responseList = new ArrayList<>();
//        responseList.add(weatherResponse);
//        return responseList;
//    }
//
//    String getWeatherResponse() {
//        String json = null;
//        try {
//            InputStream is = getActivity().getResources().openRawResource(R.raw.weatherresponse);
//            int size = is.available();
//            byte[] buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            json = new String(buffer, "UTF-8");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        return json;
//    }


    @Override
    public void showWeather(List<WeatherResponse> weatherResponse) {

    }
}
