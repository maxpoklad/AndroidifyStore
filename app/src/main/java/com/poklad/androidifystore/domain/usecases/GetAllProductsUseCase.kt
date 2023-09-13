package com.poklad.androidifystore.domain.usecases

import com.poklad.androidifystore.domain.model.ProductItem
import com.poklad.androidifystore.domain.repositories.ProductsRepository
import javax.inject.Inject

typealias GetAllProducts = UseCaseSuspend<Unit, List<ProductItem>>

class GetAllProductsUseCase @Inject constructor(
    private val repository: ProductsRepository,
) : GetAllProducts {
    override suspend fun execute(params: Unit): List<ProductItem> {
        return repository.getAllProducts()
    }
}