package com.poklad.androidifystore.domain.usecases

import com.poklad.androidifystore.domain.model.ProductItem
import com.poklad.androidifystore.domain.repositories.ProductsRepository
import javax.inject.Inject

class GetProductsBySpecificCategoryUseCase  @Inject constructor(
    private val repository: ProductsRepository
) : UseCaseSuspend2<String, List<ProductItem>> {

    override suspend fun execute(categoryName: String): List<ProductItem> {
        return repository.getProductsBySpecificCategory(categoryName)
    }
}