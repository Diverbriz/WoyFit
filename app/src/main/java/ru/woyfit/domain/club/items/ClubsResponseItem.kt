package ru.neopharm.clubs.domain.club.items

import ru.neopharm.clubs.domain.article.items.ArticleItem
import ru.neopharm.clubs.domain.article.items.NewArticleItem
import ru.neopharm.clubs.domain.interview.items.InterviewItem

interface ClubsResponseItem {
    val clubs: List<ClubItem>
    val mainArticles: List<ArticleItem>
    val interviews: List<InterviewItem>
    val newArticles: List<NewArticleItem>
}