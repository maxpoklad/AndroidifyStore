package com.poklad.androidifystore.data.remote.model

data class ProductCategoryResponse(
    val categoryName: ProductCategoryName
)
enum class ProductCategoryName {
    ELECTRONICS,
    JEWELRY,
    MENS_CLOTHING,
    WOMENS_CLOTHING
}
