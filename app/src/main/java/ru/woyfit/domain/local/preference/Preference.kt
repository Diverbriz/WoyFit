package ru.woyfit.domain.local.preference

interface Preference {

    fun store(key: String, value: String)

    fun restoreString(key: String): String

    fun store(key: String, value: Long)

    fun restoreLong(key: String): Long

    fun store(key: String, value: Int)

    fun restoreInt(key: String): Int

    fun restoreInt(key: String, defaultValue: Int): Int

    fun store(key: String, value: Boolean)

    fun restoreBoolean(key: String): Boolean

    fun restoreBoolean(key: String, value: Boolean): Boolean
}