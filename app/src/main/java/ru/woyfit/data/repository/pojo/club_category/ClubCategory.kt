package ru.neopharm.clubs.data.repository.pojo.club_category

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.neopharm.clubs.domain.club_category.items.ClubCategoryItem

class ClubCategory(
    @SerializedName("id") @Expose override val id: Int = 0,
    @SerializedName("club_id") @Expose override val clubId: Int = 0,
    @SerializedName("title") @Expose override val title: String = "",
    @SerializedName("color") @Expose override val color: String = "#FFFFFF",
    @SerializedName("description") @Expose override val description: String = ""
) : ClubCategoryItem