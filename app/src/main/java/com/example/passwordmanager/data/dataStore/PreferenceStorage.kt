package com.example.passwordmanager.data.dataStore

import kotlinx.coroutines.flow.Flow

interface PreferenceStorage {

    val masterPassword: Flow<String>
    suspend fun setMasterPassword(newPassword: String)

}