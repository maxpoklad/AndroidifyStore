package com.poklad.androidifystore.domain.usecases

import com.poklad.androidifystore.domain.model.ProductItem
import com.poklad.androidifystore.domain.repositories.ProductsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

typealias GetAllProductsBaseUseCaseSuspend = UseCaseSuspend<Unit, List<ProductItem>>
class GetAllProductsUseCase @Inject constructor(
    private val repository: ProductsRepository,
) : GetAllProductsBaseUseCaseSuspend {
    override suspend fun execute(params: Unit): Flow<List<ProductItem>> {
        return flow {
            emit(repository.getAllProducts())
        }
    }

}