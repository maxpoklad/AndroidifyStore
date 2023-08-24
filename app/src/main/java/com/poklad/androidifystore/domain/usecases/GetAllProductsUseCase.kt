package com.poklad.androidifystore.domain.usecases

import com.poklad.androidifystore.domain.model.ProductItem
import com.poklad.androidifystore.domain.repositories.ProductsRepository
import com.poklad.androidifystore.utils.Resource
import com.poklad.androidifystore.utils.log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

typealias GetAllProductsBaseUseCaseSuspend = UseCaseSuspend<Unit, List<ProductItem>>

// TODO why is it singleton?
class GetAllProductsUseCase @Inject constructor(
    private val repository: ProductsRepository,
) : GetAllProductsBaseUseCaseSuspend {
    override suspend fun execute(params: Unit): Flow<List<ProductItem>> {
        return flow {
            log("execute${repository.getAllProducts()}")
            emit(repository.getAllProducts())
            log("after emit${repository.getAllProducts()}")
        }
    }
}