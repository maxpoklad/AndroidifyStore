package com.poklad.androidifystore.domain.usecases

interface UseCaseSuspend<in Parameter, out Result> {
    suspend operator fun invoke(params: Parameter): Result
}