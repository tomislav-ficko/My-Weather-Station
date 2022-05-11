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

    /*
       This object tells Hilt (the dependency injection library) how to create certain objects. When Hilt creates an object,
       it exist in the background of the application and is provided to any class that needs it.

       E.g. The repository needs the API and mapper in order to function properly. When the WeatherRepository object is being created,
       Hilt looks around to find if the Api and Mapper objects exist (since they are the dependencies of the repository object).
       - If they exist, it retrieves and adds them to the Repository constructor.
       - If they don't already exist, Hilt executes one of the lower function which creates an object of the needed type
         (i.e. "provideOpenWeatherMapApi(..)" or "provideWeatherMapper()") and then adds it to the Repository constructor.

    */

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