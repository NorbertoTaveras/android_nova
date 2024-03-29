package com.norbertotaveras.android_nova.utils.location

import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlin.coroutines.resume

object LocationHelper {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    fun init(client: FusedLocationProviderClient) {
        fusedLocationProviderClient = client
    }

    suspend fun getCurrentLocation(
        context: Context
    ): Result<Location> = withContext(Dispatchers.IO) {
        try {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                throw SecurityException("Location permission not granted")
            }

            if (!::fusedLocationProviderClient.isInitialized) {
                throw IllegalStateException("FusedLocationProviderClient is not initialized.")
            }

            val locationResult: Result<Location> = suspendCancellableCoroutine { continuation ->
                fusedLocationProviderClient.lastLocation.addOnSuccessListener { location: Location? ->
                    if (location != null) {
                        continuation.resume(Result.success(location))
                    } else {
                        continuation.resume(Result.failure(Exception("Location not found")))
                    }
                }.addOnFailureListener { exception ->
                    continuation.resume(Result.failure(exception))
                }
            }
            locationResult
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    @SuppressLint("MissingPermission")
    fun startLocationUpdates(
        context: Context,
        locationUpdateInterval: Long,
        fastestLocationInterval: Long
    ) = callbackFlow {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            close(Exception("Location permission not granted"))
            return@callbackFlow
        }

        val locationRequest = LocationRequest.Builder(locationUpdateInterval)
            .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
            .setMinUpdateIntervalMillis(locationUpdateInterval)
            .setMaxUpdateDelayMillis(fastestLocationInterval)
            .build()

        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.lastLocation?.let { location ->
                    trySend(Result.success(location)).isSuccess
                }
            }
        }

        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())

        awaitClose {
            fusedLocationProviderClient.removeLocationUpdates(locationCallback)
        }
    }
}

