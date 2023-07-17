package com.poklad.androidifystore.domain.usecases

interface BaseUseCase<in Parameter, out Result> {
    fun execute(params: Parameter): Result
}