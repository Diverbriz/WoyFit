package ru.neopharm.clubs.data.repository.pojo.club

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.neopharm.clubs.data.repository.pojo.club_category.ClubCategory
import ru.neopharm.clubs.domain.club.items.ClubItem

class Club(
    @SerializedName("id") @Expose override val id: Int = 0,
    @SerializedName("title") @Expose override val title: String = "",
    @SerializedName("description") @Expose override val description: String = "",
    @SerializedName("image") @Expose override val image: String = "",
    @SerializedName("icon") @Expose override val icon: String = "",
    @SerializedName("logo_1") @Expose override val logoWhite: String = "",
    @SerializedName("logo_2") @Expose override val logoColor: String = "#FFFFFF",
    @SerializedName("logo_3") @Expose override val logo3: String = "",
    @SerializedName("color") @Expose override val color: String = "#FFFFFF",
    @SerializedName("categories") @Expose override val categories: List<ClubCategory> = emptyList(),
    @SerializedName("accent_color_1") @Expose override val accentColor1: String = "#FFFFFF",
    @SerializedName("accent_color_2") @Expose override val accentColor2: String = "#FFFFFF",
    @SerializedName("accent_color_3") @Expose override val accentColor3: String = "#FFFFFF",
    @SerializedName("accent_color_4") @Expose override val accentColor4: String = "#FFFFFF",
    @SerializedName("decoration_3") @Expose override val decoration3: String = "#FFFFFF",
    @SerializedName("decoration_4") @Expose override val decoration4: String = "#FFFFFF"
) : ClubItem