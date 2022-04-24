package com.prathamesh.weather_lookup.viewmodels.enter_city

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prathamesh.weather_lookup.BuildConfig
import com.prathamesh.weather_lookup.datastores.ForecastDataStore
import com.prathamesh.weather_lookup.datastores.GeoCodingDataStore
import com.prathamesh.weather_lookup.models.ForecastModel
import com.prathamesh.weather_lookup.viewmodels.State
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.launch

class EnterCityViewModel(
    private val geoCodingDataStore: GeoCodingDataStore,
    private val forecastDataStore: ForecastDataStore
) : ViewModel() {

    val state: LiveData<State<EnterCityState>>
        get() = _state

    private val _state = MutableLiveData<State<EnterCityState>>()

    init {
        setStateStart()
    }

    fun onClickLookup(cityName: String) = viewModelScope.launch {
        setStateLoading()

        val apiKey = BuildConfig.OPENWEATHER_BASE_URL

        val cityList = geoCodingDataStore
            .get(cityName = cityName, apiKey = apiKey)
            .catch {
                it.localizedMessage
                "string"
            }
            .singleOrNull()

        if (cityList.isNullOrEmpty()) {
            setStateErrorFetchCoordinatesFailed()
            return@launch
        }

        val (_, lon, lat) = cityList.first()
        val forecast = forecastDataStore
            .get(longitude = lon, latitude = lat, apiKey = apiKey)
            .catch {}
            .singleOrNull()

        if (forecast == null) {
            setStateErrorFetchWeatherDetailsFailed()
            return@launch
        }

        setStateStopProceed(forecast)
    }

    private fun setStateStart() {
        _state.value = State(EnterCityState.Start)
    }

    private fun setStateLoading() {
        _state.value = State(EnterCityState.Loading)
    }

    private fun setStateErrorFetchCoordinatesFailed() {
        _state.value = State(EnterCityState.Error.FetchCoordinatesFailed)
    }

    private fun setStateErrorFetchWeatherDetailsFailed() {
        _state.value = State(EnterCityState.Error.FetchWeatherDetailsFailed)
    }

    private fun setStateStopProceed(forecast: ForecastModel) {
        _state.value = State(EnterCityState.Stop.Proceed(forecast))
    }
}