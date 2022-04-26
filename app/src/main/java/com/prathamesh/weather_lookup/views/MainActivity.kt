package com.prathamesh.weather_lookup.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.prathamesh.weather_lookup.R
import com.prathamesh.weather_lookup.databinding.ActivityMainBinding
import com.prathamesh.weather_lookup.viewmodels.HomeViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}