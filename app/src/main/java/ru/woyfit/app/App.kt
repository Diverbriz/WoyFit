package ru.woyfit.app

import android.app.Application
import ru.woyfit.di.component.AppComponent
import ru.woyfit.di.component.DaggerAppComponent
import ru.woyfit.di.module.AppModule

class App: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        val appModule = AppModule(context = this)

        appComponent = DaggerAppComponent.builder()
            .appModule(appModule)
            .build()
    }
}