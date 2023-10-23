package com.poklad.androidifystore.presentation.ui.screens.auth.logIn

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.poklad.androidifystore.data.firebase.User
import com.poklad.androidifystore.domain.usecases.LogInUseCase
import com.poklad.androidifystore.extensions.log
import com.poklad.androidifystore.presentation.ui.base.BaseViewModel
import com.poklad.androidifystore.presentation.ui.screens.auth.utils.AllEvents
import com.poklad.androidifystore.utils.CoroutineDispatchersProvider
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    dispatchersProvider: CoroutineDispatchersProvider,
    private val logInUseCase: LogInUseCase
) : BaseViewModel(dispatchersProvider) {
    override val coroutineExceptionHandler: CoroutineExceptionHandler
        get() = CoroutineExceptionHandler { _, throwable ->
            log(throwable.message.toString())
        }
    private val _firebaseUser = MutableStateFlow<FirebaseUser?>(null)
    val firebaseUser = _firebaseUser.asStateFlow()

    private val _eventsFlow = MutableSharedFlow<AllEvents>()
    val eventsFlow = _eventsFlow.asSharedFlow()
    fun logInUserValidate(user: User) {
        viewModelScope.launch {
            when {
                user.email.isEmpty() -> {
                    _eventsFlow.emit(AllEvents.ErrorCode(1))
                }

                user.password.isEmpty() -> {
                    _eventsFlow.emit(AllEvents.ErrorCode(2))
                }

                else -> {
                    actualSignInUser(user)
                }
            }
        }
    }

    private fun actualSignInUser(user: User) = viewModelScope.launch {
        try {
            val fUser = logInUseCase.execute(user)
            fUser?.let {
                _firebaseUser.value = it
                _eventsFlow.emit(AllEvents.Message("login success"))
            }
        } catch (e: Exception) {
            val error = e.toString().split(":").toTypedArray()
            _eventsFlow.emit(AllEvents.Error(error[1]))
        }
    }
}