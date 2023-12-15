package ru.neopharm.clubs.domain.article.vo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.woyfit.domain.club.vo.ClubVO

@Parcelize
data class FavoriteArticlesVO(
    val clubs: List<ClubVO>,
    val articles: List<ArticleVO>
) : Parcelable