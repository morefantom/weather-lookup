package com.prathamesh.weather_lookup.models

import com.google.gson.annotations.SerializedName

data class ForecastModel(
    @SerializedName("cod")
    var cod: String? = null,
    @SerializedName("message")
    var message: Double? = null,
    @SerializedName("cnt")
    var cnt: Int? = null,
    @SerializedName("list")
    var list: ArrayList<ListModel> = arrayListOf(),
)
