package com.poklad.androidifystore.presentation.ui.screens.categories

import com.poklad.androidifystore.data.remote.model.ProductCategoryResponse
import com.poklad.androidifystore.domain.usecases.GetAllCategoriesUseCase
import com.poklad.androidifystore.presentation.ui.base.BaseViewModel
import com.poklad.androidifystore.utils.CoroutineDispatchersProvider
import com.poklad.androidifystore.utils.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

class CategoriesViewModel @Inject constructor(
    dispatchersProvider: CoroutineDispatchersProvider,
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase
) : BaseViewModel(dispatchersProvider) {
    override val coroutineExceptionHandler: CoroutineExceptionHandler
        get() = CoroutineExceptionHandler { _, throwable ->
            _categories.value = Resource.Error(throwable)
        }
    private val _categories =
        MutableStateFlow<Resource<List<ProductCategoryResponse>>>(Resource.Loading())
    val categories = _categories.asStateFlow()

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        launchCoroutineIO {
            getAllCategoriesUseCase.execute(Unit)
                .catch { cause: Throwable ->
                    _categories.value = Resource.Error(cause)
                }
                .collect { productList ->
                    _categories.value = Resource.Success(productList)
                }
        }
    }
}