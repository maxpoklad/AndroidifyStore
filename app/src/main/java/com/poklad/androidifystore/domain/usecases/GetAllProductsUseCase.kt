package com.poklad.androidifystore.domain.usecases

import com.poklad.androidifystore.domain.model.ProductItem
import com.poklad.androidifystore.domain.repositories.ProductsRepository
import com.poklad.androidifystore.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

typealias GetAllProductsBaseUseCaseSuspend = UseCaseSuspend<Unit, List<ProductItem>>

class GetAllProductsUseCase @Inject constructor(
    private val repository: ProductsRepository,
) : GetAllProductsBaseUseCaseSuspend {
    override suspend fun execute(params: Unit): Flow<Resource<List<ProductItem>>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repository.getAllProducts()))
        } catch (e: HttpException) {
            emit(
                Resource.Error(e.cause)
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(e.cause)
            )
        }
        emit(Resource.Loading())
    }
}