package com.ingemark.ficko.myweatherstation.data

import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapApi {

    /*
        This interface tells Retrofit how to structure API calls:
        - what HTTP method to use
        - what endpoint to hit
        - what data to send
        - what data will be received
     */

    @GET("data/2.5/weather")
    suspend fun getWeatherInfo(
        @Query("q") locationName: String,
        @Query("appid") appId: String,
        @Query("units") units: String = "metric"
    ): WeatherInfoResponse
}