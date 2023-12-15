package ru.neopharm.clubs.domain.article.items

import com.google.gson.JsonArray
import ru.neopharm.clubs.domain.club.items.ClubItem
import ru.neopharm.clubs.domain.club_category.items.ClubCategoryItem
import ru.neopharm.clubs.domain.respondent.items.RespondentItem
import java.util.*

interface ArticleDetailsItem {
    val clubs: List<ClubItem>
    val club: ClubItem
    val id: Int
    val title: String
    val previewText: String
    val detailText: JsonArray
    val image: String
    val dateCreated: Date
    val category: ClubCategoryItem
    val views: Int
    val isFavourite: Int
    val showDrugs: Int
    val type: Int
    val respondent: RespondentItem
}