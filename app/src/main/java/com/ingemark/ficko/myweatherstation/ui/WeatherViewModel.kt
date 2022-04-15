package com.ingemark.ficko.myweatherstation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ingemark.ficko.myweatherstation.data.WeatherRepository
import com.ingemark.ficko.myweatherstation.domain.WeatherInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {

    val getWeatherInfoSuccess = MutableLiveData<WeatherInfo>()

    fun getWeatherInfo(locationName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val weatherInfo = repository.getWeatherInfo(locationName)
                getWeatherInfoSuccess.postValue(weatherInfo)
            } catch (e: HttpException) {
                Timber.e(e.localizedMessage)
            }
        }
    }
}