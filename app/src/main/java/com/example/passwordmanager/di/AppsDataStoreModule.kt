package com.example.passwordmanager.di

import com.example.passwordmanager.data.dataStore.AppsPrefsStorage
import com.example.passwordmanager.data.dataStore.PreferenceStorage
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppsDataStoreModule {

    @Singleton
    @Binds
    abstract fun providePreferenceStorage(appsPrefsStorage: AppsPrefsStorage): PreferenceStorage

}