package com.poklad.androidifystore.di.components

import android.content.Context
import com.poklad.androidifystore.di.modules.DispatcherModule
import com.poklad.androidifystore.di.modules.NetworkModule
import com.poklad.androidifystore.di.modules.RepositoryModule
import com.poklad.androidifystore.di.viewModel.ViewModelFactoryModule
import com.poklad.androidifystore.di.viewModel.ViewModelModule
import com.poklad.androidifystore.presentation.MainActivity
import com.poklad.androidifystore.presentation.ui.screens.all_products.AllProductsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        DispatcherModule::class,
        ViewModelModule::class,
        ViewModelFactoryModule::class,
        RepositoryModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(fragment: AllProductsFragment)
    fun inject(activity: MainActivity)
}