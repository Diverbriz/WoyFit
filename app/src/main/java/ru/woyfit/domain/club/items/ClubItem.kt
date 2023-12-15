package ru.neopharm.clubs.domain.club.items

import ru.neopharm.clubs.domain.club_category.items.ClubCategoryItem

interface ClubItem {
    val id: Int
    val title: String
    val description: String
    val image: String
    val icon: String
    val logoWhite: String
    val logoColor: String
    val logo3: String
    val color: String
    val accentColor1: String
    val accentColor2: String
    val accentColor3: String
    val accentColor4: String
    val decoration3: String
    val decoration4: String
    val categories: List<ClubCategoryItem>
}