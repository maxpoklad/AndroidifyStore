package com.poklad.androidifystore.presentation.ui.screens.home

import com.poklad.androidifystore.R
import com.poklad.androidifystore.domain.model.ProductItem
import com.poklad.androidifystore.domain.usecases.GetProductsBySpecificCategoryUseCase
import com.poklad.androidifystore.presentation.ui.base.BaseViewModel
import com.poklad.androidifystore.utils.CoroutineDispatchersProvider
import com.poklad.androidifystore.utils.Resource
import com.poklad.androidifystore.utils.log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
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
        fetchMenClothes()
        fetchWomenClothes()
    }

    private val _menClothesList =
        MutableStateFlow<Resource<List<ProductItem>>>(Resource.Loading())
    val menClothesList = _menClothesList.asStateFlow()

    private val _womenClothesList =
        MutableStateFlow<Resource<List<ProductItem>>>(Resource.Loading())
    val womenClothesList = _womenClothesList.asStateFlow()

    private fun fetchMenClothes() {
        launchCoroutineIO {
            getProductsByCategory.execute("men's clothing")
                .catch { cause: Throwable ->
                    _menClothesList.value = Resource.Error(cause)
                }
                .collect { productList ->
                    _menClothesList.value = Resource.Success(productList)
                }
        }
    }

    private fun fetchWomenClothes() {
        launchCoroutineIO {
            getProductsByCategory.execute("women's clothing")
                .catch { cause: Throwable ->
                    _womenClothesList.value = Resource.Error(cause)
                }
                .collect { productList ->
                    _womenClothesList.value = Resource.Success(productList)
                }
        }
    }

}