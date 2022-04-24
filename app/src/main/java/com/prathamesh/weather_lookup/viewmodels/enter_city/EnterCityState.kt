package com.prathamesh.weather_lookup.viewmodels.enter_city

import com.prathamesh.weather_lookup.models.ForecastModel

sealed class EnterCityState {
    object Start: EnterCityState()
    object Loading: EnterCityState()
    sealed class Error: EnterCityState() {
        object FetchCoordinatesFailed: Error()
        object FetchWeatherDetailsFailed: Error()
    }
    sealed class Stop: EnterCityState() {
        object Exit: Stop()
        data class Proceed(val forecast: ForecastModel): Stop()
    }
}