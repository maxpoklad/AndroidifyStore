package com.poklad.androidifystore.di

import android.content.Context
import com.poklad.androidifystore.di.modules.DispatcherModule
import com.poklad.androidifystore.di.modules.NetworkModule
import com.poklad.androidifystore.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component

@Component(modules = [NetworkModule::class, DispatcherModule::class,ViewModelModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}