package com.example.nativebaseproject.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.nativebaseproject.MyApplication
import com.example.nativebaseproject.common.util.getDefaultLanguage
import com.example.nativebaseproject.ui.theme.DarkModeSetting
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import java.io.IOException

/**
 * Created by ThangNNT on 21/03/2023.
 */

object AppDataStore {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_data")
    private val dataStore = MyApplication.getInstance().applicationContext.dataStore

    private val FIRST_APP_OPEN = booleanPreferencesKey("FIRST_APP_OPEN")
    private val CURRENT_LANGUAGE = stringPreferencesKey("CURRENT_LANGUAGE")
    private val DARK_MODE = intPreferencesKey("DARK_MODE")

    var isFirstAppOpen: Boolean
        get() = get(FIRST_APP_OPEN, true)
        set(value) {
            put(FIRST_APP_OPEN, value)
        }

    /**
     * current language code. @sample en,vi,fr
     */
    var currentLanguage: String
        get() = get(CURRENT_LANGUAGE, getDefaultLanguage().languageCode)
        set(value) {
            put(CURRENT_LANGUAGE, value)
        }

    var darkModeSetting: DarkModeSetting
    get() {
        val darkMode = get(DARK_MODE, DarkModeSetting.SystemSetting.value)
        DarkModeSetting.values().forEach {
            if (it.value == darkMode)
                return it
        }
        return DarkModeSetting.SystemSetting
    }
    set(value) {
        put(DARK_MODE, value.value)
    }

    private inline fun <reified T>get(key: Preferences.Key<T>, default: T): T = runBlocking(Dispatchers.Default) {
        return@runBlocking dataStore.getValueFlow(key, default).first()
    }

    private inline fun <reified T>put(key: Preferences.Key<T>, value: T) = runBlocking(Dispatchers.Default){
        dataStore.edit {
            it[key] = value
        }
    }

    private inline fun <reified T>getObject(key: Preferences.Key<String>): T = runBlocking(Dispatchers.Default) {
        val jsonString = dataStore.getValueFlow(key, "").first()
        return@runBlocking Gson().fromJson(jsonString, T::class.java)
    }

    private inline fun <reified T>putObject(key: Preferences.Key<String>, value: T) = runBlocking(Dispatchers.Default){
        val jsonString = Gson().toJson(value)
        dataStore.edit {
            it[key] = jsonString
        }
    }

    private fun <T> DataStore<Preferences>.getValueFlow(
        key: Preferences.Key<T>,
        defaultValue: T,
    ): Flow<T> {
        return this.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[key] ?: defaultValue
            }
    }

}