package com.poklad.androidifystore.di.components

import android.content.Context
import com.poklad.androidifystore.di.annotations.ApplicationScope
import com.poklad.androidifystore.di.modules.DispatcherModule
import com.poklad.androidifystore.di.modules.FirebaseAuthModule
import com.poklad.androidifystore.di.modules.NetworkModule
import com.poklad.androidifystore.di.modules.RepositoryModule
import com.poklad.androidifystore.di.viewModel.ViewModelFactoryModule
import com.poklad.androidifystore.di.viewModel.ViewModelModule
import com.poklad.androidifystore.presentation.MainActivity
import com.poklad.androidifystore.presentation.ui.screens.all_products.AllProductsFragment
import com.poklad.androidifystore.presentation.ui.screens.categories.CategoriesFragment
import com.poklad.androidifystore.presentation.ui.screens.home.HomeFragment
import com.poklad.androidifystore.presentation.ui.screens.product_details.ProductDetailsFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        NetworkModule::class,
        DispatcherModule::class,
        ViewModelModule::class,
        ViewModelFactoryModule::class,
        RepositoryModule::class,
        FirebaseAuthModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(fragment: AllProductsFragment)
    fun inject(activity: MainActivity)
    fun inject(fragment: ProductDetailsFragment)
    fun inject(fragment: HomeFragment)
    fun inject(fragment: CategoriesFragment)
}