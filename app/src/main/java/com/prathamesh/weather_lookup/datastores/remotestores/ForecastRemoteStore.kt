package com.prathamesh.weather_lookup.datastores.remotestores

import com.prathamesh.weather_lookup.BuildConfig
import com.prathamesh.weather_lookup.datastores.ForecastDataStore
import kotlinx.coroutines.flow.flow

class ForecastRemoteStore : ForecastDataStore {

    private val service: ForecastService by lazy {
        RetrofitBuilder.buildGeneric(ForecastService::class.java, BuildConfig.OPENWEATHER_BASE_URL_PRO)
    }

    override fun get(longitude: String, latitude: String, apiKey: String) = flow {
        val result = service.get(longitude = longitude, latitude = latitude, apiKey = apiKey)
        emit(result)
    }
}