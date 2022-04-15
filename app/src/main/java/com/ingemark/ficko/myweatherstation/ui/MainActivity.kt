package com.ingemark.ficko.myweatherstation.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ingemark.ficko.myweatherstation.databinding.MainActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        private const val INITIAL_LOCATION_NAME = "Zupanja"
    }

    private lateinit var binding: MainActivityBinding
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeViewModel()
        setupScreen()
    }

    fun buttonClicked() {
        val inputLocationName = binding.locationNameInput.text
        if (inputLocationName.isNotEmpty()) {
            startActivity(
                Intent(this, DetailsActivity::class.java)
                    .putExtra(DetailsActivity.LOCATION_NAME, inputLocationName)
            )
        }
    }

    private fun observeViewModel() {
        viewModel.getWeatherInfoSuccess.observe(this) { weatherInfo ->
            binding.locationName.text = weatherInfo.locationName
            binding.temperatureValue.text = weatherInfo.temperature?.toString()
        }
    }

    private fun setupScreen() {
        binding.activity = this
        viewModel.getWeatherInfo(INITIAL_LOCATION_NAME)
    }
}