package ru.woyfit.core.di.module

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import ru.woyfit.core.di.WoyfitApp
import javax.inject.Singleton

@Module
class CoreModule {
    fun provideApplicationContext(app: WoyfitApp): Context = app.getApplicationContext()

    @Singleton
    @Provides
    fun providePreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("def_prefs", Context.MODE_PRIVATE)
    }
}