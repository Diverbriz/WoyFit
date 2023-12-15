package ru.neopharm.clubs.domain.interview.vo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.neopharm.clubs.domain.ListItemVO
import ru.neopharm.clubs.domain.club_category.vo.ClubCategoryVO
import ru.neopharm.clubs.domain.respondent.vo.RespondentVO
import java.util.*

@Parcelize
data class InterviewVO (
    val id: Int = 0,
    val clubId: Int = 0,
    val clubTitle: String = "",
    val title: String = "",
    val previewText: String = "",
    val respondent: RespondentVO = RespondentVO(),
    val color: String = "#",
    val dateCreated: Date = Date(),
    val category: ClubCategoryVO = ClubCategoryVO(),
    val views: Int = 0,
    val textColor: String = "#"
): Parcelable, ListItemVO




