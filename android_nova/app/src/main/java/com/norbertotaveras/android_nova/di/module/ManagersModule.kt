package com.norbertotaveras.android_nova.di.module

import android.app.Application
import com.norbertotaveras.android_nova.data.manager.LocalUserManagerImpl
import com.norbertotaveras.android_nova.data.manager.LocationManagerImpl
import com.norbertotaveras.android_nova.domain.manager.LocalUserManager
import com.norbertotaveras.android_nova.domain.manager.LocationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ManagersModule {
    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(context = application)

    @Provides
    @Singleton
    fun provideLocationManager(
        application: Application
    ): LocationManager = LocationManagerImpl(context = application)
}