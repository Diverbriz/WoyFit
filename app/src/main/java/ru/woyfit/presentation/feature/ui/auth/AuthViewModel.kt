package ru.woyfit.presentation.feature.ui.auth

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.woyfit.domain.user.AuthUseCase

class AuthViewModel @AssistedInject constructor(
    @Assisted arguments: Bundle,
    private val authUseCase: AuthUseCase
) : ViewModel(){
    private val isMessageShow: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isMessageShowFlow: StateFlow<Boolean>
        get() = isMessageShow

    init {
        viewModelScope.launch {
            delay(6500)
            isMessageShow.value = false
        }

    }
    fun setText():String{
        val gson = Gson()
        return gson.toJson(authUseCase.getUser())
    }

    @AssistedFactory
    interface Factory {
        fun create(arguments: Bundle?): AuthViewModel
    }
}