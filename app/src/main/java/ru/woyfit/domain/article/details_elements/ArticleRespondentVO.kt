package ru.neopharm.clubs.domain.article.details_elements

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.neopharm.clubs.domain.ListItemVO

data class ArticleRespondentVO(
    val type: String = "",
    val image: String = "",
    var name: String = "",
    var profession: String = "",
    var content: String = "",
    @SerializedName("border-color") @Expose val borderColor: String = "#"
): ListItemVO
