package com.poklad.androidifystore.data.remote

import com.poklad.androidifystore.data.remote.model.ProductCategoryModel
import com.poklad.androidifystore.data.remote.model.ProductItemResponse
import com.poklad.androidifystore.utils.Constants.ALL_PRODUCTS
import retrofit2.http.GET
import retrofit2.http.Path

interface StoreApi {
    @GET(ALL_PRODUCTS)
    suspend fun getAllProducts(): List<ProductItemResponse>

    @GET("products/{productId}")
    suspend fun getProductById(
        @Path("productId")
        productId: Long
    ): ProductItemResponse

    @GET("products/category/{categoryName}")
    suspend fun getProductsByCategories(
        @Path("categoryName")
        categoryName: String
    ): List<ProductItemResponse>

    @GET("products/categories")
    suspend fun getAllCategories(): List<ProductCategoryModel>
}