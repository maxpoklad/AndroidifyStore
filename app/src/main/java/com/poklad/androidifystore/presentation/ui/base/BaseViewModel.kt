package com.poklad.androidifystore.presentation.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poklad.androidifystore.utils.CoroutineDispatchersProvider
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class BaseViewModel(
    val dispatchers: CoroutineDispatchersProvider
) : ViewModel() {

    private val job = Job()
    abstract val coroutineExceptionHandler: CoroutineExceptionHandler

    protected fun launchCoroutineIO(block: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch(dispatchers.getIO() + job + coroutineExceptionHandler) {
            block()
        }
    }

    protected fun launchCoroutineMain(block: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch(dispatchers.getMain() + job + coroutineExceptionHandler) {
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}