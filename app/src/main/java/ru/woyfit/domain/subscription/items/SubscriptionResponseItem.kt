package ru.woyfit.domain.subscription.items

import ru.woyfit.data.repository.pojo.subscription.Subscription

interface SubscriptionResponseItem {
    val subscriptions: List<Subscription>
    val paginator : String
}