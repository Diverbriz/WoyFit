package ru.woyfit.data.local.preference

import ru.woyfit.domain.local.preference.Preference


class ApplicationPreference constructor(
    private val mDefaultPreference: Preference
){
    fun getToken(): String{
        return mDefaultPreference.restoreString("auth_token")
    }
}