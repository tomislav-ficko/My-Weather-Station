package com.ingemark.ficko.myweatherstation.data

import com.ingemark.ficko.myweatherstation.domain.WeatherInfo

class WeatherMapper {

    /*
        Mappers on the data layer are used to convert data from a backend model (which is received from the API)
        to a domain model, which will be used throughout the app
    */

    fun mapWeatherInfoResponseToWeatherInfo(response: WeatherInfoResponse?): WeatherInfo {
        return WeatherInfo().apply {
            locationName = response?.name
            temperature = response?.main?.temp
            pressure = response?.main?.pressure
            humidity = response?.main?.humidity
            windSpeed = response?.wind?.speed
            description = response?.weather?.first()?.main
            latitude = response?.coord?.lat
            longitude = response?.coord?.lon
        }
    }
}
