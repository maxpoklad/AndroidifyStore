package com.poklad.androidifystore.domain.usecases

//todo execute краще?
interface UseCaseSuspend<in Parameter, out Result> {
    suspend operator fun invoke(params: Parameter): Result
}

