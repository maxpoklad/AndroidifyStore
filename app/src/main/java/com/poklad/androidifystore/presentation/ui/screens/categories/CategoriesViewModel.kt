package com.poklad.androidifystore.presentation.ui.screens.categories

import com.poklad.androidifystore.presentation.ui.base.BaseViewModel
import com.poklad.androidifystore.utils.CoroutineDispatchersProvider
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

class CategoriesViewModel @Inject constructor(
    dispatchersProvider: CoroutineDispatchersProvider
) : BaseViewModel(dispatchersProvider) {
    override val coroutineExceptionHandler: CoroutineExceptionHandler
        get() = TODO("Not yet implemented")
}