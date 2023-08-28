package com.poklad.androidifystore.presentation.ui.screens.home

import com.poklad.androidifystore.R
import com.poklad.androidifystore.presentation.ui.base.BaseViewModel
import com.poklad.androidifystore.utils.CoroutineDispatchersProvider
import com.poklad.androidifystore.utils.log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    dispatchersProvider: CoroutineDispatchersProvider
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
    }
}