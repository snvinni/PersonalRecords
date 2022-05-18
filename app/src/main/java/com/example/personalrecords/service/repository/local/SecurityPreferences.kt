package com.example.personalrecords.service.repository.local

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

class SecurityPreferences @Inject constructor(context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences("recordShared", Context.MODE_PRIVATE)

    fun store(key: String, value: String) {
        preferences.edit().putString(key, value).apply()
    }

    fun remove(key: String) {
       return preferences.edit().remove(key).apply()
    }

    fun get(key: String): String {
        return preferences.getString(key, "") ?: ""
    }
    fun setRecordType(isLbs: Boolean) {
         preferences.edit().putBoolean("isLbs", isLbs).apply()
    }
    fun getRecordType(): Boolean {
        return preferences.getBoolean("isLbs", false)
    }

}