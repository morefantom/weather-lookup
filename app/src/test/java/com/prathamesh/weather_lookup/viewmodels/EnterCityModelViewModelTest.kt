package com.prathamesh.weather_lookup.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.prathamesh.weather_lookup.MainCoroutineScopeRule
import com.prathamesh.weather_lookup.datastores.ForecastDataStore
import com.prathamesh.weather_lookup.datastores.GeoCodingDataStore
import com.prathamesh.weather_lookup.models.CityModel
import com.prathamesh.weather_lookup.models.ForecastModel
import com.prathamesh.weather_lookup.viewmodels.enter_city.EnterCityState
import com.prathamesh.weather_lookup.viewmodels.enter_city.EnterCityViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class EnterCityModelViewModelTest {

    lateinit var sut: EnterCityViewModel

    private val cityName = "Mumbai"

    private val city = CityModel(cityName, "0", "0")
    private val forecast = ForecastModel()

    @Mock
    lateinit var geoCodingDataStore: GeoCodingDataStore

    @Mock
    lateinit var forecastDataStore: ForecastDataStore

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineScopeRule: MainCoroutineScopeRule = MainCoroutineScopeRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        sut = EnterCityViewModel(geoCodingDataStore, forecastDataStore)
    }

    @Test
    fun test_constructor_should_setInitialStateToStart() {
        val initialState = sut.state.value?.getIfNotConsumed()

        Assert.assertEquals(EnterCityState.Start, initialState)
    }

    @Test
    fun test_onClickLookup_should_setStateLoading_when_invoked() {
        val sequenceOfState = mutableListOf<State<EnterCityState>>()
        var isObserverSet = false
        val observer = Observer<State<EnterCityState>> {
            if (isObserverSet)
                sequenceOfState.add(it)
        }
        try {
            sut.state.observeForever(observer)
            isObserverSet = true

            sut.onClickLookup(cityName)
        } finally {
            sut.state.removeObserver(observer)
        }
        val initialState = sequenceOfState.first().getIfNotConsumed()

        Assert.assertEquals(EnterCityState.Loading, initialState)
    }

    @Test
    fun test_onClickLookup_should_setStateErrorFetchCoordinatesFailed_when_getCityEmitsException() {
        val sequenceOfState = mutableListOf<State<EnterCityState>>()
        var isObserverSet = false
        val observer = Observer<State<EnterCityState>> {
            if (isObserverSet)
                sequenceOfState.add(it)
        }
        try {
            sut.state.observeForever(observer)
            isObserverSet = true

            whenever(geoCodingDataStore.get(any(), any())).thenReturn(flow { error("") })

            sut.onClickLookup(cityName)
        } finally {
            sut.state.removeObserver(observer)
        }
        val finalState = sequenceOfState.last().getIfNotConsumed()

        Assert.assertEquals(EnterCityState.Error.FetchCoordinatesFailed, finalState)
    }

    @Test
    fun test_onClickLookup_should_setStateErrorFetchCoordinatesFailed_when_getCityEmitsEmptyList() {
        val sequenceOfState = mutableListOf<State<EnterCityState>>()
        var isObserverSet = false
        val observer = Observer<State<EnterCityState>> {
            if (isObserverSet)
                sequenceOfState.add(it)
        }
        try {
            sut.state.observeForever(observer)
            isObserverSet = true

            whenever(geoCodingDataStore.get(any(), any())).thenReturn(flow { emit(emptyList()) })

            sut.onClickLookup(cityName)
        } finally {
            sut.state.removeObserver(observer)
        }
        val finalState = sequenceOfState.last().getIfNotConsumed()

        Assert.assertEquals(EnterCityState.Error.FetchCoordinatesFailed, finalState)
    }

    @Test
    fun test_onClickLookup_should_setStateErrorFetchWeatherDetailsFailed_when_getForecastEmitsException() {
        val sequenceOfState = mutableListOf<State<EnterCityState>>()
        var isObserverSet = false
        val observer = Observer<State<EnterCityState>> {
            if (isObserverSet)
                sequenceOfState.add(it)
        }
        try {
            sut.state.observeForever(observer)
            isObserverSet = true

            whenever(geoCodingDataStore.get(any(), any())).thenReturn(flow { emit(listOf(city)) })
            whenever(forecastDataStore.get(any(), any(), any())).thenReturn(flow { error("") })

            sut.onClickLookup(cityName)
        } finally {
            sut.state.removeObserver(observer)
        }
        val finalState = sequenceOfState.last().getIfNotConsumed()

        Assert.assertEquals(EnterCityState.Error.FetchWeatherDetailsFailed, finalState)
    }

    @Test
    fun test_onClickLookup_should_setStateStopProceed_when_getForecastEmitsForecast() {
        val sequenceOfState = mutableListOf<State<EnterCityState>>()
        var isObserverSet = false
        val observer = Observer<State<EnterCityState>> {
            if (isObserverSet)
                sequenceOfState.add(it)
        }
        try {
            sut.state.observeForever(observer)
            isObserverSet = true

            whenever(geoCodingDataStore.get(any(), any())).thenReturn(flow { emit(listOf(city)) })
            whenever(forecastDataStore.get(any(), any(), any())).thenReturn(flow { emit(forecast) })

            sut.onClickLookup(cityName)
        } finally {
            sut.state.removeObserver(observer)
        }
        val finalState = sequenceOfState.last().getIfNotConsumed()

        Assert.assertEquals(EnterCityState.Stop.Proceed(forecast), finalState)
    }
}