package ru.woyfit.domain.weather.vo

import android.os.Parcelable
import java.util.Date

data class TodayWeatherVO(
    val id: Int = 0,
    val date: String = "2018-12-31",
    val image: Int,
    val min: Int = 0,
    val max: Int = 20,
    val aqi: Int = 60,
)