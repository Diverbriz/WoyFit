package ru.woyfit.presentation.feature.main

import androidx.lifecycle.SavedStateHandle
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

class MainViewModel @AssistedInject constructor(
    @Assisted savedStateHandle: SavedStateHandle,
    private val authUseCase: AuthUseCase) : ViewModel(){
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
        return "gson.toJson(authUseCase.getUser())"
    }

    @AssistedFactory
    interface Factory {
        fun create(savedStateHandle: SavedStateHandle): MainViewModel
    }
}