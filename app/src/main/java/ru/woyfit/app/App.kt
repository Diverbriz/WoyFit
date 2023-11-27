package ru.woyfit.app

import android.app.Application
import ru.woyfit.core.di.WoyfitApp
import ru.woyfit.di.component.AppComponent
import ru.woyfit.di.component.DaggerAppComponent
import ru.woyfit.di.module.AppModule


class App: Application(), WoyfitApp {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        val appModule = AppModule(context = this)

        appComponent = DaggerAppComponent.builder()
            .appModule(appModule)
            .build()
    }
}