package ru.woyfit.data.repository.pojo.subscription

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.woyfit.domain.subscription.items.SubscriptionResponseItem

class SubscriptionResponse(
    @SerializedName("subscriptions") @Expose override val subscriptions: List<Subscription> = emptyList(),
    @SerializedName("paginator") @Expose override val paginator: String
) : SubscriptionResponseItem