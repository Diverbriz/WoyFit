package ru.neopharm.clubs.domain.article.vo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import ru.neopharm.clubs.domain.ListItemVO
import ru.woyfit.domain.club.vo.ClubVO
import ru.neopharm.clubs.domain.club_category.vo.ClubCategoryVO
import ru.neopharm.clubs.domain.respondent.vo.RespondentVO
import java.util.*

@Parcelize
data class ArticleDetailsVO(
    val clubs: List<ClubVO> = emptyList(),
    val club: ClubVO = ClubVO(),
    val id: Int = 0,
    val title: String = "",
    val previewText: String = "",
    val articleChanks: List<ArticleChanks> = emptyList(),
    val image: String = "",
    val dateCreated: Date = Date(),
    val category: ClubCategoryVO = ClubCategoryVO(),
    val views: Int = 0,
    var isFavourite: Boolean = false,
    val showDrugs: Boolean = false,
    val type: Int = 0,
    val respondent: RespondentVO = RespondentVO()
) : Parcelable {
    var detailsList: List<ListItemVO> = emptyList()

    fun toggleFavorite() {
        isFavourite = !isFavourite
    }
}