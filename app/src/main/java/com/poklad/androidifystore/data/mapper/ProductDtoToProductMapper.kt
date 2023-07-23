package com.poklad.androidifystore.data.mapper

import com.poklad.androidifystore.data.remote.dto.ProductDtoItem
import com.poklad.androidifystore.domain.model.ProductItem

class ProductDtoToProductMapper : Mapper<ProductDtoItem, ProductItem> {
    override fun transform(data: ProductDtoItem): ProductItem {
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