package ru.woyfit.data.repository

import ru.woyfit.data.local.preference.ApplicationPreference
import ru.woyfit.domain.user.User
import ru.woyfit.domain.user.UserRepository

class UserRepositoryImpl constructor(
    private val applicationPreferences: ApplicationPreference
): UserRepository {
    override fun getUser(): User {
        return User(1, "Vladimir")
    }

    override fun getToken(): String {
        return applicationPreferences.getToken()
    }
}