package com.example.myapplication.ui.dashboard

import android.content.SharedPreferences
import com.example.myapplication.networking.ApiService
import com.example.myapplication.utils.LATITUDE
import com.example.myapplication.utils.LONGITUDE
import javax.inject.Inject

class DashboardRepository @Inject constructor(private val apiService: ApiService) {
    fun getWeatherData() = apiService.getWeatherData()

   /* fun getUvIndexData() {
        val lat = sharedPreferences.getString(LATITUDE.toString(), null)
        val long = sharedPreferences.getString(LONGITUDE.toString(), null)
        return apiService.getUVIndex()
    }*/
}