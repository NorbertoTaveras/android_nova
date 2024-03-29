package com.norbertotaveras.android_nova.di.module

import com.norbertotaveras.android_nova.data.local.dao.NewsDao
import com.norbertotaveras.android_nova.data.remote.service.news.NewsFwkService
import com.norbertotaveras.android_nova.data.remote.service.weather.WeatherFwkService
import com.norbertotaveras.android_nova.data.repository.news.NewsFwkRepositoryImpl
import com.norbertotaveras.android_nova.data.repository.weather.WeatherFwkRepositoryImpl
import com.norbertotaveras.android_nova.domain.repository.news.NewsFwkRepository
import com.norbertotaveras.android_nova.domain.repository.weather.WeatherFwkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideNewsFwkRepository(
        service: NewsFwkService,
        newsDao: NewsDao
    ): NewsFwkRepository {
        return NewsFwkRepositoryImpl(
            service = service,
            newsDao = newsDao
        )
    }

    @Singleton
    @Provides
    fun provideWeatherFwkRepository(
        service: WeatherFwkService,
    ): WeatherFwkRepository {
        return WeatherFwkRepositoryImpl(service = service)
    }
}