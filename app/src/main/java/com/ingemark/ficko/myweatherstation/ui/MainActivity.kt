package com.ingemark.ficko.myweatherstation.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ingemark.ficko.myweatherstation.R
import com.ingemark.ficko.myweatherstation.databinding.MainActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    /*
       Activities (and Fragments) are used to set data to the Views on screen and define what happens when an action
       happens (e.g. something is clicked). Since the ViewModel holds the application logic, we mostly want to notify
       the ViewModel that a button was pressed, and let it do it's magic and just tell the Activity what to change on screen.
       In order for the Activity (or Fragment) to receive information from the ViewModel, it has to observe its LiveData object.
    */

    companion object {
        private const val INITIAL_LOCATION_NAME = "Zupanja"
    }

    private lateinit var binding: MainActivityBinding // This object is the connection between the Activity class and the XML layout
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
            binding.temperatureValue.text = getString(R.string.template_temperature, weatherInfo.temperature)
        }
    }

    private fun setupScreen() {
        binding.activity = this
        viewModel.getWeatherInfo(INITIAL_LOCATION_NAME)
    }
}