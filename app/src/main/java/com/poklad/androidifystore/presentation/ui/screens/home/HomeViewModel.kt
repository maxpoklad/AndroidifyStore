package com.poklad.androidifystore.presentation.ui.screens.home

import com.poklad.androidifystore.R
import com.poklad.androidifystore.domain.model.ProductItem
import com.poklad.androidifystore.domain.usecases.GetProductsBySpecificCategoryUseCase
import com.poklad.androidifystore.extensions.coRunCatching
import com.poklad.androidifystore.presentation.ui.base.BaseViewModel
import com.poklad.androidifystore.utils.CoroutineDispatchersProvider
import com.poklad.androidifystore.utils.log
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

class HomeViewModel @Inject constructor(
    dispatchersProvider: CoroutineDispatchersProvider,
    private val getProductsByCategory: GetProductsBySpecificCategoryUseCase
) : BaseViewModel(dispatchersProvider) {
    override val coroutineExceptionHandler: CoroutineExceptionHandler
        get() = CoroutineExceptionHandler { _, throwable ->
            log(throwable.message.toString())
        }
    private val _imageList = MutableStateFlow<List<Int>>(emptyList())
    val imageList = _imageList.asStateFlow()

    init {
        _imageList.value = listOf(
            R.drawable.pic_slider_comp,
            R.drawable.pic_slider_jewerly,
            R.drawable.pic_slider_men,
            R.drawable.pic_slider_womem
        )
        fetchData()
    }

    private val _menClothesList = MutableStateFlow<List<ProductItem>>(emptyList())
    val menClothesList = _menClothesList.asStateFlow()

    private val _womenClothesList = MutableStateFlow<List<ProductItem>>(emptyList())
    val womenClothesList = _womenClothesList.asStateFlow()

    // TODO: add as constant
    private val _errorWomanList = MutableStateFlow("")
    val errorWomanList = _errorWomanList.asStateFlow()

//    private fun fetchMenClothes() {
//        launchCoroutineIO {
//            try {
//                showLoader()
//                val list = getProductsByCategory.execute("men's clothing")
//                _menClothesList.value = list
//            } catch (ex: Exception) {
//               // error
//            } finally {
//                hideLoader()
//            }
//        }
//    }

    private fun fetchData() {
        launchMain(withLoader = true) {
            fetchMenClothes(this)
            fetchWomenClothes(this)
        }
    }

    private suspend fun fetchMenClothes(scope: CoroutineScope) {
        scope.coRunCatching {
            // TODO: add as constant
            withContext(dispatchers.getIO()) { getProductsByCategory.execute("men's clothing") }
        }.onSuccess { menList ->
            _menClothesList.value = menList
        }.onFailure {
            //handleError
        }
    }

    private suspend fun fetchWomenClothes(scope: CoroutineScope) {
        scope.coRunCatching {
            // TODO: add as constant
            withContext(dispatchers.getIO()) { getProductsByCategory.execute("women's clothing") }
        }.onSuccess { menList ->
            _womenClothesList.value = menList
        }.onFailure {
            //handleError
        }
    }

}