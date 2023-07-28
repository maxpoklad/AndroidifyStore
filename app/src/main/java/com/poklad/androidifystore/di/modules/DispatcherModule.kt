package com.poklad.androidifystore.di.modules

import com.poklad.androidifystore.utils.DispatchersProvider
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
object DispatcherModule {

    @Singleton
    @Provides
    fun provideDispatchersModule(): DispatchersProvider = object : DispatchersProvider {
        override fun getIO(): CoroutineDispatcher = Dispatchers.IO
        override fun getMain(): CoroutineDispatcher = Dispatchers.Main
        override fun getUnconfined(): CoroutineDispatcher = Dispatchers.Unconfined
        override fun getDefault(): CoroutineDispatcher = Dispatchers.Default
    }
}