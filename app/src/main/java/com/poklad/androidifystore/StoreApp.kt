package com.poklad.androidifystore

import android.app.Application
import com.poklad.androidifystore.di.AppComponent

class StoreApp : Application() {

    val appComponent: AppComponent by lazy {
        initializationComponent()
    }

    private fun initializationComponent(): AppComponent {
        TODO()
    }

}