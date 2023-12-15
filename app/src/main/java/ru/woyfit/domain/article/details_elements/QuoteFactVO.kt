package ru.neopharm.clubs.domain.article.details_elements

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.neopharm.clubs.domain.ListItemVO

data class QuoteFactVO(
    @Expose val type: String = "",
    @Expose var title: String = "",
    @SerializedName("title-color") @Expose val titleColor: String? = "#",
    @SerializedName("background-color") @Expose val backgroundColor: String? = "#",
): ListItemVO {
    var children: List<ListItemVO> = emptyList()
}