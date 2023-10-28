package ru.woyfit.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.woyfit.data.local.preference.ApplicationPreference
import ru.woyfit.data.local.preference.BaseSharedPreference
import ru.woyfit.data.repository.UserRepositoryImpl
import ru.woyfit.domain.local.preference.Preference
import ru.woyfit.domain.user.UserRepository

@Module
class DataModule {

    @Provides
    fun providePreference(context: Context): Preference{
        return BaseSharedPreference(context = context)
    }

    @Provides
    fun provideApplicationPreference(preference: Preference): ApplicationPreference{
        return ApplicationPreference(mDefaultPreference = preference)
    }

    @Provides
    fun provideUserRepository(applicationPreference: ApplicationPreference): UserRepository{
        return UserRepositoryImpl(applicationPreferences = applicationPreference)
    }
}