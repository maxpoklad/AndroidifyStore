package com.poklad.androidifystore.di.modules

import com.poklad.androidifystore.domain.usecases.GetAllProductsUseCase
import com.poklad.androidifystore.presentation.ui.screens.all_products.AllProductsViewModel
import com.poklad.androidifystore.utils.CoroutineDispatchersProvider
import dagger.Module
import dagger.Provides

@Module
object ViewModelModule {
    @Provides
    fun provideAllProductsViewModel(
        coroutineDispatchersProvider: CoroutineDispatchersProvider,
        getAllProductsUseCase: GetAllProductsUseCase
    ): AllProductsViewModel {
        return AllProductsViewModel(
            coroutineDispatchersProvider,
            getAllProductsUseCase
        )
    }
}
