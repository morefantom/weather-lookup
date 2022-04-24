package com.prathamesh.weather_lookup.datastores.remotestores

import com.prathamesh.weather_lookup.models.ForecastModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastService {

    @GET("/data/2.5/forecast/hourly")
    suspend fun get(
        @Query("appid") apiKey: String,
        @Query("lon") longitude: String,
        @Query("lat") latitude: String
    ) : Result<ForecastModel>
}