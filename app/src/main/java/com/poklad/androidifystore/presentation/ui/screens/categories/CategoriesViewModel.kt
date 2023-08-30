package com.poklad.androidifystore.presentation.ui.screens.categories

import com.poklad.androidifystore.domain.usecases.GetAllCategoriesUseCase
import com.poklad.androidifystore.presentation.ui.base.BaseViewModel
import com.poklad.androidifystore.utils.CoroutineDispatchersProvider
import com.poklad.androidifystore.utils.Resource
import com.poklad.androidifystore.utils.log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
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
        MutableStateFlow<Resource<List<String>>>(Resource.Loading())
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
                .collect { categoryList ->
                    val modifyList = categoryList.toMutableList()
                    modifyList.add(0, "all products")//todo it is ok?
                    _categories.value = Resource.Success(modifyList)
                    log("vm $categoryList")
                }
        }
    }
}