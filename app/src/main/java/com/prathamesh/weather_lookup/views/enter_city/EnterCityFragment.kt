package com.prathamesh.weather_lookup.views.enter_city

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.prathamesh.weather_lookup.R
import com.prathamesh.weather_lookup.databinding.FragmentEnterCityBinding
import com.prathamesh.weather_lookup.datastores.remotestores.ForecastRemoteStore
import com.prathamesh.weather_lookup.datastores.remotestores.GeoCodingRemoteStore
import com.prathamesh.weather_lookup.models.ForecastModel
import com.prathamesh.weather_lookup.viewmodels.enter_city.EnterCityState
import com.prathamesh.weather_lookup.viewmodels.enter_city.EnterCityViewModel
import com.prathamesh.weather_lookup.viewmodels.enter_city.EnterCityViewModelFactory

class EnterCityFragment : Fragment() {

    private lateinit var binding: FragmentEnterCityBinding
    private lateinit var navController: NavController
    private val viewModel: EnterCityViewModel by viewModels {
        EnterCityViewModelFactory(GeoCodingRemoteStore(), ForecastRemoteStore())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEnterCityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        viewModel.state.observe(viewLifecycleOwner) { state ->
            binding.render(state.getIfNotConsumed())
        }
    }

    private fun FragmentEnterCityBinding.render(state: EnterCityState?) {
        refresh()

        if (state == null) {
            renderStart()
        } else when (state) {
            EnterCityState.Start -> renderStart()
            EnterCityState.Loading -> renderLoading()
            is EnterCityState.Error -> renderError(state)
            is EnterCityState.Stop -> renderStop(state)
        }
    }

    private fun FragmentEnterCityBinding.renderStart() {
        etCity.isVisible = true
        btnLookup.isVisible = true

        btnLookup.setOnClickListener {
            val city = etCity.text.toString()
            viewModel.onClickLookup(city)
        }
    }

    private fun FragmentEnterCityBinding.renderLoading() {
        progressBar.isVisible = true
    }

    private fun FragmentEnterCityBinding.renderError(errorState: EnterCityState.Error) {
        tvError.isVisible = true

        val errorMessage = when (errorState) {
            EnterCityState.Error.FetchCoordinatesFailed -> getErrorStringForFetchCoordinatesFailed()
            EnterCityState.Error.FetchWeatherDetailsFailed -> getErrorStringForFetchWeatherDetailsFailed()
        }

        tvError.text = errorMessage
    }

    private fun renderStop(stopState: EnterCityState.Stop) {
        when (stopState) {
            is EnterCityState.Stop.Proceed -> proceed(stopState.forecast)
        }
    }

    private fun proceed(forecast: ForecastModel) {
        navController.navigate(EnterCityFragmentDirections.actionEnterCityFragmentToDetailsFragment())
    }

    private fun FragmentEnterCityBinding.refresh() {
        etCity.isVisible = false
        btnLookup.isVisible = false
        tvError.isVisible = false
        progressBar.isVisible = false
    }

    private fun getErrorStringForFetchCoordinatesFailed(): String {
        return resources.getString(R.string.enter_city_label_error_coordinates)
    }

    private fun getErrorStringForFetchWeatherDetailsFailed(): String {
        return resources.getString(R.string.enter_city_label_error_weather)
    }
}