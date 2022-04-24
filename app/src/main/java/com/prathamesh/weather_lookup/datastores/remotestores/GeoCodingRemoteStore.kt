package com.prathamesh.weather_lookup.datastores.remotestores

import com.prathamesh.weather_lookup.BuildConfig
import com.prathamesh.weather_lookup.datastores.GeoCodingDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GeoCodingRemoteStore : GeoCodingDataStore {

    private val service: GeoCodingService by lazy {
        RetrofitBuilder.buildGeneric(GeoCodingService::class.java, BuildConfig.OPENWEATHER_BASE_URL)
    }

    override fun get(cityName: String, apiKey: String) = flow {
        val result = service.get(city = cityName, apiKey = apiKey)
        emit(result.getOrThrow())
    }.flowOn(Dispatchers.IO)
}