package com.ingemark.ficko.myweatherstation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ingemark.ficko.myweatherstation.R
import com.ingemark.ficko.myweatherstation.databinding.DetailsActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    companion object {
        const val LOCATION_NAME = "location_name"
    }

    private lateinit var binding: DetailsActivityBinding
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeViewModel()
        getWeatherData()
    }

    private fun observeViewModel() {
        viewModel.getWeatherInfoSuccess.observe(this) { weatherInfo ->
            binding.apply {
                locationName.text = weatherInfo.locationName
                weatherDescriptionValue.text = weatherInfo.description
                temperatureValue.text = getString(R.string.template_temperature, weatherInfo.temperature)
                humidityValue.text = getString(R.string.template_humidity, weatherInfo.humidity)
                pressureValue.text = getString(R.string.template_pressure, weatherInfo.pressure)
                windSpeedValue.text = getString(R.string.template_wind_speed, weatherInfo.windSpeed)
                latLonValue.text = getString(R.string.template_geo_location, weatherInfo.latitude, weatherInfo.longitude)
            }
        }
    }

    private fun getWeatherData() {
        intent.extras?.get(LOCATION_NAME)?.let { requestedLocationName ->
            viewModel.getWeatherInfo(requestedLocationName.toString())
        }
    }
}