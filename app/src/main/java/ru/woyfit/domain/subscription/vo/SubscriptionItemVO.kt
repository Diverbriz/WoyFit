package ru.woyfit.domain.subscription.vo

import ru.neopharm.clubs.domain.ListItemVO
import ru.woyfit.domain.club.vo.ClubVO

data class SubscriptionItemVO(
    val club: ClubVO
) : ListItemVO