package com.poklad.androidifystore.utils

import android.util.Log
import android.view.View

fun Any.tag(): String {
    return this::class.simpleName!!
}

fun Any.log(msg: String) {
    Log.d("TAG: ${tag()}", msg)
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}