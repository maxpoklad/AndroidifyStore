package com.poklad.androidifystore.domain.usecases

import com.poklad.androidifystore.utils.Resource
import kotlinx.coroutines.flow.Flow

interface UseCaseSuspend<Parameter, Result> {
    suspend fun execute(params: Parameter): Flow<Resource<Result>>
}

