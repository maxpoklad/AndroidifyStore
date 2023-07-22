package com.poklad.androidifystore.di

import android.content.Context
import com.poklad.androidifystore.di.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component

@Component(modules = [NetworkModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}