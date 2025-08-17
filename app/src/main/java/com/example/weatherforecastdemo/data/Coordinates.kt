package com.example.weatherforecastdemo.data

import kotlinx.serialization.Serializable

@Serializable
data class Coordinates(
    val lon: Double,
    val lat: Double
)