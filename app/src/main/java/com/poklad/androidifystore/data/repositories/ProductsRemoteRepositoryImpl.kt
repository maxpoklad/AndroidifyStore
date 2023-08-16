package com.poklad.androidifystore.data.repositories

import com.poklad.androidifystore.data.mapper.ProductDtoToProductMapper
import com.poklad.androidifystore.data.remote.StoreApi
import com.poklad.androidifystore.domain.model.ProductItem
import com.poklad.androidifystore.domain.repositories.ProductsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductsRemoteRepositoryImpl @Inject constructor(
    private val storeApi: StoreApi,
    private val mapper: ProductDtoToProductMapper
) : ProductsRepository {
    override suspend fun getAllProducts(): List<ProductItem> {
        return storeApi.getAllProducts().map(mapper::map)
    }

    override suspend fun getProductsById(productId: Long): ProductItem {
        return mapper.map(storeApi.getProductById(productId))
    }

    override suspend fun getProductsByCategories(categoryName: String): List<ProductItem> {
        return storeApi.getProductsByCategories(categoryName).map(mapper::map)
    }
}