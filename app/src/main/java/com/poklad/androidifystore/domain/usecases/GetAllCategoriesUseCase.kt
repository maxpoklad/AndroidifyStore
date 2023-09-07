package com.poklad.androidifystore.domain.usecases

import com.poklad.androidifystore.domain.repositories.CategoriesRepository
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(
    private val repository: CategoriesRepository
) : UseCaseSuspend<Unit, List<String>> {
    override suspend fun execute(params: Unit): List<String> {
        return repository.getAllCategories().toMutableList().apply {
            add(0, ALL_PRODUCTS)
        }
    }

    companion object {
        const val ALL_PRODUCTS = "all products"
    }
}