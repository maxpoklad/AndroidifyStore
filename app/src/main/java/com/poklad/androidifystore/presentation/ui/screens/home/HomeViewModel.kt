package com.poklad.androidifystore.presentation.ui.screens.home

import com.poklad.androidifystore.R
import com.poklad.androidifystore.domain.model.ProductItem
import com.poklad.androidifystore.domain.usecases.GetProductsBySpecificCategoryUseCase
import com.poklad.androidifystore.extensions.coRunCatching
import com.poklad.androidifystore.extensions.log
import com.poklad.androidifystore.presentation.ui.base.BaseViewModel
import com.poklad.androidifystore.utils.CoroutineDispatchersProvider
import com.poklad.androidifystore.utils.PresentationConstants
import com.poklad.androidifystore.utils.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

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

    private val _errorWomanList = MutableStateFlow<String?>(null)
    val errorWomanList = _errorWomanList.asStateFlow()

    private val _errorMenList = MutableStateFlow<String?>(PresentationConstants.EMPTY_STRING)
    val errorMenList = _errorMenList.asStateFlow()
    /*    private val _menClothesList =
            MutableStateFlow<Resource<List<ProductItem>>>(Resource.Loading())
        val menClothesList = _menClothesList.asStateFlow()

        private val _womenClothesList =
            MutableStateFlow<Resource<List<ProductItem>>>(Resource.Loading())
        val womenClothesList = _womenClothesList.asStateFlow()*/
    /*    private fun fetchData() {
            launchMain(withLoader = true) {
                _menClothesList.value = executeWithResource {
                    withContext(dispatchers.getIO()) {
                        getProductsByCategory.execute(PresentationConstants.MENS_CLOTHING)
                    }
                }

                _womenClothesList.value = executeWithResource {
                    withContext(dispatchers.getIO()) {
                        getProductsByCategory.execute(PresentationConstants.WOMENS_CLOTHING)
                    }
                }
            }
        }*/


    private fun fetchData() {
        launchMain(withLoader = true) {
            fetchMenClothes(this)
            fetchWomenClothes(this)
        }
    }


    private suspend fun fetchMenClothes(scope: CoroutineScope) {
        scope.coRunCatching {
            withContext(dispatchers.getIO()) {
                getProductsByCategory.execute(PresentationConstants.MENS_CLOTHING)
            }
        }.onSuccess { menList ->
            _menClothesList.value = menList
        }.onFailure {
            //todo handle error, Resource class?
        }
    }

    private suspend fun fetchWomenClothes(scope: CoroutineScope) {
        scope.coRunCatching {
            withContext(dispatchers.getIO()) { getProductsByCategory.execute(PresentationConstants.WOMENS_CLOTHING) }
        }.onSuccess { womenList ->
            _womenClothesList.value = womenList
        }.onFailure {
            //todo handle error, Resource class?
        }
    }

}