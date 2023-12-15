package ru.woyfit.domain.subscription.items

interface SubscriptionItem {
    val id: Int
    val type: String
    val resource: String
    val resourceId: Long
}