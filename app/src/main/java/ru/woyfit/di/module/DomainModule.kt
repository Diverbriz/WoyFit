package ru.woyfit.di.module

import dagger.Module
import dagger.Provides
import ru.woyfit.data.usecase.AuthUseCaseImpl
import ru.woyfit.domain.user.AuthUseCase
import ru.woyfit.domain.user.UserRepository

@Module
class DomainModule {

    @Provides
    fun provideAuthUseCase(userRepository: UserRepository):AuthUseCase{
        return AuthUseCaseImpl(userRepository = userRepository)
    }
}