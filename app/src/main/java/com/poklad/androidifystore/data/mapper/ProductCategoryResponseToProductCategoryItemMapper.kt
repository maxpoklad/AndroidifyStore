package com.poklad.androidifystore.data.mapper

import com.poklad.androidifystore.data.remote.model.ProductCategoryResponse
import com.poklad.androidifystore.data.remote.model.ProductCategoryTypeName
import com.poklad.androidifystore.utils.Mapper

class ProductCategoryResponseToProductCategoryItemMapper :
    Mapper<String, ProductCategoryResponse> {
    override fun map(categoryName: String): ProductCategoryResponse {
        val categoryType = when (categoryName) {
            "electronics" -> ProductCategoryTypeName.ELECTRONICS
            "jewelery" -> ProductCategoryTypeName.JEWELRY
            "men's clothing" -> ProductCategoryTypeName.MENS_CLOTHING
            "women's clothing" -> ProductCategoryTypeName.WOMENS_CLOTHING
            else -> throw IllegalArgumentException("Unknown category: $categoryName")
        }
        return ProductCategoryResponse(
            categoryTypeName = categoryType,
        )
    }
}