package ru.woyfit.data.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.woyfit.domain.items.AuthResponseItem

class AuthResponse(@SerializedName("token") @Expose override val token: String): AuthResponseItem