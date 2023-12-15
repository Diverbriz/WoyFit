package ru.woyfit.data.network.request

import com.google.gson.annotations.SerializedName

class LoginRequest(
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String
)