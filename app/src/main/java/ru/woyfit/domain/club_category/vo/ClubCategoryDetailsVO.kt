package ru.neopharm.clubs.domain.club_category.vo

import ru.neopharm.clubs.domain.article.vo.ArticleVO
import ru.neopharm.clubs.domain.article.vo.NewArticleVO
import ru.woyfit.domain.club.vo.ClubVO
import ru.neopharm.clubs.domain.interview.vo.InterviewVO

data class ClubCategoryDetailsVO(
    val category: ClubCategoryVO = ClubCategoryVO(),
    val club: ClubVO = ClubVO(),
    val mainArticles: List<ArticleVO> = emptyList(),
    val mainInterviews: List<InterviewVO> = emptyList(),
    var newArticles: List<NewArticleVO> = emptyList(),
    val clubs: List<ClubVO> = emptyList()
)