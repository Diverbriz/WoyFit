package ru.woyfit.di.provider

import ru.woyfit.presentation.feature.auth.AuthViewModel

interface ViewModelFactoryProvider {
    fun inject(): AuthViewModel.Factory
}