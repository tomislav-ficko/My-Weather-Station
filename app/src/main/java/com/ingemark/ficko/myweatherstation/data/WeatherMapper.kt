package com.ingemark.ficko.myweatherstation.data

import com.ingemark.ficko.myweatherstation.model.WeatherInfo

class WeatherMapper {

    fun mapWeatherInfoResponseToWeatherInfo(response: WeatherInfoResponse): WeatherInfo {
        return WeatherInfo().apply {
            locationName = response.name
            temperature = response.main?.temp
            pressure = response.main?.pressure
            humidity = response.main?.humidity
            windSpeed = response.wind?.speed
            description = response.weather?.main
            latitude = response.coord?.lat
            longitude = response.coord?.lon
        }
    }
}
