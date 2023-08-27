package com.poklad.androidifystore.presentation.ui.screens.all_products

import androidx.lifecycle.viewModelScope
import com.poklad.androidifystore.domain.model.ProductItem
import com.poklad.androidifystore.domain.usecases.GetAllProductsUseCase
import com.poklad.androidifystore.presentation.ui.base.BaseViewModel
import com.poklad.androidifystore.utils.CoroutineDispatchersProvider
import com.poklad.androidifystore.utils.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class AllProductsViewModel @Inject constructor(
    coroutineDispatchersProvider: CoroutineDispatchersProvider,
    private val getAllProductsUseCase: GetAllProductsUseCase,
) : BaseViewModel(coroutineDispatchersProvider) {
    private val _products = MutableStateFlow<Resource<List<ProductItem>>>(Resource.Loading())
    val products = _products.asStateFlow()
    override val coroutineExceptionHandler: CoroutineExceptionHandler
        get() = CoroutineExceptionHandler { _, throwable ->
            _products.value = Resource.Error(throwable)
        }

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            getAllProductsUseCase.execute(Unit)
                .catch { cause: Throwable ->
                    _products.value = Resource.Error(cause)
                }
                .collect { productList ->
                    _products.value = Resource.Success(productList)
                }
        }
    }
}

