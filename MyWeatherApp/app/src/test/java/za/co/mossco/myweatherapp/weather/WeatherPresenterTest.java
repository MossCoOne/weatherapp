/*
* Copyright (c) 2017 Barclays Bank Plc, All Rights Reserved.
*
* This code is confidential to Barclays Bank Plc and shall not be disclosed
* outside the Bank without the prior written permission of the Director of
* CIO
*
* In the event that such disclosure is permitted the code shall not be copied
* or distributed other than on a need-to-know basis and any recipients may be
* required to sign a confidentiality undertaking in favor of Barclays Bank
* Plc.
*/
package za.co.mossco.myweatherapp.weather;


import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import za.co.mossco.myweatherapp.model.WeatherRepository;

import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;

public class WeatherPresenterTest {

    @Mock
    WeatherRepository weatherRepository;
    @Mock
    WeatherContract.View weatherView;
    @Mock
    WeatherPresenter weatherPresenter;
    @Captor
    private ArgumentCaptor<WeatherRepository.WeatherDataCallback> argumentCaptor;


    @Before
    public void setupWeatherPresenter() {
        //inject mocks to this object
        MockitoAnnotations.initMocks(this);
        weatherPresenter = new WeatherPresenter(weatherView,weatherRepository);
    }


    @Test
    public void shouldLoadWeatherWhenAppStarts() {
        weatherPresenter.loadWeather("jhjhjhjhj");
        verify(weatherRepository).getWeatherByCityName(anyString(),argumentCaptor.capture());
        //argumentCaptor.getValue().onWeatherDataLoaded(anyList());
       // verify(weatherView).showWeather(anyList());
    }
}
