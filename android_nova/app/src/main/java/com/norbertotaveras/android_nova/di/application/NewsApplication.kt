package com.norbertotaveras.android_nova.di.application

import android.app.Application
import com.google.android.gms.location.LocationServices
import com.norbertotaveras.android_nova.utils.location.LocationHelper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        LocationHelper.init(fusedLocationProviderClient)
    }
}