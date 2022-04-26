package com.prathamesh.weather_lookup.viewmodels

import androidx.lifecycle.ViewModel
import com.prathamesh.weather_lookup.models.ForecastModel

class HomeViewModel : ViewModel() {

    private var forecast: ForecastModel? = null

    fun setForecast(forecast: ForecastModel) {
        this.forecast = forecast
    }

    fun getForecast() = forecast
}