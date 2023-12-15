package ru.neopharm.clubs.domain.club.vo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.neopharm.clubs.domain.article.FavoritesItem
import ru.woyfit.domain.club.vo.ClubVO

@Parcelize
data class ClubFavoritesVO(
    val clubFilteredId: Int,
    val clubs: List<ClubVO>
) : FavoritesItem, Parcelable