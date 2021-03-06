package com.stalib.passwordmanager.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.stalib.passwordmanager.data.room.dao.CardsDao
import com.stalib.passwordmanager.data.room.dao.LoginsDao
import com.stalib.passwordmanager.data.room.dao.OthersDao
import com.stalib.passwordmanager.data.room.database.AppsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class AppsDatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppsDatabase {
        return Room.databaseBuilder(
            context,
            AppsDatabase::class.java,
            "Password manager database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideLoginsDao(appsDatabase: AppsDatabase): LoginsDao {
        return appsDatabase.loginsDao()
    }

    @Provides
    fun provideCardsDao(appsDatabase: AppsDatabase): CardsDao {
        return appsDatabase.cardsDao()
    }

    @Provides
    fun provideOthersDao(appsDatabase: AppsDatabase): OthersDao {
        return appsDatabase.othersDao()
    }


    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }
}