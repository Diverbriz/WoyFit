package ru.neopharm.clubs.domain.club.items

import ru.neopharm.clubs.domain.article.items.ArticleItem

interface ClubsMainResponseItem {
    val clubs: List<ClubItem>
    val mainArticles: List<ArticleItem>
}