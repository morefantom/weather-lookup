package com.prathamesh.weather_lookup.models

import com.google.gson.annotations.SerializedName

data class ForecastModel(
    @SerializedName("coord")
    val coordinates: CoordinatesModel? = null,
    @SerializedName("weather")
    val weatherList: List<WeatherModel>? = null,
    @SerializedName("base")
    val base: String? = null,
    @SerializedName("main")
    val main: MainModel? = null,
    @SerializedName("visibility")
    val visibility: Int = 0,
    @SerializedName("wind")
    val wind: WindModel? = null,
    @SerializedName("clouds")
    val clouds: CloudsModel? = null,
    @SerializedName("dt")
    val dt: Long = 0,
    @SerializedName("sys")
    val sys: SysModel? = null,
    @SerializedName("timezone")
    val timezone: Int = 10800,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("cod")
    val cod: String? = null,
)
