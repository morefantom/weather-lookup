package com.prathamesh.weather_lookup.views.advance_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.prathamesh.weather_lookup.databinding.FragmentAdvanceDetailsBinding
import com.prathamesh.weather_lookup.models.ForecastModel
import com.prathamesh.weather_lookup.viewmodels.HomeViewModel

class AdvanceDetailsFragment : Fragment() {

    private lateinit var binding: FragmentAdvanceDetailsBinding
    private lateinit var navController: NavController
    private val homeViewModel: HomeViewModel by viewModels(ownerProducer = { requireActivity() })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAdvanceDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        binding.render(homeViewModel.getForecast())
    }

    private fun FragmentAdvanceDetailsBinding.render(forecast: ForecastModel?) {
        if (forecast != null) {
            tvTemp.text = forecast.main?.temp?.toString() ?: ""
            tvFeelsLike.text = forecast.main?.feelsLike?.toString() ?: ""
            tvWeather.text = forecast.weatherList?.firstOrNull()?.main ?: ""
            tvWeatherDescription.text = forecast.weatherList?.firstOrNull()?.description ?: ""
        }
    }
}