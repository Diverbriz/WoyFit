package ru.woyfit.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.woyfit.core.coroutines.DefaultDispatchersProvider
import ru.woyfit.core.coroutines.DispatchersProvider
import ru.woyfit.data.network.WoyfitRestApi
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class WoyfitModule {

    @Provides
    fun provideWoyfitService(
        retrofit: Retrofit
    ): WoyfitRestApi{
        return retrofit.create(WoyfitRestApi::class.java)
    }

    @Provides
    fun provideDispatchers(): DispatchersProvider {
        return DefaultDispatchersProvider()
    }

    private val requestTimeOutTime: Long = 4
    private val baseUrl: String = "http://10.0.2.2:8080"
    @Provides
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    @Provides
    fun provideHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder.readTimeout(requestTimeOutTime, TimeUnit.SECONDS)
            .writeTimeout(requestTimeOutTime, TimeUnit.SECONDS)
            .connectTimeout(requestTimeOutTime, TimeUnit.SECONDS)

//        builder.addInterceptor(HeaderTokenInterceptor(prefs))
        return builder.build()
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }
}