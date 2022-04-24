package com.prathamesh.weather_lookup.models

import com.google.gson.annotations.SerializedName

data class CloudsModel(
    @SerializedName("all") var all: Int? = null
)
