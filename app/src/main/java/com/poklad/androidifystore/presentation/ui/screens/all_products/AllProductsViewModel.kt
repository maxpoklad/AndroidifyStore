package com.poklad.androidifystore.presentation.ui.screens.all_products

import androidx.lifecycle.viewModelScope
import com.poklad.androidifystore.domain.model.ProductItem
import com.poklad.androidifystore.domain.usecases.GetAllProductsUseCase
import com.poklad.androidifystore.presentation.ui.base.BaseViewModel
import com.poklad.androidifystore.utils.CoroutineDispatchersProvider
import com.poklad.androidifystore.utils.Resource
import com.poklad.androidifystore.utils.log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

class AllProductsViewModel @Inject constructor(
    coroutineDispatchersProvider: CoroutineDispatchersProvider,
    private val getAllProductsUseCase: GetAllProductsUseCase,
) : BaseViewModel(coroutineDispatchersProvider) {

    /*private val _products = MutableSharedFlow<Resource<List<ProductItem>>>()
    val products = _products.asSharedFlow()*/
    private val _products = MutableStateFlow<Resource<List<ProductItem>>>(Resource.Loading())
    val products = _products.asStateFlow()
    override val coroutineExceptionHandler: CoroutineExceptionHandler
        get() = CoroutineExceptionHandler { _, throwable ->
            _products.value = Resource.Error(throwable)
        }


        init {
            loadProducts()
        }


    /*private fun loadProducts() {
        launchCoroutineIO {
            _products.emit(Resource.Loading())
            try {
                val productsList = getAllProductsUseCase.execute(Unit)
                log("loadProducts before emitAll $productsList")
                _products.emitAll(productsList)
                log("loadProducts after emitAll $productsList")

            } catch (e: Exception) {
                _products.emit(Resource.Error(e.cause ?: Throwable(e.message)))
            }
        }
    }*/

    private fun loadProducts() {
        viewModelScope.launch {
            getAllProductsUseCase.execute(Unit)
                .catch { cause: Throwable ->
                    _products.value = Resource.Error(cause)
                }
                .collect { productList ->
                    log("collect1 - $productList")
                    _products.value = Resource.Success(productList)
                    log("collect2 - $productList + "+ " ${Resource.Success(productList)}")
                }
        }
    }
}

