package ru.neopharm.clubs.domain.article.items

import ru.neopharm.clubs.domain.club.items.ClubItem

interface FavoriteArticlesResponseItem {
    val clubs: List<ClubItem>
    val articles: List<ArticleItem>
}