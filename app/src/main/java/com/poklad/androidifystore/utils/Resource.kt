package com.poklad.androidifystore.utils

sealed class Resource<T> {
    class Success<T>(data: T) : Resource<T>()
    class Error<T>(val throwable: Throwable?) : Resource<T>()
    class Loading<T>() : Resource<T>()
}