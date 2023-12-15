package ru.woyfit.data.network.request

import com.google.gson.annotations.SerializedName

class SignupRequest (
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String
)