package com.example.passwordmanager.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.passwordmanager.room.dao.CardsDao
import com.example.passwordmanager.room.dao.LoginsDao
import com.example.passwordmanager.room.dao.OthersDao
import com.example.passwordmanager.room.entity.CardsItems
import com.example.passwordmanager.room.entity.LoginsItems
import com.example.passwordmanager.room.entity.OthersItems
import com.example.passwordmanager.util.MyTypeConverter


@Database(entities = [LoginsItems::class,CardsItems::class,OthersItems::class],version = 3,exportSchema = false)
@TypeConverters(MyTypeConverter::class)
abstract class AppsDatabase: RoomDatabase(){

    abstract fun loginsDao(): LoginsDao
    abstract fun cardsDao(): CardsDao
    abstract fun othersDao(): OthersDao
}