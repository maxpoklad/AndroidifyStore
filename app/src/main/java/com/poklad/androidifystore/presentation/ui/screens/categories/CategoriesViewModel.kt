package com.poklad.androidifystore.presentation.ui.screens.categories

import com.poklad.androidifystore.domain.usecases.GetAllCategoriesUseCase
import com.poklad.androidifystore.extensions.coRunCatching
import com.poklad.androidifystore.extensions.log
import com.poklad.androidifystore.presentation.ui.base.BaseViewModel
import com.poklad.androidifystore.utils.CoroutineDispatchersProvider
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class CategoriesViewModel @Inject constructor(
    dispatchersProvider: CoroutineDispatchersProvider,
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase
) : BaseViewModel(dispatchersProvider) {
    override val coroutineExceptionHandler: CoroutineExceptionHandler
        get() = CoroutineExceptionHandler { _, throwable ->
            log(throwable.message.toString())
        }
    private val _categories =
        MutableStateFlow<List<String>>(emptyList())
    val categories = _categories.asStateFlow()

    init {
        loadCategories()
    }

    private fun loadCategories() {
        launchMain(withLoader = true) {
            fetchCategories(this)
        }
    }

    private suspend fun fetchCategories(scope: CoroutineScope) {
        scope.coRunCatching {
            getAllCategoriesUseCase.execute(Unit)
        }.onSuccess { categoriesList ->
            _categories.value = categoriesList.toMutableList()
        }.onFailure {
            _categories.value = emptyList()
        }
    }
}