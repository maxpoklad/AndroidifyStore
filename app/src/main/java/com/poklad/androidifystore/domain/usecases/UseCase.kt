package com.poklad.androidifystore.domain.usecases

interface UseCase<in Parameter, out Result> {
    operator fun invoke(params: Parameter): Result
}