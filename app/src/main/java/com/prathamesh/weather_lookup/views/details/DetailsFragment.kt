package com.prathamesh.weather_lookup.views.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.prathamesh.weather_lookup.databinding.FragmentDetailsBinding
import com.prathamesh.weather_lookup.models.ForecastModel
import com.prathamesh.weather_lookup.viewmodels.HomeViewModel

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var navController: NavController
    private val homeViewModel: HomeViewModel by viewModels(ownerProducer = {requireActivity()})

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        binding.render(homeViewModel.getForecast())
    }

    private fun FragmentDetailsBinding.render(forecast: ForecastModel?) {
        if (forecast != null) {
            rvDetails.apply {
                adapter = DetailsAdapter(listOf(forecast))
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }
}