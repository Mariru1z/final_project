package com.example.agenda_proyecto.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferenceManager(private val context: Context) {

    companion object {
        private val Context.dataStore by preferencesDataStore(name = "user_prefs")
        val EMAIL_KEY = stringPreferencesKey("email")
        val THEME_KEY = stringPreferencesKey("theme")
    }

    fun getEmail(): Flow<String?> = context.dataStore.data
        .map { it[EMAIL_KEY] }

    suspend fun saveEmail(email: String) {
        context.dataStore.edit { prefs ->
            prefs[EMAIL_KEY] = email
        }
    }

    fun getTheme(): Flow<String?> = context.dataStore.data
        .map { it[THEME_KEY] }

    suspend fun saveTheme(theme: String) {
        context.dataStore.edit { prefs ->
            prefs[THEME_KEY] = theme
        }
    }
}
