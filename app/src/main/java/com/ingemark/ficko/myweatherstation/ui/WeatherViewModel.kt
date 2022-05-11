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

    /*
        ViewModels are used to define the application logic.
        Whenever an action is done on the UI (e.g. button clicked), we want to notify the ViewModel (by calling its function)
        so that it executes the logic we want to achieve. When the logic is completed (e.g. data retrieved from a Repository),
        the UI (i.e. Activity or Fragment) is notified via a LiveData object
     */

    val getWeatherInfoSuccess = MutableLiveData<WeatherInfo>()

    fun getWeatherInfo(locationName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            /*
                - "CoroutineScope(Dispatchers.IO)" says that we want a background coroutine (thread)
                    -> By default, everything runs on the Dispatchers.Main coroutine (i.e. UI thread)
                    -> IO stands for Input-Output, Dispatchers.IO is used for all long-running tasks, like DB queries and API requests
                - ".launch {...}" says that we want to run the code inside the curly brackets on the previously defined coroutine
            */
            try {
                val weatherInfo = repository.getWeatherInfo(locationName)
                getWeatherInfoSuccess.postValue(weatherInfo)
            } catch (e: HttpException) {
                Timber.e(e.localizedMessage) // The try-catch block is used to catch any HTTP errors that may happen (e.g. wrong api key)
            }
        }
    }
}