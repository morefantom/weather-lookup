package com.prathamesh.weather_lookup.models

import com.google.gson.annotations.SerializedName

data class ListModel(
    @SerializedName("dt") var dt: Int? = null,
    @SerializedName("main") var main: MainModel? = MainModel(),
    @SerializedName("weather") var weather: ArrayList<WeatherModel> = arrayListOf(),
    @SerializedName("clouds") var clouds: CloudsModel? = CloudsModel(),
    @SerializedName("wind") var wind: WindModel? = WindModel(),
    @SerializedName("visibility") var visibility: Int? = null,
    @SerializedName("pop") var pop: Double? = null,
    @SerializedName("sys") var sys: SysModel? = SysModel(),
    @SerializedName("dt_txt") var dtTxt: String? = null
)