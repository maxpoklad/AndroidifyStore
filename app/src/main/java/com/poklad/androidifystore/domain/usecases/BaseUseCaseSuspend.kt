package com.poklad.androidifystore.domain.usecases

import com.poklad.androidifystore.utils.Resource
import kotlinx.coroutines.flow.Flow

interface BaseUseCaseSuspend<Parameter, Result> {
    suspend fun invoke(params: Parameter): Flow<Resource<Result>>
}

