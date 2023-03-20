package com.example.nativebaseproject.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.nativebaseproject.MyApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.IOException

/**
 * Created by ThangNNT on 21/03/2023.
 */

object AppDataStore {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_data")
    private val dataStore = MyApplication.getInstance().applicationContext.dataStore

    private val FIRST_APP_OPEN = booleanPreferencesKey("FIRST_APP_OPEN")

    var isFirstAppOpen: Boolean
    get() =  runBlocking {
        withContext(Dispatchers.Default){
            dataStore.getValueFlow(FIRST_APP_OPEN, false).first()
        }
    }
    set(value) = runBlocking {
        withContext(Dispatchers.Default){
            dataStore.edit {
                it[FIRST_APP_OPEN] = value
            }
        }
    }

    fun <T> DataStore<Preferences>.getValueFlow(
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