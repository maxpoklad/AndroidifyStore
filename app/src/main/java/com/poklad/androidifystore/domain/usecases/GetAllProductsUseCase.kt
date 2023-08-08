package com.poklad.androidifystore.domain.usecases

import com.poklad.androidifystore.domain.model.ProductItem
import com.poklad.androidifystore.domain.repositories.ProductsRepository
import com.poklad.androidifystore.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

typealias GetAllProductsBaseUseCase = BaseUseCaseSuspend<Unit, List<ProductItem>>

class GetAllProductsUseCase @Inject constructor(
    private val repository: ProductsRepository
) : GetAllProductsBaseUseCase {
    override suspend fun invoke(params: Unit): Flow<Resource<List<ProductItem>>> = flow {
        emit(Resource.Loading())
        try {
            val products = repository.getAllProducts()
            emit(Resource.Success(products))
        } catch (e: Throwable) {
            emit(Resource.Error(e.toString()))
        }
    }

}