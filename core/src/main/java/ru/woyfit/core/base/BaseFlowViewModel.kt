package ru.woyfit.core.base

import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.withContext
import ru.woyfit.core.coroutines.DispatchersProvider
import kotlin.coroutines.CoroutineContext

open class BaseFlowViewModel(
    protected val dispatchersProvider: DispatchersProvider
) : ViewModel(),
    CoroutineScope {

    private val parentJob = SupervisorJob()

    override val coroutineContext: CoroutineContext = dispatchersProvider.io() + parentJob

    override fun onCleared() {
        parentJob.cancelChildren()
    }

    @MainThread
    protected open fun onError(error: Throwable) {
        showErrorMessage(error)
    }

    protected suspend fun runOnUi(block: suspend () -> Unit) =
        withContext(dispatchersProvider.main()) {
            block()
        }

    protected suspend fun <T> RequestResult<T>.handleResult(
        block: suspend (T) -> Unit
    ) = runOnUi {
        when (val result = this@handleResult) {
            is RequestResult.Success -> block(result.data)
            is RequestResult.Error -> onError(result.error)
        }
    }

    protected suspend fun <T> RequestResult<T>.handleResultWithError(
        resultBlock: suspend (T) -> Unit,
        errorBlock: suspend (Throwable) -> Unit
    ) = runOnUi {
        when (val result = this@handleResultWithError) {
            is RequestResult.Success -> resultBlock(result.data)
            is RequestResult.Error -> errorBlock(result.error)
        }
    }

    @MainThread
    protected open fun showErrorMessage(error: Throwable) {
    }
}