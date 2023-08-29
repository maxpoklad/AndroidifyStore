package com.poklad.androidifystore.domain.usecases

import com.poklad.androidifystore.data.remote.model.ProductCategoryResponse
import com.poklad.androidifystore.domain.repositories.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAllCategoriesUseCase(
    private val repository: CategoriesRepository
) : UseCaseSuspend<Unit, List<ProductCategoryResponse>> {

    override suspend fun execute(params: Unit): Flow<List<ProductCategoryResponse>> {
        return flow {
            emit(repository.getAllCategories())
        }
    }
}