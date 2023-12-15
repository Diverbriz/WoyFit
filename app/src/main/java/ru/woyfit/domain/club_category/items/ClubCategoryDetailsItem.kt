package ru.woyfit.domain.club_category.items

import ru.neopharm.clubs.data.repository.pojo.club.Club
import ru.neopharm.clubs.domain.article.items.ArticleItem
import ru.neopharm.clubs.domain.article.items.NewArticleItem
import ru.neopharm.clubs.domain.club.items.ClubItem
import ru.neopharm.clubs.domain.club_category.items.ClubCategoryItem
import ru.neopharm.clubs.domain.interview.items.InterviewItem

interface ClubCategoryDetailsItem {
    val category: ClubCategoryItem
    val club: Club
    val mainArticles: List<ArticleItem>
    val mainInterviews: List<InterviewItem>
    val newArticles: List<NewArticleItem>
    val clubs: List<ClubItem>
}