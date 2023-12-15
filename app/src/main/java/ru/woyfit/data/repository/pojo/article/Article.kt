package ru.neopharm.clubs.data.repository.pojo.article

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.neopharm.clubs.data.repository.pojo.club_category.ClubCategory
import ru.neopharm.clubs.domain.article.items.ArticleDetailsItem
import ru.neopharm.clubs.domain.article.items.ArticleItem
import java.util.*

class Article(
    @SerializedName("id") @Expose override val id: Int = 0,
    @SerializedName("club_id") @Expose override val clubId: Int = 0,
    @SerializedName("club_title") @Expose override val clubTitle: String = "",
    @SerializedName("club_color") @Expose override val clubColor: String = "#FFFFFF",
    @SerializedName("title") @Expose override val title: String= "",
    @SerializedName("preview_text") @Expose override val previewText: String= "",
    @SerializedName("text_color") @Expose override val textColor: String= "#FFFFFF",
    @SerializedName("image") @Expose override val image: String= "",
    @SerializedName("background_image") @Expose override val backgroundImage: String= "",
    @SerializedName("color") @Expose override val color: String= "#FFFFFF",
    @SerializedName("highlight") @Expose override val highlight: Int = 0,
    @SerializedName("date_created") @Expose override val dateCreated: Date = Date(0L),
    @SerializedName("category") @Expose override val category: ClubCategory = ClubCategory(),
    @SerializedName("views") @Expose override val views: Int = 0,
    @SerializedName("text_shadow_color") @Expose override val textShadowColor: String = "#"
) : ArticleItem