package com.poklad.androidifystore.presentation.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poklad.androidifystore.extensions.coRunCatching
import com.poklad.androidifystore.utils.CoroutineDispatchersProvider
import com.poklad.androidifystore.utils.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel(
    protected val dispatchers: CoroutineDispatchersProvider
) : ViewModel() {

    abstract val coroutineExceptionHandler: CoroutineExceptionHandler

    private val _loadingFlow = MutableStateFlow(false)
    val loadingFlow = _loadingFlow.asStateFlow()

    private val _errorFlow = MutableSharedFlow<Throwable?>()
    val errorFlow = _errorFlow.asSharedFlow()
    protected fun emitError(throwable: Throwable?) {
        viewModelScope.launch {
            _errorFlow.emit(throwable)
        }
    }

    protected fun showLoader() {
        _loadingFlow.value = true
    }

    protected fun hideLoader() {
        _loadingFlow.value = false
    }

    protected fun launchCoroutineIO(block: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch(dispatchers.getIO() + coroutineExceptionHandler) {
            block()
        }
    }

    protected suspend fun <T> CoroutineScope.executeWithResource(block: suspend () -> T): Resource<T> {
        return coroutineScope {
            coRunCatching {
                block()
            }.fold(
                onSuccess = { Resource.Success(it) },
                onFailure = { Resource.Error(it) }
            )
        }
    }

    protected fun launchMain(
        withLoader: Boolean = false,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return viewModelScope.launch(dispatchers.getMain() + coroutineExceptionHandler) {
            if (withLoader) {
                showLoader()
            }
            block()
            if (withLoader) {
                hideLoader()
            }
        }
    }
}