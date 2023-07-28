package com.poklad.androidifystore.utils

sealed class Resource{
    class Success<T>(data: T) : Resource()
    class Error(val throwable: Throwable?) : Resource()
    object Loading : Resource()
}