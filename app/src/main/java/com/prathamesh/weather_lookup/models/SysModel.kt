package com.prathamesh.weather_lookup.models

import com.google.gson.annotations.SerializedName

data class SysModel(
    @SerializedName("sunrise") val sunrise: Long = 0,
    @SerializedName("sunset") val sunset: Long = 0
)