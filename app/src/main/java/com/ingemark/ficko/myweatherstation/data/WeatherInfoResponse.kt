package com.ingemark.ficko.myweatherstation.data

class WeatherInfoResponse {
    var name: String? = null
    var main: AttributesData? = null
    var weather: List<DescriptionData>? = null
    var wind: WindData? = null
    var coord: CoordinateData? = null
}

class CoordinateData {
    var lon: Float? = null
    var lat: Float? = null
}

class DescriptionData {
    var main: String? = null
}

class AttributesData {
    var temp: Float? = null
    var pressure: Int? = null
    var humidity: Int? = null
}

class WindData {
    var speed: Float? = null
}