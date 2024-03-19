package com.poklad.androidifystore.data.firebase

import com.google.firebase.auth.FirebaseUser

interface Authenticator {
    suspend fun signUpWithEmailPassword(user: User): FirebaseUser?
    suspend fun signInWithEmailPassword(user: User): FirebaseUser?
    fun signOut(): FirebaseUser?

    fun getUser(): FirebaseUser?

    suspend fun sendPasswordReset(email: String)
}