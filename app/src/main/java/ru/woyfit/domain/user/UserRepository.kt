package ru.woyfit.domain.user

interface UserRepository {
    fun getUser(): User
    fun getToken(): String
}
