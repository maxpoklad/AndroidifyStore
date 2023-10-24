package com.poklad.androidifystore.domain.usecases

import com.poklad.androidifystore.domain.repositories.AuthRepository
import javax.inject.Inject

class ResetPasswordUseCase @Inject constructor(
    private val repository: AuthRepository
) : UseCaseSuspend<String, Unit> {
    override suspend fun execute(params: String) {
        repository.sendResetPassword(params)
    }
}