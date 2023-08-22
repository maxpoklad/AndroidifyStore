package com.poklad.androidifystore.data.mapper

import com.poklad.androidifystore.data.remote.model.ProductItemModel
import com.poklad.androidifystore.domain.model.ProductItem
import com.poklad.androidifystore.utils.Mapper
import javax.inject.Inject

class ProductDtoToProductMapper @Inject constructor() : Mapper<ProductItemModel, ProductItem> {
    override fun map(data: ProductItemModel): ProductItem {
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