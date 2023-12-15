package ru.neopharm.clubs.domain.article.details_elements

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.neopharm.clubs.domain.TextStyleVO

data class TextLinkVO(
    @SerializedName("type") @Expose val type: String = "",
    @SerializedName("content") @Expose override var content: String = "",
    @SerializedName("href") @Expose var link: String = "",
    @SerializedName("app-href") @Expose var applink: String = "",
): TextStyleVO {
    override var children: List<TextStyleVO> = emptyList()
}