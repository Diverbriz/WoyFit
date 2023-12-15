package ru.woyfit.data.usecase

import ru.woyfit.domain.user.AuthUseCase
import ru.woyfit.domain.user.User
import ru.woyfit.domain.user.UserRepository

class AuthUseCaseImpl(
    private val userRepository: UserRepository
):AuthUseCase {
    override suspend fun getUser(): User = userRepository.getUser()
    override suspend fun registerUser(username: String, password: String) {
        userRepository.registerUser(username, password)
    }

    override suspend fun loginUser(username: String, password: String) {
        userRepository.loginUser(username, password)
    }

    override suspend fun getToken(): String = userRepository.getToken()

}