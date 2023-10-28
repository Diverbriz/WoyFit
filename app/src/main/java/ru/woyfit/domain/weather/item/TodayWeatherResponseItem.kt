package ru.woyfit.domain.weather.item

import java.util.Date

interface TodayWeatherResponseItem {
    val id: Int
    val date: Date
    val image: Int
    val min: Int
    val max: Int
    val aqi: Int
}