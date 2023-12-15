package ru.neopharm.clubs.domain.article.details_elements

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.neopharm.clubs.domain.TextStyleVO

data class TextFontStyleVO(
    @SerializedName("type") @Expose val type: String = "",
    @SerializedName("content") @Expose override var content: String = "",
) : TextStyleVO {
    override var children: List<TextStyleVO> = emptyList()
}