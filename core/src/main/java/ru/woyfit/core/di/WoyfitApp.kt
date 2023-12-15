package ru.woyfit.core.di

import android.content.Context
import ru.woyfit.core.di.provider.CoreProvider

interface WoyfitApp {

    fun getApplicationContext(): Context
    fun getApplicationProvider(): CoreProvider
    fun getPreference(): CoreAuthPrefs
}