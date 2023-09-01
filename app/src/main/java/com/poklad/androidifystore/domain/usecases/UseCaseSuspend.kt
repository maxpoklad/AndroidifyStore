package com.poklad.androidifystore.domain.usecases

import kotlinx.coroutines.flow.Flow


interface UseCaseSuspend<Parameter, Result> {
    suspend fun execute(params: Parameter): Flow<Result>
}

interface UseCaseSuspend2<Parameter, Result> {
    suspend fun execute(params: Parameter): Result
}

