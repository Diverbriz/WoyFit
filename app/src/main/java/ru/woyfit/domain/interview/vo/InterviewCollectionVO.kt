package ru.neopharm.clubs.domain.interview.vo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.neopharm.clubs.domain.ListItemVO

@Parcelize
data class InterviewCollectionVO(
    val interviews: List<InterviewVO>
) : Parcelable, ListItemVO