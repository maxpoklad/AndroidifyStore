package com.poklad.androidifystore.data.remote

import com.poklad.androidifystore.data.remote.model.ProductCategoryTypeNameResponse
import com.poklad.androidifystore.data.remote.model.ProductItemResponse
import com.poklad.androidifystore.utils.ApiConstants
import retrofit2.http.GET
import retrofit2.http.Path

interface StoreApi {
    @GET(ApiConstants.GET_ALL_PRODUCTS)
    suspend fun getAllProducts(): List<ProductItemResponse>

    @GET(ApiConstants.GET_PRODUCT_BY_ID)
    suspend fun getProductById(
        @Path(ApiConstants.PRODUCT_ID)
        productId: Long
    ): ProductItemResponse

    @GET(ApiConstants.GET_PRODUCTS_BY_SPECIFIC_CATEGORY)
    suspend fun getProductsBySpecificCategory(
        @Path(ApiConstants.CATEGORY_NAME)
        categoryName: String
    ): List<ProductItemResponse>

    @GET(ApiConstants.GET_ALL_CATEGORIES)
    suspend fun getAllCategories(): List<ProductCategoryTypeNameResponse>
}