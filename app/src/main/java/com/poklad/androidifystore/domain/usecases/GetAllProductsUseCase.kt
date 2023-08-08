package com.poklad.androidifystore.domain.usecases

import com.poklad.androidifystore.domain.model.ProductItem
import com.poklad.androidifystore.domain.repositories.ProductsRepository
import com.poklad.androidifystore.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

typealias GetAllProductsBaseUseCaseSuspend = BaseUseCaseSuspend<Unit, List<ProductItem>>

class GetAllProductsUseCase @Inject constructor(
    private val repository: ProductsRepository,
) : GetAllProductsBaseUseCaseSuspend {
    override suspend fun invoke(params: Unit): Flow<Resource<List<ProductItem>>> = flow {
        emit(Resource.Loading(true))
        try {
            emit(Resource.Success(repository.getAllProducts()))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong!"
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection."
                )
            )
        }
        emit(Resource.Loading(false))
    }
}