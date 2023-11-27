package ru.woyfit.di.component

import dagger.Component
import ru.woyfit.di.module.AppModule
import ru.woyfit.di.module.DataModule
import ru.woyfit.di.module.DomainModule
import ru.woyfit.app.MainActivity
import ru.woyfit.presentation.feature.ui.auth.AuthFragment
import ru.woyfit.presentation.feature.ui.auth.AuthViewModel
import ru.woyfit.presentation.feature.ui.main.MainViewModel

@Component(
    modules = [AppModule::class, DomainModule::class, DataModule::class]
)
interface AppComponent {

    class Builder private constructor() {

        companion object {

            fun build(application: MainActivity): AppComponent {

                val appModule = AppModule(context = application)

                return DaggerAppComponent.builder()
                    .appModule(appModule)
                    .build()
            }
        }
    }
    fun inject(mainActivity: MainActivity)
    fun inject(authFragment: AuthFragment)
    fun mainViewModel(): MainViewModel.Factory

    fun authViewModel(): AuthViewModel.Factory
}
