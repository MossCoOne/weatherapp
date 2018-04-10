package za.co.mossco.myweatherapp.weather;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import za.co.mossco.myweatherapp.R;
import za.co.mossco.myweatherapp.model.bean.WeatherResponse;
import za.co.mossco.myweatherapp.weather.adapter.WeatherAdapter;
import za.co.mossco.myweatherapp.weather.adapter.WeatherAdapter.WeatherItemClickListener;
import za.co.mossco.myweatherapp.weatherdetail.WeatherDetailActivity;


public class WeatherFragment extends Fragment implements WeatherContract.View {
    private final String TAG = WeatherFragment.class.getCanonicalName();
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 111;
    protected WeatherPresenter weatherPresenter;
    private RecyclerView weaklyWeatherRecyclerView;
    private ProgressDialog weatherLoadingProgress;
    public static String LOCATION = "Johannesburg";

    private FusedLocationProviderClient fusedLocationClient;

    public WeatherFragment() {
        // Required empty public constructor
    }


//    public static WeatherFragment newInstance() {
//        WeatherFragment fragment = new WeatherFragment();
//        return fragment;
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weatherPresenter = new WeatherPresenter(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!checkPermissions()) {
            Log.i(TAG, "Inside onStart function; requesting permission when permission is not available");
            requestPermissions();
        } else {
            Log.i(TAG, "Inside onStart function; getting location when permission is already available");
            getLastLocation();
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        weaklyWeatherRecyclerView = view.findViewById(R.id.weekly_weather_recyclerview);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        return view;
    }


    @Override
    public void showWeather(java.util.List<WeatherResponse> weatherResponseList) {
        WeatherAdapter weatherAdapter = new WeatherAdapter(weatherResponseList.get(0).getList(), weatherItemClickListener);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        weaklyWeatherRecyclerView.setLayoutManager(linearLayoutManager);
        weaklyWeatherRecyclerView.setItemAnimator(new DefaultItemAnimator());
        weaklyWeatherRecyclerView.setAdapter(weatherAdapter);
    }

    @Override
    public void showProgressDialog() {
        weatherLoadingProgress = new ProgressDialog(getActivity());
        weatherLoadingProgress.setTitle(getString(R.string.weather_loading));
        weatherLoadingProgress.setMessage(getString(R.string.please_wait_message));
        weatherLoadingProgress.setIndeterminate(true);
        weatherLoadingProgress.show();
    }

    @Override
    public void dismissProgressDialog() {
        weatherLoadingProgress.dismiss();
    }

    @Override
    public void showFailedToLoadWeatherErrorMessage() {
        //Failed to load weather screen
    }

    @Override
    public void showErrorOccurredMessage(String errorMessage) {

    }


    WeatherItemClickListener weatherItemClickListener = new WeatherItemClickListener() {
        @Override
        public void onConsultantClicked(za.co.mossco.myweatherapp.model.bean.List clickedWeather) {
            startActivity(WeatherDetailActivity.getInstance(getActivity(), clickedWeather));
        }
    };


    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                        Manifest.permission.ACCESS_FINE_LOCATION);

        if (shouldProvideRationale) {
            startLocationPermissionRequest();
        } else {
            startLocationPermissionRequest();
        }
    }

    private void startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                REQUEST_PERMISSIONS_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.length <= 0) {
            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            } else {
                Log.i(TAG, "User denied permission.");
            }
        }
    }


    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        fusedLocationClient.getLastLocation()
                .addOnCompleteListener(getActivity(), new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            Location location = task.getResult();
                            String latitude = String.format("%s: %f", "Lati", location.getLatitude());
                            String longitude = String.format("%s: %f", "Longi", location.getLongitude());
                            Toast.makeText(getActivity(), latitude, Toast.LENGTH_LONG).show();
                            Toast.makeText(getActivity(), longitude, Toast.LENGTH_LONG).show();
                            weatherPresenter.loadWeather(location.getLatitude(), location.getLongitude());
                        } else {
                            Log.i(TAG, "Error while getting location");
                            System.out.println(TAG + task.getException());
                        }
                    }
                });
    }
}
