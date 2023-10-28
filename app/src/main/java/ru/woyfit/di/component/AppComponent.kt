package ru.woyfit.di.component

import dagger.Component
import ru.woyfit.di.module.AppModule
import ru.woyfit.di.module.DataModule
import ru.woyfit.di.module.DomainModule
import ru.woyfit.presentation.feature.ui.MainActivity

@Component(
    modules = [AppModule::class, DomainModule::class, DataModule::class]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}