package com.poklad.androidifystore.data.repositories

import com.poklad.androidifystore.data.mapper.ProductResponseToProductItemMapper
import com.poklad.androidifystore.data.remote.StoreApi
import com.poklad.androidifystore.di.annotations.ApplicationScope
import com.poklad.androidifystore.domain.model.ProductItem
import com.poklad.androidifystore.domain.repositories.ProductsRepository
import javax.inject.Inject

@ApplicationScope
class ProductsRemoteRepositoryImpl @Inject constructor(
    private val storeApi: StoreApi,
    private val mapper: ProductResponseToProductItemMapper
) : ProductsRepository {
    override suspend fun getAllProducts(): List<ProductItem> {
        return storeApi.getAllProducts().map(mapper::map)
    }

    override suspend fun getProductById(productId: Long): ProductItem {
        return mapper.map(storeApi.getProductById(productId))
    }

    override suspend fun getProductsBySpecificCategory(categoryName: String): List<ProductItem> {
        return storeApi.getProductsBySpecificCategory(categoryName).map(mapper::map)
    }
}