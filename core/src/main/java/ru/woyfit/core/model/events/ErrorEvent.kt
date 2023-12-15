package ru.neopharm.core.model.events

data class ErrorEvent(
    val message: String,
    val actionNameStringId: Int?=null,
    val action:(()->Unit)?=null
) : IViewEvent