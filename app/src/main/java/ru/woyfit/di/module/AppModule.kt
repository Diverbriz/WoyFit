package ru.woyfit.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.woyfit.domain.user.AuthUseCase
import ru.woyfit.presentation.feature.ui.main.MainViewModelFactory

@Module
class AppModule(private val context: Context) {

    @Provides
    fun provideContext():Context = context

    @Provides
    fun provideMainViewModelFactory(authUseCase: AuthUseCase): MainViewModelFactory{
        return MainViewModelFactory(authUseCase = authUseCase)
    }
}