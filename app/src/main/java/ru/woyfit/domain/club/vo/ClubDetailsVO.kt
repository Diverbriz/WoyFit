package ru.neopharm.clubs.domain.club.vo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.neopharm.clubs.domain.article.vo.ArticleVO
import ru.neopharm.clubs.domain.article.vo.NewArticleVO
import ru.neopharm.clubs.domain.interview.vo.InterviewVO
import ru.woyfit.domain.club.vo.ClubVO

@Parcelize
data class ClubDetailsVO(
    val club: ClubVO = ClubVO(),
    val mainArticles: List<ArticleVO> = emptyList(),
    val interviews: List<InterviewVO> = emptyList(),
    val newArticles: List<NewArticleVO> = emptyList(),
    val clubs: List<ClubVO> = emptyList()
) : Parcelable