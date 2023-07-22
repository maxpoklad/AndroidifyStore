package com.poklad.androidifystore.data.remote

import com.poklad.androidifystore.data.remote.dto.ProductDtoItem
import retrofit2.http.GET
import retrofit2.http.Path

interface StoreApi {
    /* @GET("products")
     suspend fun getAllProducts(): ProductsDto*/
//todo какой способ лучше? нужен ли нам класс всех продуктов?
    //todo и скажи, по поводу Обертки Call and Response, как я понял при ассинхрнном методе єто ну нужно в Ретрофит
    //и я так понял будет нужно уже в RXjava

    @GET("products")
    suspend fun getAllProducts(): List<ProductDtoItem>

    @GET("products/{productId]")
    suspend fun getProductById(
        @Path("productId")
        productId: Int
    ): ProductDtoItem

    @GET("products/category/{categoriesName}")
    suspend fun getProductsByCategories(
        @Path("categoriesName")
        categoriesName: String
    ): List<ProductDtoItem>
}