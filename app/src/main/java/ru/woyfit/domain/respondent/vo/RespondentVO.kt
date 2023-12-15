package ru.neopharm.clubs.domain.respondent.vo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RespondentVO(
    val image: String = "",
    val name: String = "",
    val profession: String = ""
) : Parcelable