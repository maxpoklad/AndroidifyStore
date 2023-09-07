package com.poklad.androidifystore.presentation.ui.screens.all_products

import com.poklad.androidifystore.domain.model.ProductItem
import com.poklad.androidifystore.domain.usecases.GetAllProductsUseCase
import com.poklad.androidifystore.extensions.coRunCatching
import com.poklad.androidifystore.extensions.log
import com.poklad.androidifystore.presentation.ui.base.BaseViewModel
import com.poklad.androidifystore.utils.CoroutineDispatchersProvider
import com.poklad.androidifystore.utils.PresentationConstants
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AllProductsViewModel @Inject constructor(
    coroutineDispatchersProvider: CoroutineDispatchersProvider,
    private val getAllProductsUseCase: GetAllProductsUseCase,
) : BaseViewModel(coroutineDispatchersProvider) {

    private val _products = MutableStateFlow<List<ProductItem>>(emptyList())
    val products = _products.asStateFlow()

    private val _productsError = MutableStateFlow(PresentationConstants.EMPTY_STRING)
    val productsError = _productsError.asStateFlow()
    override val coroutineExceptionHandler: CoroutineExceptionHandler
        get() = CoroutineExceptionHandler { _, throwable ->
            log(throwable.message.toString())
        }

    init {
        loadProducts()
    }

    private fun loadProducts() {
        launchMain(withLoader = true) {
            fetchProducts(this)
        }
    }

    private suspend fun fetchProducts(scope: CoroutineScope) {
        scope.coRunCatching {
            withContext(dispatchers.getIO()) {
                getAllProductsUseCase.execute(Unit)
            }
        }.onSuccess { productList ->
            _products.value = productList
        }.onFailure {
            //handleError
        }
    }
}

