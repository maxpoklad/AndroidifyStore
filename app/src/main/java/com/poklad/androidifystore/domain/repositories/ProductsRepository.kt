package com.poklad.androidifystore.domain.repositories

import com.poklad.androidifystore.domain.model.ProductItem

interface ProductsRepository {
    suspend fun getAllProducts(): List<ProductItem>
    suspend fun getProductById(productId: Long): ProductItem
    suspend fun getProductsBySpecificCategory(categoryName: String): List<ProductItem>
}