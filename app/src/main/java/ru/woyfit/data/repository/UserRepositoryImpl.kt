package ru.woyfit.data.repository

import ru.woyfit.data.local.preference.ApplicationPreference
import ru.woyfit.data.network.WoyfitRestApi
import ru.woyfit.data.network.request.LoginRequest
import ru.woyfit.data.network.request.SignupRequest
import ru.woyfit.domain.user.User
import ru.woyfit.domain.user.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val applicationPreferences: ApplicationPreference,
    private val woyfitRestApi: WoyfitRestApi
): UserRepository {
    override suspend fun getUser(): User {
        return User(1, "Vladimir")
    }

    override suspend fun getToken(): String {
        return applicationPreferences.getToken()
    }

    override suspend fun registerUser(username: String, password: String) {
        woyfitRestApi.signupUser(SignupRequest(username, password))
    }

    override suspend fun loginUser(username: String, password: String) {
        woyfitRestApi.loginUser(LoginRequest(username, password))
    }
}