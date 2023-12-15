package ru.woyfit.domain.subscription.vo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.neopharm.clubs.domain.subscription.vo.SubscriptionVO

@Parcelize
data class SubscriptionsVO(
    val subscriptions: List<SubscriptionVO> = emptyList()
) : Parcelable