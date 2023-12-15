package ru.woyfit.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import ru.woyfit.app.App
import ru.woyfit.core.di.WoyfitApp
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: App): Application {
        return application
    }
}