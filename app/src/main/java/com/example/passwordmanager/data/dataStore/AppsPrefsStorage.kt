package com.example.passwordmanager.data.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.passwordmanager.util.APPS_PREFERENCES
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject


private val Context.datastore by preferencesDataStore(APPS_PREFERENCES)

class AppsPrefsStorage @Inject constructor(
    @ApplicationContext context: Context
): PreferenceStorage{

    private val myDataStore = context.datastore

    override val masterPassword: Flow<String>
        get() = myDataStore.getValue(PreferenceKeys.USER_MASTER_PASSWORD,"")

    override suspend fun setMasterPassword(newPassword: String) {
        myDataStore.setValue(PreferenceKeys.USER_MASTER_PASSWORD,newPassword)
    }

    private fun<T> DataStore<Preferences>.getValue(
        key: Preferences.Key<T>,
        defaultValue: T
    ): Flow<T>{

        return this.data
            .catch {exception->
                if(exception is IOException){
                    emit(emptyPreferences())
                }else{
                    throw exception
                }
            }
            .map {preferences->
                preferences[key] ?: defaultValue
            }

    }

    private suspend fun<T> DataStore<Preferences>.setValue(
        key: Preferences.Key<T>,
        value: T
    ){

        this.edit { preferences->
            preferences[key] = value
        }
    }

    private object PreferenceKeys{
        val USER_MASTER_PASSWORD = stringPreferencesKey("user_master_password")
    }

}