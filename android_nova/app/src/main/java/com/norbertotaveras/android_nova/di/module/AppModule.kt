package com.norbertotaveras.android_nova.di.module

import android.app.Application
import androidx.room.Room
import com.norbertotaveras.android_nova.data.local.dao.NewsDao
import com.norbertotaveras.android_nova.data.local.database.NewsDatabase
import com.norbertotaveras.android_nova.data.local.database.NewsTypeConvertor
import com.norbertotaveras.android_nova.domain.manager.LocalUserManager
import com.norbertotaveras.android_nova.domain.repository.news.NewsFwkRepository
import com.norbertotaveras.android_nova.domain.repository.weather.WeatherFwkRepository
import com.norbertotaveras.android_nova.domain.usecase.app.AppEntryUseCases
import com.norbertotaveras.android_nova.domain.usecase.app.ReadAppEntryUseCase
import com.norbertotaveras.android_nova.domain.usecase.app.SaveAppEntryUseCase
import com.norbertotaveras.android_nova.domain.usecase.bookmark.DeleteArticleUseCase
import com.norbertotaveras.android_nova.domain.usecase.bookmark.GetArticleUseCase
import com.norbertotaveras.android_nova.domain.usecase.bookmark.GetArticlesUseCase
import com.norbertotaveras.android_nova.domain.usecase.bookmark.UpsertArticleUseCase
import com.norbertotaveras.android_nova.domain.usecase.news.GetHeadlinesNewsUseCase
import com.norbertotaveras.android_nova.domain.usecase.news.GetNewsUseCase
import com.norbertotaveras.android_nova.domain.usecase.news.NewsUseCases
import com.norbertotaveras.android_nova.domain.usecase.news.SearchNewsUseCase
import com.norbertotaveras.android_nova.domain.usecase.sources.GetSourcesUseCase
import com.norbertotaveras.android_nova.domain.usecase.weather.GetCurrentWeatherUseCase
import com.norbertotaveras.android_nova.domain.usecase.weather.GetForecastWeatherUseCase
import com.norbertotaveras.android_nova.domain.usecase.weather.WeatherUseCases
import com.norbertotaveras.android_nova.utils.constants.Const
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManger: LocalUserManager
    ): AppEntryUseCases = AppEntryUseCases(
        readAppEntryUseCase = ReadAppEntryUseCase(localUserManger),
        saveAppEntryUseCase = SaveAppEntryUseCase(localUserManger)
    )

    @Provides
    @Singleton
    fun provideNewsUseCases(
        repository: NewsFwkRepository,
        newsDao: NewsDao
    ): NewsUseCases = NewsUseCases(
        getNewsUseCase = GetNewsUseCase(repository = repository),
        searchNewsUseCase = SearchNewsUseCase(repository = repository),
        sourcesUseCase = GetSourcesUseCase(repository = repository),
        upsertArticleUseCase = UpsertArticleUseCase(repository = repository),
        getArticlesUseCase = GetArticlesUseCase(repository = repository),
        deleteArticleUseCase = DeleteArticleUseCase(repository = repository),
        getArticleUseCase = GetArticleUseCase(repository = repository),
        headlinesNewsUseCase = GetHeadlinesNewsUseCase(repository = repository)
    )

    @Provides
    @Singleton
    fun provideWeatherUseCases(
        repository: WeatherFwkRepository,
    ): WeatherUseCases = WeatherUseCases(
        getCurrentWeatherUseCase = GetCurrentWeatherUseCase(repository = repository),
        getForecastWeatherUseCase = GetForecastWeatherUseCase(repository = repository)
    )

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = Const.NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao
}