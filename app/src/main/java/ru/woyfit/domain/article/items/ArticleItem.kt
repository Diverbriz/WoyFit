package ru.neopharm.clubs.domain.article.items

import ru.neopharm.clubs.domain.club_category.items.ClubCategoryItem
import java.util.*

interface ArticleItem {
    val id: Int
    val clubId: Int
    val clubTitle: String
    val clubColor: String
    val title: String
    val previewText: String
    val textColor: String
    val image: String
    val backgroundImage: String
    val color: String
    val highlight: Int
    val dateCreated: Date
    val category: ClubCategoryItem
    val views: Int
    val textShadowColor: String
}