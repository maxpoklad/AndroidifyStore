package com.poklad.androidifystore.data.remote

import com.poklad.androidifystore.data.remote.dto.ProductCategoryDto
import com.poklad.androidifystore.data.remote.dto.ProductDtoItem
import retrofit2.http.GET
import retrofit2.http.Path

interface StoreApi {
    @GET("products")
    suspend fun getAllProducts(): List<ProductDtoItem>

    @GET("products/{productId}")
    suspend fun getProductById(
        @Path("productId")
        productId: Long
    ): ProductDtoItem

    @GET("products/category/{categoryName}")
    suspend fun getProductsByCategories(
        @Path("categoryName")
        categoryName: String
    ): List<ProductDtoItem>

    @GET("products/categories")
    suspend fun getAllCategories(): List<ProductCategoryDto>
}