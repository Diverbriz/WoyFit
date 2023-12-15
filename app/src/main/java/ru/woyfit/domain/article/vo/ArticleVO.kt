package ru.neopharm.clubs.domain.article.vo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.neopharm.clubs.domain.ListItemVO
import ru.neopharm.clubs.domain.article.FavoritesItem
import ru.neopharm.clubs.domain.club_category.vo.ClubCategoryVO
import java.util.*

@Parcelize
data class ArticleVO(
    val id: Int = 0,
    val clubId: Int = 0,
    val clubTitle: String = "",
    val clubColor: String = "#FFFFFF",
    val title: String = "",
    val previewText: String = "",
    val textColor: String = "#",
    val image: String = "",
    val backgroundImage: String = "",
    val color: String = "#FFFFFF",
    val isHighlight: Boolean = true,
    val dateCreated: Date = Date(),
    val category: ClubCategoryVO = ClubCategoryVO(),
    val views: Int = 0,
    val textShadowColor: String
) : Parcelable, ListItemVO, FavoritesItem