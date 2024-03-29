package com.norbertotaveras.android_nova.di.module

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import com.norbertotaveras.android_nova.BuildConfig
import com.norbertotaveras.android_nova.utils.android.AndroidUtils
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.norbertotaveras.android_nova.data.remote.service.news.NewsFwkService
import com.norbertotaveras.android_nova.data.remote.service.weather.WeatherFwkService
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Singleton
    @Provides
    fun provideNewsFwkService(@ApplicationContext context: Context) : NewsFwkService {
        return buildService(
            context = context,
            clazz = NewsFwkService::class.java,
            baseUrl = BuildConfig.API_URL,
            addAuth = true,
            addKeyParam = false,
            addExpose = false,
            header = "X-Api-Key",
            key = BuildConfig.API_KEY
        )
    }

    @Singleton
    @Provides
    fun provideWeatherFwkService(@ApplicationContext context: Context) : WeatherFwkService {
        return buildService(
            context = context,
            clazz = WeatherFwkService::class.java,
            baseUrl = BuildConfig.WEATHER_API_URL,
            addAuth = false,
            addExpose = false,
            addKeyParam = true,
            header = "",
            key = BuildConfig.WEATHER_API_KEY
        )
    }

    private fun <T> buildService(
        context: Context,
        clazz: Class<T>,
        baseUrl: String,
        addAuth: Boolean,
        addKeyParam: Boolean,
        addExpose: Boolean,
        header: String,
        key: String,
    ): T {
        val gson = when(addExpose) {
            true -> GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
            else -> GsonBuilder().create()
        }
        val retrofit = Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient.Builder().apply {
                    this.connectTimeout(30L, TimeUnit.SECONDS)
                    this.readTimeout(30L, TimeUnit.SECONDS)
                }.addInterceptor(HttpLoggingInterceptor().apply {
                    this.level =
                        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                        else HttpLoggingInterceptor.Level.NONE

                }).addInterceptor(Interceptor { chain ->
                    val originalRequest = chain.request()
                    val request: Request = chain.request().newBuilder()
                        .apply {
                            if (addAuth) {
                                //addHeader("accept", "application/json")
                                addHeader(header, key)
                            }
                            if (addKeyParam) {
                                val url = originalRequest.url.newBuilder().addQueryParameter("key", key)
                                    .build()
                                url(url = url)
                            }
                        }
                        //.addUserAgentHeader(context = context)
                        .build()
                    Log.d("RequestURLInterceptor", "Request URL: ${request.url}")
                    chain.proceed(request)
                })
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit.create(clazz)
    }

    private fun Request.Builder.addUserAgentHeader(
        context: Context
    ): Request.Builder {
        return addHeader(
            "User-Agent",
            AndroidUtils.generateCustomUserAgent(context, "NEWS/${BuildConfig.VERSION_CODE}")
        )
    }
}