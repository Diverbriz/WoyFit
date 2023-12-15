package ru.neopharm.clubs.domain.club_category.items

interface ClubCategoryItem {
    val id: Int
    val clubId: Int
    val title: String
    val color: String
    val description: String
}