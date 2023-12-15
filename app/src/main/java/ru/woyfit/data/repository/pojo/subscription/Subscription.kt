package ru.woyfit.data.repository.pojo.subscription

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.woyfit.domain.subscription.items.SubscriptionItem

class Subscription(
    @SerializedName("id") @Expose override val id: Int = 0,
    @SerializedName("type") @Expose override val type: String = "",
    @SerializedName("resource") @Expose override val resource: String = "",
    @SerializedName("resource_id") @Expose override val resourceId: Long =0,
) : SubscriptionItem