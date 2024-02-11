package com.github.com.ebrahimi16153.todo.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.github.com.ebrahimi16153.todo.data.Priority
import com.github.com.ebrahimi16153.todo.util.Constants.PREFERENCES_NAME
import com.github.com.ebrahimi16153.todo.util.Constants.SORT_KEY
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject


// sort by DataStore Preferncece
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

@ViewModelScoped
class SortRepository @Inject constructor(@ApplicationContext private val context: Context) {


    private object PreferencesKey {
        val sortKey = stringPreferencesKey(name = SORT_KEY)
    }

    private val dataStore = context.dataStore


    suspend fun saveSortState(priority: Priority) {

        dataStore.edit { preferences ->
            preferences[PreferencesKey.sortKey] = priority.name

        }


    }

    val readStore: Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {

                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[PreferencesKey.sortKey] ?: Priority.None.name
        }

}