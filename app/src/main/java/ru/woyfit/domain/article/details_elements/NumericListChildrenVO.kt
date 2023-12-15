package ru.neopharm.clubs.domain.article.details_elements

import com.google.gson.annotations.Expose
import ru.neopharm.clubs.domain.ListItemVO
import ru.neopharm.clubs.domain.TextStyleVO


data class NumericListChildrenVO(
    @Expose val type: String = "",
    @Expose var number: Int = 0,
    @Expose override var content: String = "",
): ListItemVO, TextStyleVO {
    override var children: List<TextStyleVO> = emptyList()
}