package com.poklad.androidifystore.presentation.mapper

import com.poklad.androidifystore.domain.model.ProductItem
import com.poklad.androidifystore.presentation.model.ProductItemUi
import com.poklad.androidifystore.utils.Mapper
import javax.inject.Inject
class ProductItemToProductItemUi @Inject constructor() : Mapper<ProductItem, ProductItemUi> {
    override fun map(data: ProductItem): ProductItemUi {
        return ProductItemUi(
            id = data.id,
            title = data.title,
            category = data.category,
            price = data.price,
            image = data.image,
            description = data.description,
        )
    }
}