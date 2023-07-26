package com.poklad.androidifystore.data.remote.model

data class ProductCategoryModel(
    val category: ProductCategory
)
enum class ProductCategory {
    ELECTRONICS,
    JEWELRY,
    MENS_CLOTHING,
    WOMENS_CLOTHING
}
