package ru.neopharm.clubs.domain

interface TextStyleVO: ListItemVO {
    val content: String
    var children: List<TextStyleVO>
}