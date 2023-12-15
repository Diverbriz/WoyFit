package ru.neopharm.clubs.domain.article.vo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.neopharm.clubs.domain.ListItemVO
import ru.neopharm.clubs.domain.club_category.vo.ClubCategoryVO
import java.util.*

@Parcelize
data class NewArticleVO(
    val id: Int = 0,
    val clubId: Int = 0,
    val clubTitle: String = "",
    val title: String  = "",
    val image: String = "",
    val dateCreated: Date = Date(),
    val category: ClubCategoryVO = ClubCategoryVO(),
    val views: Int = 0
) : Parcelable, ListItemVO