package ru.woyfit.core.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.woyfit.core.di.CoreAuthPrefs
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    private val requestTimeOutTime: Long = 40
    private val baseUrl: String = "http://10.0.2.2:8080"
    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder.readTimeout(requestTimeOutTime, TimeUnit.SECONDS)
            .writeTimeout(requestTimeOutTime, TimeUnit.SECONDS)
            .connectTimeout(requestTimeOutTime, TimeUnit.SECONDS)

//        if (BuildConfig.DEBUG) {
//            builder.addNetworkInterceptor(
//                HttpLoggingInterceptor()
//                    .apply {
//                        level = HttpLoggingInterceptor.Level.BODY
//                    })
//            builder.addInterceptor(AccessReasonInterceptor())
//        }

//        builder.addInterceptor(BrandIdInterceptor(brandId))
//        builder.addInterceptor(PlatformInterceptor())
//        builder.addInterceptor(HeaderTokenInterceptor(prefs))
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val builder = GsonBuilder()
        return builder.registerTypeAdapter(Date::class.java, object : JsonDeserializer<Date> {
            val DATE_FORMATS = arrayOf(
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.000'Z'").apply{
                    timeZone = TimeZone.getTimeZone("UTC")
                },
                SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
                SimpleDateFormat("yyyy-MM-dd HH:mm"),
                SimpleDateFormat("yyyy-MM-dd")
            )
            @Throws(JsonParseException::class)
            override fun deserialize(
                json: JsonElement, typeOfT: Type,
                context: JsonDeserializationContext
            ): Date {
                for(format in DATE_FORMATS) {
                    try {
                        return if(json.asString.isNotEmpty() && json.asString != null)
                            format.parse(json.asString) else
                            Date()
                    } catch (e: ParseException) {}
                }
                throw JsonParseException("Unparseable date: \"" + json.asString
                        + "\". Supported formats: " + DATE_FORMATS.contentToString()
                )
            }
        })
            .registerTypeAdapter(Date::class.java, object : JsonSerializer<Date> {
                override fun serialize(
                    src: Date,
                    typeOfSrc: Type?,
                    context: JsonSerializationContext?
                ): JsonElement {
                    return JsonPrimitive(SimpleDateFormat("yyyy-MM-dd").format(src))
                }
            })
            .serializeNulls()
            .create()
    }
}