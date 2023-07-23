package com.poklad.androidifystore.data.mapper

import com.poklad.androidifystore.data.remote.dto.ProductCategoryDto
import com.poklad.androidifystore.domain.model.ProductCategory
import java.lang.IllegalArgumentException

class ProductCategoryMapper : Mapper<ProductCategoryDto, ProductCategory> {
    override fun transform(data: ProductCategoryDto): ProductCategory {
        return when (data.category) {
            "electronics" -> ProductCategory.ELECTRONICS
            "jewelery" -> ProductCategory.JEWELRY
            "men's clothing" -> ProductCategory.MENS_CLOTHING
            "women's clothing" -> ProductCategory.WOMENS_CLOTHING
            else -> throw IllegalArgumentException("Unknown category")//todo хотя их всего 5 может свою ошибку?
        }
    }
}