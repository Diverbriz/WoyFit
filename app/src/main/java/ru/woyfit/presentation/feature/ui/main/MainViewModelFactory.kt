package ru.woyfit.presentation.feature.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.woyfit.domain.user.AuthUseCase

class MainViewModelFactory(
    val authUseCase: AuthUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            authUseCase = authUseCase
        ) as T
    }
}