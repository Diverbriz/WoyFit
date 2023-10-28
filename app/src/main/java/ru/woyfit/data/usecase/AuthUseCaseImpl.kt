package ru.woyfit.data.usecase

import ru.woyfit.domain.user.AuthUseCase
import ru.woyfit.domain.user.User
import ru.woyfit.domain.user.UserRepository

class AuthUseCaseImpl(
    private val userRepository: UserRepository
):AuthUseCase {
    override fun getUser(): User = userRepository.getUser()


    override fun getToken(): String = userRepository.getToken()

}