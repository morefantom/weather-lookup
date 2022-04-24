package com.prathamesh.weather_lookup.viewmodels.enter_city

sealed class EnterCityState {
    object Start: EnterCityState()
    object Loading: EnterCityState()
    sealed class Error: EnterCityState() {
        object FetchCoordinatesFailed: Error()
        object FetchWeatherDetailsFailed: Error()
    }
    sealed class Stop: EnterCityState() {
        object Exit: Stop()
        object Proceed: Stop()
    }
}