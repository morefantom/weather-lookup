package com.prathamesh.weather_lookup.datastores.remotestores

import com.prathamesh.weather_lookup.models.CityModel
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoCodingService {

    @GET("/geo/1.0/direct")
    suspend fun get(
        @Query("q") city: String,
        @Query("appid") apiKey: String
    ): List<CityModel>
}