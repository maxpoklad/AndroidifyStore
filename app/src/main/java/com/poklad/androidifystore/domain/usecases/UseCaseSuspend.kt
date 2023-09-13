package com.poklad.androidifystore.domain.usecases

interface UseCaseSuspend<Parameter, Result> {
    suspend fun execute(params: Parameter): Result
}