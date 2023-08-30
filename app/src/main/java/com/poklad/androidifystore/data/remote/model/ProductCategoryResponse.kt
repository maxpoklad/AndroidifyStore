package com.poklad.androidifystore.data.remote.model

data class ProductCategoryResponse(
    val categoryTypeName: ProductCategoryTypeName
)

enum class ProductCategoryTypeName {
    ELECTRONICS,
    JEWELRY,
    MENS_CLOTHING,
    WOMENS_CLOTHING,
}