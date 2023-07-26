package com.poklad.androidifystore.domain.repositories

import com.poklad.androidifystore.domain.model.ProductItem

interface ProductsRepository {
    suspend fun getAllProducts(): List<ProductItem>
    suspend fun getProductsById(productId: Long): ProductItem
    suspend fun getProductsByCategories(categoryName: String): List<ProductItem>
}