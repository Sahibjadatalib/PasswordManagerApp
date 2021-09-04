package com.example.passwordmanager.data.dataStore

import kotlinx.coroutines.flow.Flow

interface PreferenceStorage {

    val masterPassword: Flow<String>
    suspend fun setMasterPassword(newPassword: String)

    val passwordHint: Flow<String>
    suspend fun setPasswordHint(newHint: String)

    val appTheme: Flow<Boolean>
    suspend fun setAppTheme(newTheme: Boolean)

}