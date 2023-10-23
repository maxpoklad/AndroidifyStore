package com.poklad.androidifystore.di.modules

import com.poklad.androidifystore.data.firebase.Authenticator
import com.poklad.androidifystore.data.firebase.FirebaseAuthenticator
import com.poklad.androidifystore.di.annotations.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class FirebaseAuthModule {
    @ApplicationScope
    @Provides
    fun provideFirebaseAuthenticator(): Authenticator {
        return FirebaseAuthenticator()
    }

}