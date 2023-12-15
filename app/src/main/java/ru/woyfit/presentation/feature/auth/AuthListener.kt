package ru.woyfit.presentation.feature.auth

interface AuthListener {
    fun changeAuthMethod(isLogin: Boolean)

    fun registerUser(username:String, password: String)

    fun loginUser(username:String, password: String)

    fun showMessage(message:String)
}