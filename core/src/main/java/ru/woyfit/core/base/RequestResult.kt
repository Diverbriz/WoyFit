package ru.woyfit.core.base

sealed class RequestResult<out T : Any?> {
    data class Success<T : Any?>(val data: T) : RequestResult<T>()

    data class Error(val error: Throwable) : RequestResult<Nothing>()
}