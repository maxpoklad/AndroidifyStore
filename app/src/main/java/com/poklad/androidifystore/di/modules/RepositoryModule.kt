package com.poklad.androidifystore.di.modules

import com.poklad.androidifystore.data.repositories.ProductsRemoteRepositoryImpl
import com.poklad.androidifystore.domain.repositories.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindProductRepository(repositoryImpl: ProductsRemoteRepositoryImpl): ProductsRepository
}