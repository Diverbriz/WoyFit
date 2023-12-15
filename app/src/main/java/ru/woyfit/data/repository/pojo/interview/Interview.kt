package ru.neopharm.clubs.data.repository.pojo.interview

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.neopharm.clubs.data.repository.pojo.club_category.ClubCategory
import ru.neopharm.clubs.data.repository.pojo.respondent.Respondent
import ru.neopharm.clubs.domain.interview.items.InterviewItem
import java.util.*

class Interview(
    @SerializedName("id") @Expose override val id: Int = 0,
    @SerializedName("club_id") @Expose override val clubId: Int = 0,
    @SerializedName("club_title") @Expose override val clubTitle: String = "",
    @SerializedName("title") @Expose override val title: String = "",
    @SerializedName("preview_text") @Expose override val previewText: String = "",
    @SerializedName("respondent") @Expose override val respondent: Respondent = Respondent(),
    @SerializedName("color") @Expose override val color: String = "#FFFFFF",
    @SerializedName("date_created") override val dateCreated: Date = Date(0L),
    @SerializedName("category") @Expose override val category: ClubCategory,
    @SerializedName("views") @Expose override val views: Int = 0,
    @SerializedName("text_color") @Expose override val textColor: String = "#FFFFFF"
) : InterviewItem