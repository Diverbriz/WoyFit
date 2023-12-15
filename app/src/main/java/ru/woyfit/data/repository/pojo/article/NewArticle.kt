package ru.neopharm.clubs.data.repository.pojo.article

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.neopharm.clubs.data.repository.pojo.club_category.ClubCategory
import ru.neopharm.clubs.domain.article.items.NewArticleItem
import java.util.*

class NewArticle(
    @SerializedName("id") @Expose override val id: Int = 0,
    @SerializedName("club_id") @Expose override val clubId: Int = 0,
    @SerializedName("club_title") @Expose override val clubTitle: String = "",
    @SerializedName("title") @Expose override val title: String = "",
    @SerializedName("image") @Expose override val image: String = "",
    @SerializedName("date_created") override val dateCreated: Date = Date(0),
    @SerializedName("category") @Expose override val category: ClubCategory = ClubCategory(),
    @SerializedName("views") @Expose override val views: Int = 0
) : NewArticleItem