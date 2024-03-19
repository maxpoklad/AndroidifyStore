package com.poklad.androidifystore.di.modules

import com.poklad.androidifystore.data.repositories.AuthRepositoryImpl
import com.poklad.androidifystore.data.repositories.CategoriesRepositoryImpl
import com.poklad.androidifystore.data.repositories.ProductsRemoteRepositoryImpl
import com.poklad.androidifystore.domain.repositories.AuthRepository
import com.poklad.androidifystore.domain.repositories.CategoriesRepository
import com.poklad.androidifystore.domain.repositories.ProductsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindProductRepository(repositoryImpl: ProductsRemoteRepositoryImpl): ProductsRepository

    @Binds
    abstract fun bindCategoriesRepository(repositoryImpl: CategoriesRepositoryImpl): CategoriesRepository

    @Binds
    abstract fun bindAuthRepository(repositoryImpl: AuthRepositoryImpl): AuthRepository

}