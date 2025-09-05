package com.example.weatherforecastdemo.data

import kotlinx.serialization.Serializable

@Serializable
data class WeatherForecastItem(
    val dt: Int,
    val main: MainData,
    val weather: List<WeatherData>,
    val clouds: CloudsData,
    val wind: WindData,
    val visibility: Int,
    val pop: Double,
    val rain: RainData? = null,
    val snow: SnowData? = null,
    val sys: SysData,
    val dt_txt: String
)