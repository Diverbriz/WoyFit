package ru.woyfit.presentation.feature.ui.auth

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import dagger.assisted.Assisted

import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.woyfit.domain.user.AuthUseCase

class AuthViewModel @AssistedInject constructor(
    @Assisted savedStateHandle: SavedStateHandle,
    private val authUseCase: AuthUseCase
) : ViewModel(){
    private val isMessageShow: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isMessageShowFlow: StateFlow<Boolean>
        get() = isMessageShow


    fun setText():String{
        val gson = Gson()
        return gson.toJson(authUseCase.getUser())
    }

    @AssistedFactory
    interface Factory {
        fun create(savedStateHandle: SavedStateHandle): AuthViewModel
    }
}