package com.prathamesh.weather_lookup.models

import com.google.gson.annotations.SerializedName

data class WeatherModel(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("main") var main: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("icon") var icon: String? = null
)