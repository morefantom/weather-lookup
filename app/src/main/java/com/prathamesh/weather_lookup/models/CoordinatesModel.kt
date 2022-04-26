package com.prathamesh.weather_lookup.models

import com.google.gson.annotations.SerializedName

data class CoordinatesModel(
    @SerializedName("lon")
    val longitude: Float? = null,
    @SerializedName("lat")
    val latitude: Float? = null
)