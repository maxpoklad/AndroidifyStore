package com.poklad.androidifystore

import android.app.Application
import com.poklad.androidifystore.di.AppComponent
import com.poklad.androidifystore.di.DaggerAppComponent

class StoreApp : Application() {

    val appComponent: AppComponent by lazy {
        initializationComponent()
    }

    private fun initializationComponent(): AppComponent {
        return DaggerAppComponent.factory().create(this)
    }

}