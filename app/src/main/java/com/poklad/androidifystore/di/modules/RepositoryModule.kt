package com.poklad.androidifystore.di.modules

import com.poklad.androidifystore.data.repositories.CategoriesRepositoryImpl
import com.poklad.androidifystore.data.repositories.ProductsRemoteRepositoryImpl
import com.poklad.androidifystore.domain.repositories.CategoriesRepository
import com.poklad.androidifystore.domain.repositories.ProductsRepository
import com.poklad.androidifystore.domain.usecases.GetAllProductsUseCase
import com.poklad.androidifystore.domain.usecases.UseCase
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindProductRepository(repositoryImpl: ProductsRemoteRepositoryImpl): ProductsRepository

    @Binds
    abstract fun bindCategoriesRepository(repositoryImpl: CategoriesRepositoryImpl): CategoriesRepository

}