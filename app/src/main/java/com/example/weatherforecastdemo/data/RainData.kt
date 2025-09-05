package com.example.weatherforecastdemo.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RainData(
    @SerialName("3h")
    val volumeIn3Hours: Double
)
