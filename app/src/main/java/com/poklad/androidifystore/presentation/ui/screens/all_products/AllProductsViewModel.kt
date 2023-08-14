package com.poklad.androidifystore.presentation.ui.screens.all_products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.poklad.androidifystore.domain.model.ProductItem
import com.poklad.androidifystore.domain.usecases.GetAllProductsUseCase
import com.poklad.androidifystore.presentation.ui.base.BaseViewModel
import com.poklad.androidifystore.utils.CoroutineDispatchersProvider
import com.poklad.androidifystore.utils.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class AllProductsViewModel @Inject constructor(
    coroutineDispatchersProvider: CoroutineDispatchersProvider,
    private val getAllProductsUseCase: GetAllProductsUseCase,
) : BaseViewModel(coroutineDispatchersProvider) {

    private val _allProductsList = MutableSharedFlow<List<ProductItem>>()
    val allProductsList = _allProductsList.asSharedFlow()

    private val _allProductsLiveData = MutableLiveData<List<ProductItem>>()
    val allProductsLiveData: LiveData<List<ProductItem>> = _allProductsLiveData
    override val coroutineExceptionHandler: CoroutineExceptionHandler
        get() = CoroutineExceptionHandler { _, throwable ->
            val message = throwable.message
        }

    fun getAllProductsLiveData() {
        launchCoroutineIO {
            getAllProductsUseCase.execute(Unit).collect {
                TODO()
            }
        }
    }

    fun getAllProducts() {
        launchCoroutineIO {
            try {
                val products = getAllProductsUseCase.execute(Unit)
                products.collect { productsResource ->
                    when (productsResource) {
                        is Resource.Success -> {
                            productsResource.data?.let { product ->
                                _allProductsList.emit(product)
                            }
                        }

                        is Resource.Error -> {

                        }

                        is Resource.Loading -> {

                        }
                    }
                }
            } catch (e: Throwable) {

            }
        }
    }
}

