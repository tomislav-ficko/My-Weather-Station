package com.ingemark.ficko.myweatherstation.data

import com.ingemark.ficko.myweatherstation.domain.WeatherInfo

class WeatherRepository(
    private val api: OpenWeatherMapApi,
    private val mapper: WeatherMapper
) {

    /*
        Repositories are used to gather data from various sources (e.g. database, local cache, API)
        and provide it to the lower levels in the app (mainly the ViewModel)
    */

    suspend fun getWeatherInfo(locationName: String): WeatherInfo {
        val response = api.getWeatherInfo(locationName)
        return mapper.mapWeatherInfoResponseToWeatherInfo(response)
    }
}