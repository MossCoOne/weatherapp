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
package za.co.mossco.myweatherapp.model;


import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import za.co.mossco.myweatherapp.network.WeatherServiceApi;
import za.co.mossco.myweatherapp.network.WeatherServiceApiClient;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class WeatherRepositoryImplTest {

    @Mock
    WeatherServiceApi weatherServiceApi;
    @Mock
    WeatherRepository weatherRepository;

    @Captor
    private ArgumentCaptor<WeatherRepository.WeatherDataCallback> weatherDataCallbackArgumentCaptor;
    public void setUpWeatherRepositoryTest() {
        MockitoAnnotations.initMocks(this);
        weatherServiceApi = WeatherServiceApiClient.getInstance();
    }
@Test
    public void showInvokeTheLoadWeatherNetworkService(){
        verify(weatherServiceApi).getWeatherByCity(anyString(),anyString());
        //verify(weatherRepository,times(1)).getWeatherByCityName("hhjhjj",weatherDataCallbackArgumentCaptor.capture());
    }
}
