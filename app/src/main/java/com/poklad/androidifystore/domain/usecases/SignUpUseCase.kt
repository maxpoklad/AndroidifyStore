package com.poklad.androidifystore.domain.usecases

import com.google.firebase.auth.FirebaseUser
import com.poklad.androidifystore.data.firebase.User
import com.poklad.androidifystore.domain.repositories.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCaseSuspend<User, FirebaseUser?> {
    override suspend fun execute(params: User): FirebaseUser? {
        return authRepository.signUpWithEmailPassword(user = params)
    }
}