package ru.woyfit.core.di.component

import dagger.BindsInstance
import dagger.Component
import ru.woyfit.core.di.WoyfitApp
import ru.woyfit.core.di.module.CoreModule
import ru.woyfit.core.di.module.NetworkModule
import ru.woyfit.core.di.provider.CoreProvider
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CoreModule::class,
        NetworkModule::class
    ]
)
interface CoreComponent: CoreProvider {
    @Component.Builder
    interface ComponentBuilder {

        @BindsInstance
        fun application(woyfitApp: WoyfitApp): ComponentBuilder

        fun build(): CoreComponent
    }
    class Builder private constructor() {

        companion object {

            fun build(application: WoyfitApp): CoreProvider {
                return DaggerCoreComponent.builder()
                    .application(application)
                    .build()
            }
        }
    }
}