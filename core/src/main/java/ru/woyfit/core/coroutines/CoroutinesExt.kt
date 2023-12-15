package ru.woyfit.core.coroutines

inline fun <T : Any?> wrapResult(block: () -> T): RequestResult<T> {
    return try {
        RequestResult.Success(block())
    } catch (ex: Throwable) {
        RequestResult.Error(ex)
    }
}

suspend fun <R : Any?> RequestResult<R>.mapError(mapper: suspend (Throwable) -> Throwable): RequestResult<R> =
    when (this) {
        is RequestResult.Error -> RequestResult.Error(mapper(error))
        is RequestResult.Success -> this
    }


suspend fun <R : Any?> RequestResult<R>.mapResultOnError(mapper: suspend (Throwable) -> RequestResult<R>): RequestResult<R> =
    when (this) {
        is RequestResult.Error -> mapper(error)
        is RequestResult.Success -> this
    }

suspend fun <T : Any?, R : Any?> RequestResult<T>.map(mapper: suspend (T) -> R): RequestResult<R> =
    when (this) {
        is RequestResult.Error -> RequestResult.Error(error)
        is RequestResult.Success -> RequestResult.Success(mapper(data))
    }

suspend fun <T : Any?, R : Any?> RequestResult<T>.mapResult(mapper: suspend (T) -> RequestResult<R>): RequestResult<R> =
    when (this) {
        is RequestResult.Error -> RequestResult.Error(error)
        is RequestResult.Success -> mapper(data)
    }

fun <T : Any?> RequestResult<T>.resultOrDefault(default: T): T =
    when (this) {
        is RequestResult.Error -> default
        is RequestResult.Success -> data
    }