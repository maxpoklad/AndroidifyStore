package com.poklad.androidifystore.utils

import android.util.Log

fun Any.tag(): String {
    return this::class.simpleName!!
}

fun Any.log(msg: String) {
    Log.d("TAG: ${tag()}", msg)
}