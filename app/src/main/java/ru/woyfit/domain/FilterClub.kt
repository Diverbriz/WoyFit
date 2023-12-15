package ru.neopharm.clubs.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class FilterClub : Parcelable {
    abstract val id: Int
    @Parcelize
    class AllClubs(override val id: Int = 1) : FilterClub()
    @Parcelize
    class FavoriteClubs(override val id: Int = 2) : FilterClub()
    @Parcelize
    class FavoriteScreen(override val id: Int = 3) : FilterClub()
}