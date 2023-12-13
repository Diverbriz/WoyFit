package ru.woyfit.di.component

import dagger.Component
import ru.woyfit.app.App
import ru.woyfit.app.MainActivity
import ru.woyfit.core.di.component.CoreComponent
import ru.woyfit.core.di.provider.CoreProvider
import ru.woyfit.di.module.AppModule
import ru.woyfit.di.module.DataModule
import ru.woyfit.di.module.DomainModule
import ru.woyfit.di.provider.ViewModelFactoryProvider
import ru.woyfit.presentation.feature.ui.auth.AuthFragment

@Component(
    modules = [AppModule::class, DomainModule::class, DataModule::class],
    dependencies = [CoreProvider::class]
)
interface AppComponent: CoreProvider, ViewModelFactoryProvider {

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
}
