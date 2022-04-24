package com.prathamesh.weather_lookup.viewmodels.enter_city

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prathamesh.weather_lookup.datastores.ForecastDataStore
import com.prathamesh.weather_lookup.datastores.GeoCodingDataStore
import java.lang.IllegalArgumentException

class EnterCityViewModelFactory(
    private val geoCodingDataStore: GeoCodingDataStore,
    private val forecastDataStore: ForecastDataStore
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EnterCityViewModel::class.java)) {
            return EnterCityViewModel(geoCodingDataStore, forecastDataStore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}