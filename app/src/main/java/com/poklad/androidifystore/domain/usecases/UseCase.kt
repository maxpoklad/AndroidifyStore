package com.poklad.androidifystore.domain.usecases
//todo добавить LCE Resource в реализацию
interface UseCase<in Parameter, out Result> {
    operator fun invoke(params: Parameter): Result
}

//suspend fun getAllProducts():Flow< Resourse<List<ProductDtoItem>>>
