package com.app.travelchecklist

import android.app.Application
import io.realm.Realm

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}