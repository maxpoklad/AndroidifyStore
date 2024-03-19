package com.poklad.androidifystore.data.repositories

import com.poklad.androidifystore.data.mapper.ProductCategoryResponseToProductCategoryItemMapper
import com.poklad.androidifystore.data.remote.StoreApi
import com.poklad.androidifystore.di.annotations.ApplicationScope
import com.poklad.androidifystore.domain.repositories.CategoriesRepository
import javax.inject.Inject

typealias CategoryMapper = ProductCategoryResponseToProductCategoryItemMapper

@ApplicationScope
class CategoriesRepositoryImpl @Inject constructor(
    private val storeApi: StoreApi,
    private val mapper: CategoryMapper
) : CategoriesRepository {
    override suspend fun getAllCategories(): List<String> {
        return storeApi.getAllCategories()
            .map { enumValue -> mapper.map(enumValue) }
    }

}