package ru.neopharm.clubs.domain.article.details_elements

import com.google.gson.annotations.SerializedName
import ru.neopharm.clubs.domain.ListItemVO

data class ProductSliderVO(
    val type: String = "",
    @SerializedName("slider-type") val sliderType: Int = 0,
    val products: List<Int> = listOf(),
    @SerializedName("background-color") val backgroundColor: String = "#",
    var num: Int = 0
): ListItemVO
