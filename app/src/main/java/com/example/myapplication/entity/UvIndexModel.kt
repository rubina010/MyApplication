package com.example.myapplication.entity

data class UvIndexResponse(
        val uvData: List<UvData>
)

data class UvData(
        val lat: Double,
        val lon: Double,
        val date_iso: String,
        val date: Long,
        val value: Double
)