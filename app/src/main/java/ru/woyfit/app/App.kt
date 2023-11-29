package ru.woyfit.app

import android.app.Application
import ru.woyfit.core.di.CoreAuthPrefs
import ru.woyfit.core.di.WoyfitApp
import ru.woyfit.core.di.provider.CoreProvider
import ru.woyfit.data.local.preference.ApplicationPreference
import ru.woyfit.di.component.AppComponent
import javax.inject.Inject


class App: Application(), WoyfitApp {

    lateinit var appComponent: AppComponent
    @Inject
    lateinit var mPrefs: ApplicationPreference
    override fun onCreate() {
        super.onCreate()

        initializeInjector()
    }

    private fun initializeInjector() {
        appComponent = AppComponent.Builder.build(this)
    }

    override fun getApplicationProvider(): CoreProvider {
        return appComponent
    }

    override fun getPreference(): CoreAuthPrefs = mPrefs
}