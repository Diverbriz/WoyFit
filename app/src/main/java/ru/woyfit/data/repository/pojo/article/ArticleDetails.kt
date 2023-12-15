package ru.neopharm.clubs.data.repository.pojo.article

import com.google.gson.JsonArray
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.neopharm.clubs.data.repository.pojo.club.Club
import ru.neopharm.clubs.data.repository.pojo.club_category.ClubCategory
import ru.neopharm.clubs.data.repository.pojo.respondent.Respondent
import ru.neopharm.clubs.domain.article.items.ArticleDetailsItem
import java.util.*
import kotlin.collections.List

class ArticleDetails(
    @SerializedName("clubs") @Expose override val clubs: List<Club> = emptyList(),
    @SerializedName("club") @Expose override val club: Club = Club(),
    @SerializedName("id") @Expose override val id: Int = 0,
    @SerializedName("title") @Expose override val title: String = "",
    @SerializedName("preview_text") @Expose override val previewText: String = "",
    @SerializedName("detail_text") @Expose override val detailText: JsonArray = JsonArray(),
    @SerializedName("image") @Expose override val image: String = "",
    @SerializedName("date_created") @Expose override val dateCreated: Date = Date(0L),
    @SerializedName("category") @Expose override val category: ClubCategory = ClubCategory(),
    @SerializedName("views") @Expose override val views: Int = 0,
    @SerializedName("is_favourite") @Expose override val isFavourite: Int = 0,
    @SerializedName("show_drugs") @Expose override val showDrugs: Int = 0,
    @SerializedName("type") @Expose override val type: Int = 0,
    @SerializedName("respondent") @Expose override val respondent: Respondent = Respondent()
) : ArticleDetailsItem