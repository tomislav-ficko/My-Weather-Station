package com.ingemark.ficko.myweatherstation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ingemark.ficko.myweatherstation.databinding.DetailsActivityBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: DetailsActivityBinding
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeViewModel()
        setupScreen()
    }


    private fun observeViewModel() {
        viewModel.getWeatherInfoSuccess.observe(this) { weatherInfo ->
            binding.locationName.text = weatherInfo.locationName
            binding.weatherDescriptionValue.text = weatherInfo.description?.toString()
            binding.temperatureValue.text = weatherInfo.temperature?.toString()
            binding.humidityValue.text = weatherInfo.humidity?.toString()
            binding.pressureValue.text = weatherInfo.pressure?.toString()
            binding.windSpeedValue.text = weatherInfo.windSpeed?.toString()
            val latitude = weatherInfo.latitude?.toString() ?: "NA"
            val longitude = weatherInfo.longitude?.toString() ?: "NA"
            val geographicalLocation = "$latitude, $longitude"
            binding.latLonValue.text = geographicalLocation
        }
    }

    private fun setupScreen() {
        binding.activity = this
    }
}