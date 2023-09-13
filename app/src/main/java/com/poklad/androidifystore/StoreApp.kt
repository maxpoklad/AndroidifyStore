package com.poklad.androidifystore

import android.app.Application
import com.poklad.androidifystore.di.components.AppComponent
import com.poklad.androidifystore.di.components.DaggerAppComponent

class StoreApp : Application() {
    override fun onCreate() {
        super.onCreate()
        daggerComponent = DaggerAppComponent.factory().create(applicationContext)
    }

    companion object {
        lateinit var daggerComponent: AppComponent
    }
}
