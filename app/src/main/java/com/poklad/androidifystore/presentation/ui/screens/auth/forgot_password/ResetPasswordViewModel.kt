package com.poklad.androidifystore.presentation.ui.screens.auth.forgot_password

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.poklad.androidifystore.domain.usecases.ResetPasswordUseCase
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
import javax.inject.Inject

class ResetPasswordViewModel @Inject constructor(
    dispatchersProvider: CoroutineDispatchersProvider,
    private val resetPasswordUseCase: ResetPasswordUseCase
) : BaseViewModel(dispatchersProvider) {
    override val coroutineExceptionHandler: CoroutineExceptionHandler
        get() = CoroutineExceptionHandler { _, throwable ->
            log(throwable.message.toString())
        }
    private val _eventsFlow = MutableSharedFlow<AllEvents>()
    val eventsFlow = _eventsFlow.asSharedFlow()
    fun verifySendPasswordReset(email: String) {
        if (email.isEmpty()) {
            viewModelScope.launch {
                _eventsFlow.emit(AllEvents.ErrorCode(1))
            }
        } else {
            sendPasswordResetEmail(email)
        }

    }

    private fun sendPasswordResetEmail(email: String) = viewModelScope.launch {
        try {
            resetPasswordUseCase.execute(email)
            _eventsFlow.emit(AllEvents.Message("reset email sent"))
        } catch (e: Exception) {
            val error = e.toString().split(":").toTypedArray()
            _eventsFlow.emit(AllEvents.Error(error[1]))
        }
    }
}