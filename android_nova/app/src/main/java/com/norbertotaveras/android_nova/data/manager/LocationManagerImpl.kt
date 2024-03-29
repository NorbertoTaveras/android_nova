package com.norbertotaveras.android_nova.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.norbertotaveras.android_nova.domain.manager.LocationManager
import com.norbertotaveras.android_nova.utils.constants.Const
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocationManagerImpl(
    private val context: Context
): LocationManager {
    override suspend fun storeLocation(context: Context, latitude: Double, longitude: Double) {
        context.dataStore.edit { preferences ->
            preferences[PreferenceKeys.LATITUDE_KEY] = latitude
            preferences[PreferenceKeys.LONGITUDE_KEY] = longitude
        }
    }

    /*val Context.latitudeFlow: Flow<Double?>
        get() = dataStore.data.map { preferences ->
            preferences[PreferenceKeys.LATITUDE_KEY]
        }

    val Context.longitudeFlow: Flow<Double?>
        get() = dataStore.data.map { preferences ->
            preferences[PreferenceKeys.LONGITUDE_KEY]
        }*/

    override val latitudeFlow: Flow<Double?> = context.dataStore.data
        .map { preferences -> preferences[PreferenceKeys.LATITUDE_KEY] }

    override val longitudeFlow: Flow<Double?> = context.dataStore.data
        .map { preferences -> preferences[PreferenceKeys.LONGITUDE_KEY] }
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Const.USER_LOCATION)
private object PreferenceKeys {
    val LATITUDE_KEY = doublePreferencesKey("latitude_key")
    val LONGITUDE_KEY = doublePreferencesKey("longitude_key")
}