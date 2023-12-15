package ru.woyfit.core.di

interface CoreAuthPrefs {
    fun storeToken(token: String)

    fun getToken(): String
}