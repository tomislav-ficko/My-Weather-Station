package com.ingemark.ficko.myweatherstation.data

import com.ingemark.ficko.myweatherstation.model.WeatherInfo

class WeatherRepository(
    private val api: OpenWeatherMapApi,
    private val mapper: WeatherMapper
) {

    fun getWeatherInfo(locationName: String): WeatherInfo {
        val appId = ""
        val response = api.getWeatherInfo(locationName, appId)
        return mapper.mapWeatherInfoResponseToWeatherInfo(response)
    }
}