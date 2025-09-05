package com.example.weatherforecastdemo.data

import kotlinx.serialization.Serializable

@Serializable
data class WeatherForecastResponse (
    val cod: Int,
    val message: Int,
    val cnt: Int,
    val list: List<WeatherForecastItem>,
    val city: CityInfo
)


/*
{
    "cod": "200",
    "message": 0,
    "cnt": 40,
    "list": [
    {
        "dt": 1755313200,
        "main": {
                 "temp": 289.87,
                 "feels_like": 289.76,
                 "temp_min": 289.87,
                 "temp_max": 289.87,
                 "pressure": 1027,
                 "sea_level": 1027,
                 "grnd_level": 1023,
                 "humidity": 83,
                 "temp_kf": 0
                 },
        "weather": [
        {
            "id": 800,
            "main": "Clear",
            "description": "clear sky",
            "icon": "01n"
        }
        ],
        "clouds": {"all": 9},
        "wind": {
                  "speed": 3.98,
                  "deg": 53,
                  "gust": 6.85
                  },
        "visibility": 10000,
        "pop": 0,
        "sys": {"pod": "n"},
        "dt_txt": "2025-08-16 03:00:00"
    }],
    "city": {
             "id": 2643743,
             "name": "London",
             "coord": {
                       "lat": 51.5085,
                       "lon": -0.1257
             },
    "country": "GB",
    "population": 1000000,
    "timezone": 3600,
    "sunrise": 1755319669,
    "sunset": 1755372120
    }
}
*/