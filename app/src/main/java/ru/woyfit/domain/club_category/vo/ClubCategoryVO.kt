package ru.neopharm.clubs.domain.club_category.vo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.neopharm.clubs.domain.ListItemVO

@Parcelize
data class ClubCategoryVO(
    val id: Int = 0,
    val clubId: Int = 0,
    val title: String = "",
    val color: String = "#FFFFFF",
    val description: String = ""
) : Parcelable, ListItemVO