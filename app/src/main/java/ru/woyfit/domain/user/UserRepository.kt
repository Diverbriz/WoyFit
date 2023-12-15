package ru.woyfit.domain.user

interface UserRepository {
    suspend fun getUser(): User
    suspend fun getToken(): String

    suspend fun registerUser(username: String, password: String)
    suspend fun loginUser(username: String, password: String)
}
