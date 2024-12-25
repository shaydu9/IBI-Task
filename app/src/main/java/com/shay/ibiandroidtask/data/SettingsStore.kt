package com.shay.ibiandroidtask.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user_prefs")

class SettingsStore(private val context: Context) {

    companion object {
        val IS_DARK_KEY = booleanPreferencesKey("is_dark")
    }

    val getIsDark: Flow<Boolean?> = context.dataStore.data.map { preferences ->
        preferences[IS_DARK_KEY] ?: false
    }

    suspend fun setIsDark(isDark: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_DARK_KEY] = isDark
        }
    }
}