package ru.neopharm.clubs.domain.article.details_elements

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.neopharm.clubs.domain.ListItemVO

data class NoteVO(
    @Expose val type: String = "",
    @SerializedName("background-color") @Expose val backgroundColor: String = "#",
    @Expose val icon: String = "",
    var children: List<ListItemVO>
) : ListItemVO