package com.prathamesh.weather_lookup.datastores

import com.prathamesh.weather_lookup.models.CityModel
import kotlinx.coroutines.flow.Flow

interface GeoCodingDataStore {

    fun get(cityName: String, apiKey: String): Flow<List<CityModel>>
}