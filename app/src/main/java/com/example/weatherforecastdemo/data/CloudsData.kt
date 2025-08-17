package com.example.weatherforecastdemo.data

import kotlinx.serialization.Serializable

@Serializable
data class CloudsData(
    val all: Int
)