package ru.woyfit.domain.user

interface AuthUseCase {
    fun getUser(): User

    fun getToken():String
}