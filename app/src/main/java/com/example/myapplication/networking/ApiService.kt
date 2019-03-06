package com.example.myapplication.networking

import com.example.myapplication.entity.UvIndexResponse
import com.example.myapplication.entity.WeatherDataResponseModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/data/2.5/weather?q=Kathmandu&appid=d36f8e0ae9058309e528994dae50ce75")
    fun getWeatherData(): Observable<WeatherDataResponseModel>

    @GET("/data/2.5/uvi/forecast?lat=37.75&lon=-122.37&appid=d36f8e0ae9058309e528994dae50ce75")
    fun getUVIndex(): Observable<UvIndexResponse>
}