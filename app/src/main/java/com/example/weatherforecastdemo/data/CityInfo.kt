package com.example.weatherforecastdemo.data

import kotlinx.serialization.Serializable

@Serializable
data class CityInfo(
    val id: Int,
    val name: String,
    val coord: Coordinates,
    val country: String,
    val population: Int,
    val timezone: Int,
    val sunrise: Int,
    val sunset: Int
)