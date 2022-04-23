package com.prathamesh.weather_lookup.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.prathamesh.weather_lookup.R
import com.prathamesh.weather_lookup.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}