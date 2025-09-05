package com.example.weatherforecastdemo.data

import kotlinx.serialization.Serializable

@Serializable
data class MainData(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val humidity: Int,
    val sea_level: Int,
    val grnd_level: Int,
    val temp_kf: Double? = 0.0
)

