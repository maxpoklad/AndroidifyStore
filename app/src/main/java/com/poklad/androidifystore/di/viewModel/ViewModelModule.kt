package com.poklad.androidifystore.di.viewModel

import androidx.lifecycle.ViewModel
import com.poklad.androidifystore.di.annotations.ViewModelKey
import com.poklad.androidifystore.presentation.ui.screens.all_products.AllProductsViewModel
import com.poklad.androidifystore.presentation.ui.screens.categories.CategoriesViewModel
import com.poklad.androidifystore.presentation.ui.screens.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AllProductsViewModel::class)
    abstract fun bindYourViewModel(yourViewModel: AllProductsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel
}
