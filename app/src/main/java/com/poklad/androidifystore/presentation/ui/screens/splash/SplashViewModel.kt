package com.poklad.androidifystore.presentation.ui.screens.splash

import com.poklad.androidifystore.presentation.ui.base.BaseViewModel
import com.poklad.androidifystore.utils.CoroutineDispatchersProvider
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    dispatchersProvider: CoroutineDispatchersProvider
) : BaseViewModel(dispatchersProvider) {
    override val coroutineExceptionHandler: CoroutineExceptionHandler
        get() = TODO("Not yet implemented")
}
