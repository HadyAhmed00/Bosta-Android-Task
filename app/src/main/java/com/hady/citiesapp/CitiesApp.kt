package com.hady.citiesapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class CitiesApp: Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialize Timber or any other libraries here
        Timber.plant(Timber.DebugTree())
    }
}