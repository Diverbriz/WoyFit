package ru.neopharm.clubs.domain.subscription.vo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SubscriptionVO(
    val id: Int = 0,
    val type: String = "",
    val resource: String = "",
    val resourceId: Long = 0
) : Parcelable