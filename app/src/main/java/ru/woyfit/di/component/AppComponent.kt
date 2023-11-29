package ru.woyfit.di.component

import dagger.Component
import ru.woyfit.app.App
import ru.woyfit.di.module.AppModule
import ru.woyfit.di.module.DataModule
import ru.woyfit.di.module.DomainModule
import ru.woyfit.app.MainActivity
import ru.woyfit.core.di.WoyfitApp
import ru.woyfit.core.di.component.CoreComponent
import ru.woyfit.core.di.provider.CoreProvider
import ru.woyfit.presentation.feature.ui.auth.AuthFragment
import ru.woyfit.presentation.feature.ui.auth.AuthViewModel
import ru.woyfit.presentation.feature.ui.main.MainViewModel

@Component(
    modules = [AppModule::class, DomainModule::class, DataModule::class],
    dependencies = [CoreProvider::class]
)
interface AppComponent: CoreProvider {

    class Builder private constructor() {

        companion object {

            fun build(application: App): AppComponent {

                val appModule = AppModule()
                val coreProvider = CoreComponent.Builder.build(application)

                return DaggerAppComponent.builder()
                    .appModule(appModule)
                    .coreProvider(coreProvider)
                    .build()
            }
        }
    }
    fun inject(app: App)
    fun inject(mainActivity: MainActivity)
    fun inject(authFragment: AuthFragment)
    fun mainViewModel(): MainViewModel.Factory

    fun authViewModel(): AuthViewModel.Factory
}
