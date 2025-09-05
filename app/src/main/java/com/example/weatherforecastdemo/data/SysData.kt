package com.example.weatherforecastdemo.data

import kotlinx.serialization.Serializable

@Serializable
data class SysData(
    val pod: String? = null,
    val country: String? = null,
    val sunrise: Int? = 0,
    val sunset: Int? = 0
)