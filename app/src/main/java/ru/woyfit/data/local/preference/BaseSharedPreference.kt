package ru.woyfit.data.local.preference

import android.content.Context
import android.content.SharedPreferences
import ru.woyfit.domain.local.preference.Preference

class BaseSharedPreference(private val context: Context):Preference {
    private val mContext: Context
        get() = context.applicationContext

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("dev_prefs", Context.MODE_PRIVATE)
    override fun store(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun restoreString(key: String): String {
        return sharedPreferences.getString(key, "") ?: ""
    }

    override fun store(key: String, value: Long) {
        sharedPreferences.edit().putLong(key, value).apply()
    }

    override fun restoreLong(key: String): Long {
        return sharedPreferences.getLong(key, 0L)
    }

    override fun store(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    override fun restoreInt(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }

    override fun restoreInt(key: String, defaultValue: Int): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    override fun store(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    override fun restoreBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    override fun restoreBoolean(key: String, value: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, value)
    }

}