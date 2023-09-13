package com.poklad.androidifystore.data.mapper

import com.poklad.androidifystore.data.remote.model.ProductItemResponse
import com.poklad.androidifystore.domain.model.ProductItem
import com.poklad.androidifystore.utils.Mapper
import javax.inject.Inject

class ProductResponseToProductItemMapper @Inject constructor() : Mapper<ProductItemResponse, ProductItem> {
    override fun map(data: ProductItemResponse): ProductItem {
        return ProductItem(
            id = data.id,
            title = data.title,
            category = data.category,
            price = data.price,
            image = data.image,
            description = data.description,
        )
    }
}