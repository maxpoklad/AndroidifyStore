package com.poklad.androidifystore.domain.usecases

import com.poklad.androidifystore.domain.repositories.CategoriesRepository
import com.poklad.androidifystore.utils.log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(
    private val repository: CategoriesRepository
) : UseCaseSuspend<Unit, List<String>> {
    override suspend fun execute(params: Unit): Flow<List<String>> {
        return flow {
            log("emit1 ${repository.getAllCategories()}")
            emit(repository.getAllCategories())
            log("emit2 ${repository.getAllCategories()}")
        }
    }
}