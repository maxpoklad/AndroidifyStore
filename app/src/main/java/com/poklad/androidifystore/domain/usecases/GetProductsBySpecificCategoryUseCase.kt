package com.poklad.androidifystore.domain.usecases

import com.poklad.androidifystore.domain.model.ProductItem
import com.poklad.androidifystore.domain.repositories.ProductsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductsBySpecificCategoryUseCase  @Inject constructor(
    private val repository: ProductsRepository
) : UseCaseSuspend<String, List<ProductItem>> {

    override suspend fun execute(categoryName: String): Flow<List<ProductItem>> {
        return flow {
            emit(repository.getProductsBySpecificCategory(categoryName))
        }
    }
}