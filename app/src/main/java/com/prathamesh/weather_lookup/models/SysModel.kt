package com.prathamesh.weather_lookup.models

import com.google.gson.annotations.SerializedName

data class SysModel(
    @SerializedName("pod") var pod: String? = null
)