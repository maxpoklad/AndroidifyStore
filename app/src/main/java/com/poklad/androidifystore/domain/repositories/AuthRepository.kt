package com.poklad.androidifystore.domain.repositories

import com.google.firebase.auth.FirebaseUser
import com.poklad.androidifystore.data.firebase.User

interface AuthRepository {
    suspend fun signInWithEmailPassword(user: User): FirebaseUser?

    suspend fun signUpWithEmailPassword(user: User): FirebaseUser?

    fun signOut(): FirebaseUser?

    fun getCurrentUser(): FirebaseUser?

    suspend fun sendResetPassword(email: String): Boolean
}