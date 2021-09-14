package com.stalib.passwordmanager.di

import com.stalib.passwordmanager.data.dataStore.AppsPrefsStorage
import com.stalib.passwordmanager.data.dataStore.PreferenceStorage
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