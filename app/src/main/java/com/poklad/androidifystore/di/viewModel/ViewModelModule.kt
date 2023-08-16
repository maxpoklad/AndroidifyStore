package com.poklad.androidifystore.di.viewModel

import com.poklad.androidifystore.di.annotations.ViewModelKey
import com.poklad.androidifystore.domain.usecases.GetAllProductsUseCase
import com.poklad.androidifystore.presentation.ui.screens.all_products.AllProductsViewModel
import com.poklad.androidifystore.utils.CoroutineDispatchersProvider
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelFactoryModule::class])
class ViewModelModule {
    @IntoMap
    @ViewModelKey(AllProductsViewModel::class)
    @Provides
    fun provideAllProductViewModel(
        coroutineDispatchersProvider: CoroutineDispatchersProvider,
        getAllProductsUseCase: GetAllProductsUseCase
    ): AllProductsViewModel {
        return AllProductsViewModel(
            coroutineDispatchersProvider = coroutineDispatchersProvider,
            getAllProductsUseCase = getAllProductsUseCase
        )
    }
}
