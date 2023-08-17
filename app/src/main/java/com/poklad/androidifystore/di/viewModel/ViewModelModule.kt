package com.poklad.androidifystore.di.viewModel

import androidx.lifecycle.ViewModel
import com.poklad.androidifystore.di.annotations.ViewModelKey
import com.poklad.androidifystore.presentation.ui.screens.all_products.AllProductsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AllProductsViewModel::class)
    abstract fun bindYourViewModel(yourViewModel: AllProductsViewModel): ViewModel
}
