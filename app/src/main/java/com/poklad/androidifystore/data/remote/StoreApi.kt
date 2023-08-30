package com.poklad.androidifystore.data.remote

import com.poklad.androidifystore.data.remote.model.ProductItemResponse
import com.poklad.androidifystore.utils.ApiConstants.ALL_PRODUCTS
import com.poklad.androidifystore.utils.ApiConstants.CATEGORIES
import com.poklad.androidifystore.utils.ApiConstants.CATEGORY
import com.poklad.androidifystore.utils.ApiConstants.CATEGORY_NAME
import com.poklad.androidifystore.utils.ApiConstants.PRODUCT_ID
import retrofit2.http.GET
import retrofit2.http.Path

interface StoreApi {
    @GET(ALL_PRODUCTS)
    suspend fun getAllProducts(): List<ProductItemResponse>

    @GET("$ALL_PRODUCTS/{$PRODUCT_ID}")
    suspend fun getProductById(
        @Path(PRODUCT_ID)
        productId: Long
    ): ProductItemResponse

    @GET("$ALL_PRODUCTS/$CATEGORY/{$CATEGORY_NAME}")
    suspend fun getProductsBySpecificCategory(
        @Path(CATEGORY_NAME)
        categoryName: String
    ): List<ProductItemResponse>

    @GET("$ALL_PRODUCTS/$CATEGORIES")
    suspend fun getAllCategories(): List<String>//todo I'm using strings now, I can't work with ENUM, but i want create more cool app
}