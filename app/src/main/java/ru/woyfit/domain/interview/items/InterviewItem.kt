package ru.neopharm.clubs.domain.interview.items

import ru.neopharm.clubs.domain.club_category.items.ClubCategoryItem
import ru.neopharm.clubs.domain.respondent.items.RespondentItem
import java.util.*

interface InterviewItem {
    val id: Int
    val clubId: Int
    val clubTitle: String
    val title: String
    val previewText: String
    val respondent: RespondentItem
    val color: String
    val dateCreated: Date
    val category: ClubCategoryItem
    val views: Int
    val textColor: String
}