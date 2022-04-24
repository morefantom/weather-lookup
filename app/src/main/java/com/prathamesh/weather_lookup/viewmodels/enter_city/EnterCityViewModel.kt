package com.prathamesh.weather_lookup.viewmodels.enter_city

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prathamesh.weather_lookup.viewmodels.State

class EnterCityViewModel: ViewModel() {

    val state: LiveData<State<EnterCityState>>
        get() = _state

    private val _state = MutableLiveData<State<EnterCityState>>()

    init {
        setStateStart()
    }

    fun onClickLookup(city: String) {

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

    private fun setStateStopExit() {
        _state.value = State(EnterCityState.Stop.Exit)
    }

    private fun setStateStopProceed() {
        _state.value = State(EnterCityState.Stop.Proceed)
    }
}