package com.example.myapplication.entity

import androidx.room.Embedded

data class WeatherDataResponseModel(
        val weather: List<WeatherData>,
        val base: String,
        val visibility: Long,
        val dt: Long,
        val id: Long,
        val name: String,
        val cod: Int,
        @Embedded
        val coord: Coord

)

data class Coord(
        val lon: Double,
        val lat: Double
)

data class WeatherData(
        val id: Int,
        val main: String,
        val description: String,
        val icon: String
)

data class Main(
        val temp: Double,
        val pressure: Double,
        val humidity: Double,
        val temp_min: Double,
        val temp_max: Double
)

data class Wind(
        val speed: Double,
        val deg: Double
)

data class Clouds(
        val all: Int
)

data class Sys(
        val type: Int,
        val id: Int,
        val message: Double,
        val country: String,
        val sunrise: Long,
        val sunset: Long
)