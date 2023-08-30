package com.poklad.androidifystore.data.repositories

import com.poklad.androidifystore.data.remote.StoreApi
import com.poklad.androidifystore.domain.repositories.CategoriesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriesRepositoryImpl @Inject constructor(
    private val storeApi: StoreApi,
) : CategoriesRepository {
    override suspend fun getAllCategories(): List<String> {
        return storeApi.getAllCategories()
    }
}