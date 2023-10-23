package com.poklad.androidifystore.data.repositories

import com.google.firebase.auth.FirebaseUser
import com.poklad.androidifystore.data.firebase.Authenticator
import com.poklad.androidifystore.data.firebase.User
import com.poklad.androidifystore.di.annotations.ApplicationScope
import com.poklad.androidifystore.domain.repositories.AuthRepository
import javax.inject.Inject

@ApplicationScope
class AuthRepositoryImpl @Inject constructor(
    private val authenticator: Authenticator
) : AuthRepository {
    override suspend fun signInWithEmailPassword(user: User): FirebaseUser? {
        return authenticator.signInWithEmailPassword(user)
    }

    override suspend fun signUpWithEmailPassword(user: User): FirebaseUser? {
        return authenticator.signUpWithEmailPassword(user)
    }

    override fun signOut(): FirebaseUser? {
        return authenticator.signOut()
    }

    override fun getCurrentUser(): FirebaseUser? {
        return authenticator.getUser()
    }

    override suspend fun sendResetPassword(email: String): Boolean {
        authenticator.sendPasswordReset(email)
        return true
    }
}