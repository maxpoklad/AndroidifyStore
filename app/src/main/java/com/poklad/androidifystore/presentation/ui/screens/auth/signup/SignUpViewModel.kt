package com.poklad.androidifystore.presentation.ui.screens.auth.signup

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.poklad.androidifystore.data.firebase.User
import com.poklad.androidifystore.domain.usecases.SignUpUseCase
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

class SignUpViewModel @Inject constructor(
    coroutineDispatchersProvider: CoroutineDispatchersProvider,
    private val signUpUseCase: SignUpUseCase
) : BaseViewModel(coroutineDispatchersProvider) {
    override val coroutineExceptionHandler: CoroutineExceptionHandler
        get() = CoroutineExceptionHandler { _, throwable ->
            log(throwable.message.toString())
        }
    private val _firebaseUser = MutableStateFlow<FirebaseUser?>(null)
    val currentUser = _firebaseUser.asStateFlow()

    private val _eventsFlow = MutableSharedFlow<AllEvents>()
    val eventsFlow = _eventsFlow.asSharedFlow()
    fun signUpUser(user: User, confirmPass: String) = viewModelScope.launch {
        when {
            user.email.isEmpty() -> {
                _eventsFlow.emit(AllEvents.ErrorCode(1))
            }

            user.password.isEmpty() -> {
                _eventsFlow.emit(AllEvents.ErrorCode(2))
            }

            user.password != confirmPass -> {
                _eventsFlow.emit(AllEvents.ErrorCode(3))
            }

            else -> {
                actualSignUnUser(user)
            }
        }
    }

    private fun actualSignUnUser(user: User) {
        viewModelScope.launch {
            try {
                val fUser = signUpUseCase.execute(user)
                fUser?.let {
                    _firebaseUser.value = it
                    _eventsFlow.emit(AllEvents.Message("sign Up success"))
                }
            } catch (e: Exception) {
                val error = e.toString().split(":").toTypedArray()
                _eventsFlow.emit(AllEvents.Error(error[1]))
            }
        }
    }
}