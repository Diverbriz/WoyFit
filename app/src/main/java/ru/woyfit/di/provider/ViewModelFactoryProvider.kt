package ru.woyfit.di.provider

import ru.woyfit.presentation.feature.ui.auth.AuthViewModel

interface ViewModelFactoryProvider {
    fun inject(): AuthViewModel.Factory
}