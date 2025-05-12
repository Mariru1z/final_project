package com.example.simpleagenda.utils

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.example.simpleagenda.preferences.PreferenceManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

object ThemeHelper {

    fun applyThemeFromPreferences(context: Context) {
        CoroutineScope(Dispatchers.Main).launch {
            val preferenceManager = PreferenceManager(context)
            val theme = preferenceManager.getTheme().first()
            applyTheme(theme)
        }
    }

    fun applyTheme(theme: String?) {
        when (theme) {
            "dark" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            "light" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }

    fun saveThemePreference(context: Context, theme: String) {
        CoroutineScope(Dispatchers.IO).launch {
            PreferenceManager(context).saveTheme(theme)
        }
    }
}
