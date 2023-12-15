package ru.woyfit.presentation.feature.main

import ru.woyfit.presentation.feature.ui.SettingMailing

interface SubscribeClubListener {
    fun onClubSubscribeClick(clubId: Int)
    fun onClubUnsubscribeClick(clubId: Int)
    fun mailingAction(actionMailing: SettingMailing)
}