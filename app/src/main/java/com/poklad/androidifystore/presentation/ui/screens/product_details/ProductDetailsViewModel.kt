package com.poklad.androidifystore.presentation.ui.screens.product_details

import androidx.lifecycle.viewModelScope
import com.poklad.androidifystore.domain.model.ProductItem
import com.poklad.androidifystore.domain.usecases.GetProductsBySpecificCategoryUseCase
import com.poklad.androidifystore.extensions.coRunCatching
import com.poklad.androidifystore.extensions.log
import com.poklad.androidifystore.presentation.ui.base.BaseViewModel
import com.poklad.androidifystore.utils.CoroutineDispatchersProvider
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class ProductDetailsViewModel @Inject constructor(
    coroutineDispatchersProvider: CoroutineDispatchersProvider,
    private val getProductsBySpecificCategoryUseCase: GetProductsBySpecificCategoryUseCase
) : BaseViewModel(coroutineDispatchersProvider) {
    override val coroutineExceptionHandler: CoroutineExceptionHandler
        get() = CoroutineExceptionHandler { _, throwable ->
            log(throwable.message.toString())
        }


    private val _productList = MutableStateFlow<List<ProductItem>>(emptyList())
    val productList = _productList.asStateFlow()

    fun fetchData(category: String?, productId: Int) {
        launchMain(withLoader = true) {
            fetchProductsByCategory(
                this, category, productId
            )
        }
    }

    private suspend fun fetchProductsByCategory(
        scope: CoroutineScope,
        category: String?,
        productId: Int
    ) {
        category?.let {
            scope.coRunCatching {
                getProductsBySpecificCategoryUseCase.execute(it)
            }.onSuccess { list ->
                val filteredList = list.filter { it.id != productId }
                _productList.value = filteredList
            }.onFailure {
                // TODO:
            }
        }
    }
}

