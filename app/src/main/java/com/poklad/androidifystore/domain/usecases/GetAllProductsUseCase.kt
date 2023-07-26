package com.poklad.androidifystore.domain.usecases

import com.poklad.androidifystore.domain.model.ProductItem
import com.poklad.androidifystore.domain.repositories.ProductsRepository
import javax.inject.Inject

class GetAllProductsUseCase @Inject constructor(
    private val repository: ProductsRepository
) : UseCaseSuspend<Unit, List<ProductItem>> {

    override suspend fun invoke(params: Unit): List<ProductItem> {
        return repository.getAllProducts()
    }
}