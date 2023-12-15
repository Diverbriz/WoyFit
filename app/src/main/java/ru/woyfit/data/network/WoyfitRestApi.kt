package ru.woyfit.data.network

import retrofit2.http.Body
import retrofit2.http.POST
import ru.woyfit.data.network.request.LoginRequest
import ru.woyfit.data.network.request.SignupRequest
import ru.woyfit.data.network.response.AuthResponse
import ru.woyfit.domain.items.AuthResponseItem

interface WoyfitRestApi {

    @POST("auth/sign-up")
    suspend fun signupUser(@Body signupRequest: SignupRequest):AuthResponse

    @POST("auth/sign-in")
    suspend fun loginUser(@Body loginRequest: LoginRequest):AuthResponse
}