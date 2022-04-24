package com.prathamesh.weather_lookup.models

import com.google.gson.annotations.SerializedName

data class CityModel(
    @SerializedName("name") val name: String,
    @SerializedName("lat") val latitude: String,
    @SerializedName("lon") val longitude: String,
)