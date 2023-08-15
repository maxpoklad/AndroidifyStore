package com.poklad.androidifystore.presentation.ui.screens.home

import com.poklad.androidifystore.presentation.ui.base.BaseViewModel
import com.poklad.androidifystore.utils.CoroutineDispatchersProvider
import kotlinx.coroutines.CoroutineExceptionHandler

class HomeViewModel(
    coroutineDispatchersProvider: CoroutineDispatchersProvider,
) : BaseViewModel(
    coroutineDispatchersProvider
) {
    override val coroutineExceptionHandler: CoroutineExceptionHandler
        get() = TODO("Not yet implemented")
}