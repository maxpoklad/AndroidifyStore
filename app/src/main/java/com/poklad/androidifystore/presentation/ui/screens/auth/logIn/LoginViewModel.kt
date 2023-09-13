package com.poklad.androidifystore.presentation.ui.screens.auth.logIn

import com.poklad.androidifystore.presentation.ui.base.BaseViewModel
import com.poklad.androidifystore.utils.CoroutineDispatchersProvider
import kotlinx.coroutines.CoroutineExceptionHandler

class LoginViewModel(
    dispatchersProvider: CoroutineDispatchersProvider
) : BaseViewModel(dispatchersProvider) {
    override val coroutineExceptionHandler: CoroutineExceptionHandler
        get() = TODO("Not yet implemented")
}