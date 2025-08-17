package com.example.weatherforecastdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecastdemo.WeatherApi
import com.example.weatherforecastdemo.data.WeatherForecastResponse
import com.example.weatherforecastdemo.data.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private final val CurrentDayType = "Current Day"
    private val _weather = MutableStateFlow<WeatherResponse?>(null)
    private val _forecast = MutableStateFlow<WeatherForecastResponse?>(null)
    val weather: StateFlow<WeatherResponse?> = _weather
    val forecast: StateFlow<WeatherForecastResponse?> = _forecast

    fun fetchWeatherData(apiKey: String, city: String, type: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val weatherApi = WeatherApi()
            weatherApi.getWeatherFromApi(apiKey, city, type)
            weatherApi.setOnResponseListener( object : WeatherApi.IWeatherApiListener {
                override fun onSuccessWeatherResponse(weather: WeatherResponse) {
                    _weather.value = weather
                }
                override fun onSuccessForecastResponse(forecast: WeatherForecastResponse) {
                    _forecast.value = forecast
                }
            })

            /* Test Weather API Data
            if (type == CurrentDayType) {
                _weather.value = weatherApi.weatherParser()
                //_weather.value = result
            } else {
                _forecast.value = weatherApi.weatherForecastParser()
            }*/
        }
    }
}