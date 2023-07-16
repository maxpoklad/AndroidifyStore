package com.poklad.androidifystore.utils

sealed class Resource<T>(
    var data: T? = null,
    var message: String? = null
) {

    //todo возможно можно сделать лучше?
    class Successes<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String?, data: T? = null) : Resource<T>(data, message)
    object Loading : Resource<Nothing>()

}