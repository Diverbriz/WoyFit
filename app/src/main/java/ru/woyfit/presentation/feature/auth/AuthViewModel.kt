package ru.woyfit.presentation.feature.auth

import android.os.Bundle
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import dagger.assisted.Assisted

import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.woyfit.core.base.BaseFlowViewModel
import ru.woyfit.core.coroutines.DispatchersProvider
import ru.woyfit.data.network.WoyfitRestApi
import ru.woyfit.domain.user.AuthUseCase

class AuthViewModel @AssistedInject constructor(
    @Assisted val savedStateHandle: SavedStateHandle,
    private val authUseCase: AuthUseCase,
    dispatchers: DispatchersProvider
) : BaseFlowViewModel(
    dispatchersProvider = dispatchers
){
    private val isMessageShow: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isMessageShowFlow: StateFlow<Boolean>
        get() = isMessageShow
    private val mIsLoginView: MutableStateFlow<Boolean> = MutableStateFlow(true)

    val isLoginView: StateFlow<Boolean>
        get() = mIsLoginView

    val showMessage: MutableStateFlow<String> = MutableStateFlow("")

    val listener = object : AuthListener{
        override fun changeAuthMethod(isLogin: Boolean) {
            changeIsLoginView(isLogin)
        }

        override fun registerUser(username: String, password: String) {
            launch {
                authUseCase.registerUser(username, password)
            }
        }

        override fun loginUser(username: String, password: String) {
            launch {
                authUseCase.loginUser(username, password)

            }
        }

        override fun showMessage(message: String) {
            showMessage.value = message
        }

    }

    fun setText():String{
        val gson = Gson()

        return "gson.toJson(authUseCase.getUser())"
    }

    fun changeIsLoginView(isLogin: Boolean){
        mIsLoginView.value = isLogin
    }

    @AssistedFactory
    interface Factory {
        fun create(savedStateHandle: SavedStateHandle): AuthViewModel
    }
}