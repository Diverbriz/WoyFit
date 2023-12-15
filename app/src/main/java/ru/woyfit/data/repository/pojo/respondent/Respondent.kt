package ru.neopharm.clubs.data.repository.pojo.respondent

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.neopharm.clubs.domain.respondent.items.RespondentItem

class Respondent(
    @SerializedName("image") @Expose override val image: String = "",
    @SerializedName("name") @Expose override val name: String = "",
    @SerializedName("profession") @Expose override val profession: String = ""
) : RespondentItem