package ru.neopharm.clubs.domain.article.details_elements

import com.google.gson.annotations.Expose
import ru.neopharm.clubs.domain.ListItemVO
import ru.neopharm.clubs.domain.TextStyleVO

data class TextVO(
    @Expose val type: String = "",
    @Expose override var content: String = "",
    val resourceId: Int = 0,
): ListItemVO, TextStyleVO {
    override var children: List<TextStyleVO> = emptyList()
}