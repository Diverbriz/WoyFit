package ru.neopharm.clubs.domain.subscription

enum class SubscribeType(
    val type: String
) {
    EMAIL("email"),
    PUSH("push"),
    TELEGRAM("telegram"),
    SMS("sms")
}

enum class SubscribeResource(
    val resource: String
) {
    CLUBS("clubs"),
    CLUBS_CAT("clubs_cat")
}