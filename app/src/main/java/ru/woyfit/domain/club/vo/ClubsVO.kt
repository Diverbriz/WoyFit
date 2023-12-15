package ru.neopharm.clubs.domain.club.vo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.neopharm.clubs.domain.ListItemVO
import ru.neopharm.clubs.domain.article.FavoritesItem
import ru.neopharm.clubs.domain.article.vo.ArticleVO
import ru.neopharm.clubs.domain.article.vo.NewArticleVO
import ru.neopharm.clubs.domain.interview.vo.InterviewVO
import ru.woyfit.domain.club.vo.ClubVO

@Parcelize
data class ClubsVO(
    val clubs: List<ClubVO> = emptyList(),
    val mainArticles: List<ArticleVO> = emptyList(),
    val interviews: List<InterviewVO> = emptyList(),
    val newArticles: List<NewArticleVO> = emptyList()
) : Parcelable, ListItemVO, FavoritesItem