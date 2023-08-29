package com.poklad.androidifystore.domain.repositories

import com.poklad.androidifystore.data.remote.model.ProductCategoryResponse

interface CategoriesRepository {
    suspend fun getAllCategories(): List<ProductCategoryResponse>
}