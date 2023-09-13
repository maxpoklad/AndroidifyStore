package com.poklad.androidifystore.utils

object ApiConstants {
    const val BASE_URL = "https://fakestoreapi.com/"
    private const val ALL_PRODUCTS = "products"
    const val PRODUCT_ID = "productId"
    const val CATEGORY_NAME = "categoryName"
    private const val CATEGORY = "category"
    private const val CATEGORIES = "categories"
    const val GET_ALL_PRODUCTS = ALL_PRODUCTS
    const val GET_PRODUCT_BY_ID = "$ALL_PRODUCTS/{$PRODUCT_ID}"
    const val GET_PRODUCTS_BY_SPECIFIC_CATEGORY = "$ALL_PRODUCTS/$CATEGORY/{$CATEGORY_NAME}"
    const val GET_ALL_CATEGORIES = "$ALL_PRODUCTS/$CATEGORIES"
}

object PresentationConstants {
    const val ALL_PRODUCTS = "all Products"
    const val MENS_CLOTHING = "men's clothing"
    const val WOMENS_CLOTHING = "women's clothing"
    const val EMPTY_STRING = ""
}