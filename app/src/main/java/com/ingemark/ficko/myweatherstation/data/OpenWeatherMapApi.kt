package com.ingemark.ficko.myweatherstation.data

import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapApi {

    @GET("data/2.5/weather")
    fun getWeatherInfo(
        @Query("q") locationName: String,
        @Query("appid") appId: String
    ): WeatherInfoResponse
}