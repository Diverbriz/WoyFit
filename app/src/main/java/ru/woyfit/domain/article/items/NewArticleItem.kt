package ru.neopharm.clubs.domain.article.items

import ru.neopharm.clubs.domain.club_category.items.ClubCategoryItem
import java.util.*

interface NewArticleItem {
    val id: Int
    val clubId: Int
    val clubTitle: String
    val title: String
    val image: String
    val dateCreated: Date
    val category: ClubCategoryItem
    val views: Int
}