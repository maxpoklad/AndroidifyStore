package com.poklad.androidifystore.data.repositories

import com.poklad.androidifystore.data.mapper.Mapper
import com.poklad.androidifystore.data.mapper.ProductDtoToProductMapper
import com.poklad.androidifystore.data.remote.StoreApi
import com.poklad.androidifystore.data.remote.dto.ProductDtoItem
import com.poklad.androidifystore.domain.model.ProductItem
import com.poklad.androidifystore.domain.repositories.ProductsRepository

//todo вот передать маппер єто хорошая идея или лучше создаваь обьект в лямбде? И насколько у нас тут абстракция
// работает. Ведь мі же можем еще здесь с Бд работать, а єто новіе мапперы. ТАк чт может не передать в зависимости.
class ProductsRemoteRepositoryImpl(
    private val storeApi: StoreApi,
    private val mapper: Mapper<ProductDtoItem, ProductItem>// и вот уже получаеться нельзя использовтаь Категории.
) : ProductsRepository {

    // todo верно ли сделал маппер? и вообще сама реализация
    override suspend fun getAllProducts(): List<ProductItem> {
        return storeApi.getAllProducts().map {
            ProductDtoToProductMapper().transform(it)
        }
    }

    override suspend fun getProductsById(productId: Long): ProductItem {
        return mapper.transform(storeApi.getProductById(productId))
    }

    override suspend fun getProductsByCategories(categoryName: String): List<ProductItem> {
        return storeApi.getProductsByCategories(categoryName).map {
            ProductDtoToProductMapper().transform(it)
        }
    }


}