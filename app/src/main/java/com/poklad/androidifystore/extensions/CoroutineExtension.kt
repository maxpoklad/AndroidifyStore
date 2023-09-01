package com.poklad.androidifystore.extensions

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope

inline fun <R> CoroutineScope.coRunCatching(block: () -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (cancellationEx: CancellationException) {
        throw cancellationEx
    } catch (ex: Exception) {
        Result.failure(ex)
    }
}