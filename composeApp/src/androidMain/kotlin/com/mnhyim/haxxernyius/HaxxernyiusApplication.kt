package com.mnhyim.haxxernyius

import android.app.Application
import com.mnhyim.haxxernyius.di.initKoin
import org.koin.core.component.KoinComponent

class HaxxernyiusApplication: Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}