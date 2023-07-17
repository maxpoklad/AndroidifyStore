package com.poklad.androidifystore.domain.usecases

interface BaseUseCaseCoroutines<in Parameter, out Result> {
    suspend fun execute(params: Parameter): Result
}