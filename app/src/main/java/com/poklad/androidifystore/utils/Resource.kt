package com.poklad.androidifystore.utils

sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()
    class Error<T>(val throwable: Throwable?) : Resource<T>()
    class Loading<T> : Resource<T>()
}