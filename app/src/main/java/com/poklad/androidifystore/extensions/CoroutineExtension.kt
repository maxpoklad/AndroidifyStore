package com.poklad.androidifystore.extensions

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope

/**
 *  Runs the given block and returns its result as a Result object.
 *  If the block completes successfully, it returns Result.success with the result.
 *  If the block completes with exception, it is processed as follows:
 *  - if cancellationEx, it is rethrown
 *  - in other cases, Result.failure with the detected exception is returned.
 * @param  block - block of code whose result needs to be processed
 * @return Result<R> - the result of block execution
 * @throws CancellationException - if the block of code was canceled
 */
inline fun <R> CoroutineScope.coRunCatching(block: () -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (cancellationEx: CancellationException) {
        throw cancellationEx
    } catch (ex: Exception) {
        Result.failure(ex)
    }
}