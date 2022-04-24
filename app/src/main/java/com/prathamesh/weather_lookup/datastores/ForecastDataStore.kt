package com.prathamesh.weather_lookup.datastores

import com.prathamesh.weather_lookup.models.ForecastModel
import kotlinx.coroutines.flow.Flow

interface ForecastDataStore {

    fun get(longitude: String, latitude: String, apiKey: String): Flow<ForecastModel>
}