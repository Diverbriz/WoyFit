package ru.neopharm.clubs.domain.article.details_elements

import com.google.gson.annotations.Expose
import ru.neopharm.clubs.domain.TextStyleVO

data class TextColorVO(
    @Expose val type: String = "",
    @Expose var color: String = "#FFFFFF",
    @Expose override var content: String = ""
) : TextStyleVO {
    override var children: List<TextStyleVO> = emptyList()
}