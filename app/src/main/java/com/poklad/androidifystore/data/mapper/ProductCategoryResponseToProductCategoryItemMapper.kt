package com.poklad.androidifystore.data.mapper

import com.poklad.androidifystore.data.remote.model.ProductCategoryTypeNameResponse
import com.poklad.androidifystore.utils.Mapper
import javax.inject.Inject

class ProductCategoryResponseToProductCategoryItemMapper @Inject constructor():
    Mapper<ProductCategoryTypeNameResponse, String> {
    override fun map(data: ProductCategoryTypeNameResponse): String {
        return data.rawValue
    }

}