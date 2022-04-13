package com.ingemark.ficko.myweatherstation.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.openweathermap.org/"

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideOpenWeatherMapApi(retrofit: Retrofit): OpenWeatherMapApi =
        retrofit.create(OpenWeatherMapApi::class.java)

    @Singleton
    @Provides
    fun provideWeatherMapper(): WeatherMapper = WeatherMapper()

    @Singleton
    @Provides
    fun provideWeatherRepository(openWeatherMapApi: OpenWeatherMapApi, mapper: WeatherMapper): WeatherRepository =
        WeatherRepository(openWeatherMapApi, mapper)
}