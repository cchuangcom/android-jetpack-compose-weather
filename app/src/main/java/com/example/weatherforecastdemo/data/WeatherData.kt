package com.example.weatherforecastdemo.data

import kotlinx.serialization.Serializable

@Serializable
data class WeatherData(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)