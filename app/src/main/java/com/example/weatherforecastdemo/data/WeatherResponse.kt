package com.example.weatherforecastdemo.data

import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse (
    val coord: Coordinates,
    val weather: List<WeatherData>,
    val base: String,
    val main: MainData,
    val visibility: Int,
    val wind: WindData,
    val clouds: CloudsData,
    val dt: Int,
    val sys: SysData,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int
)

@Serializable
data class MainData(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val humidity: Int,
    val sea_level: Int,
    val grnd_level: Int
)


@Serializable
data class SysData(
    val country: String,
    val sunrise: Int,
    val sunset: Int
)


/*
{
  "coord" : {
    "lon" : -0.1257,
    "lat" : 51.5085
  },
  "weather" : [ {
    "id" : 800,
    "main" : "Clear",
    "description" : "clear sky",
    "icon" : "01n"
  } ],
  "base" : "stations",
  "main" : {
    "temp" : 290.25,
    "feels_like" : 290.16,
    "temp_min" : 290.25,
    "temp_max" : 290.25,
    "pressure" : 1027,
    "humidity" : 82,
    "sea_level" : 1027,
    "grnd_level" : 1023
  },
  "visibility" : 10000,
  "wind" : {
    "speed" : 3.81,
    "deg" : 53,
    "gust" : 6.37
  },
  "clouds" : {
    "all" : 8
  },
  "dt" : 1755308622,
  "sys" : {
    "country" : "GB",
    "sunrise" : 1755319669,
    "sunset" : 1755372120
  },
  "timezone" : 3600,
  "id" : 2643743,
  "name" : "London",
  "cod" : 200
}
*/