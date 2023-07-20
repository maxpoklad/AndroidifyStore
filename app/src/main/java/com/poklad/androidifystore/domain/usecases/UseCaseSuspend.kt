package com.poklad.androidifystore.domain.usecases

interface UseCaseSuspend<in Parameter, out Result> {
    suspend fun execute(params: Parameter): Result
}