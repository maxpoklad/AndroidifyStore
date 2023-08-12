package com.poklad.androidifystore.domain.usecases
interface UseCase<in Parameter, out Result> {
     fun execute(params: Parameter): Result
}