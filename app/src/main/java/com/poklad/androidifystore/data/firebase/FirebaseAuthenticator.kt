package com.poklad.androidifystore.data.firebase

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FirebaseAuthenticator : Authenticator {
    override suspend fun signUpWithEmailPassword(user: User): FirebaseUser? {
        Firebase.auth.createUserWithEmailAndPassword(user.email, user.password).await()
        return Firebase.auth.currentUser
    }

    override suspend fun signInWithEmailPassword(user: User): FirebaseUser? {
        Firebase.auth.signInWithEmailAndPassword(user.email, user.password).await()
        return Firebase.auth.currentUser
    }

    override fun signOut(): FirebaseUser? {
        Firebase.auth.signOut()
        return Firebase.auth.currentUser
    }

    override fun getUser(): FirebaseUser? {
        return Firebase.auth.currentUser
    }

    override suspend fun sendPasswordReset(email: String) {
        Firebase.auth.sendPasswordResetEmail(email).await()
    }
}