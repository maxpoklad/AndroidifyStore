package com.poklad.androidifystore.utils

import android.util.Log
import android.view.View

fun Any.tag(): String {
    // TODO it will not work with OBFUSCATION. Check your logs with minify enabled.
    //   Read: build types !!!
    return this::class.simpleName!!
}

fun Any.log(msg: String) {
    Log.d("TAG: ${tag()}", msg)
}

// TODO see KOTLIN ANDROID EXTENSION. There are the same extensions
fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}