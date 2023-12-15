package ru.woyfit.data.local.preference

import ru.woyfit.core.di.CoreAuthPrefs
import ru.woyfit.domain.local.preference.Preference
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ApplicationPreference @Inject constructor(
    private val mDefaultPreference: Preference
): CoreAuthPrefs{
    companion object {
        private const val PREF_SESSION_TOKEN_KEY = "ApplicationPreference.SessionToken"
    }
    override fun storeToken(token: String) {
        mDefaultPreference.store(PREF_SESSION_TOKEN_KEY, token)
    }

    override fun getToken(): String {
        return mDefaultPreference.restoreString(PREF_SESSION_TOKEN_KEY)
    }
}