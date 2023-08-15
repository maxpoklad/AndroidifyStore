package com.poklad.androidifystore.presentation.ui.screens.all_products

import com.poklad.androidifystore.domain.model.ProductItem
import com.poklad.androidifystore.domain.usecases.GetAllProductsUseCase
import com.poklad.androidifystore.presentation.ui.base.BaseViewModel
import com.poklad.androidifystore.utils.CoroutineDispatchersProvider
import com.poklad.androidifystore.utils.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.emitAll
import javax.inject.Inject

class AllProductsViewModel /*@Inject constructor*/(
    coroutineDispatchersProvider: CoroutineDispatchersProvider,
    private val getAllProductsUseCase: GetAllProductsUseCase,
) : BaseViewModel(coroutineDispatchersProvider) {

    private val _products = MutableSharedFlow<Resource<List<ProductItem>>>()
    val products = _products.asSharedFlow()
    override val coroutineExceptionHandler: CoroutineExceptionHandler
        get() = CoroutineExceptionHandler { _, throwable ->
            _products.tryEmit(Resource.Error(throwable))
        }

    init {
        loadProducts()
    }

    private fun loadProducts() {
        launchCoroutineIO {
            _products.emit(Resource.Loading())
            try {
                val productsList = getAllProductsUseCase.execute(Unit)
                _products.emitAll(productsList)
            } catch (e: Exception) {
                _products.emit(Resource.Error(e.cause ?: Throwable(e.message)))
            }
        }
    }
}

