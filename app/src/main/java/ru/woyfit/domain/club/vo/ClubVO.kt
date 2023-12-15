package ru.woyfit.domain.club.vo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.neopharm.clubs.domain.ListItemVO
import ru.neopharm.clubs.domain.article.FavoritesItem
import ru.neopharm.clubs.domain.club_category.vo.ClubCategoryVO

@Parcelize
data class ClubVO(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val image: String = "",
    val icon: String  = "",
    val logoWhite: String = "",
    val logoColor: String = "#FFFFFF",
    val logo3: String = "",
    val color: String = "#FFFFFF",
    val accentColor1: String = "#FFFFFF",
    val accentColor2: String = "#FFFFFF",
    val accentColor3: String = "#FFFFFF",
    val accentColor4: String = "#FFFFFFF",
    val decoration3: String = "#FFFFFF",
    val decoration4: String = "#FFFFFF",
    val categories: List<ClubCategoryVO> = emptyList()
) : Parcelable, ListItemVO,  FavoritesItem
