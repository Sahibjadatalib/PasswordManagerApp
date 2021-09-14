package com.stalib.passwordmanager.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.stalib.passwordmanager.data.room.dao.CardsDao
import com.stalib.passwordmanager.data.room.dao.LoginsDao
import com.stalib.passwordmanager.data.room.dao.OthersDao
import com.stalib.passwordmanager.data.room.entity.CardsItems
import com.stalib.passwordmanager.data.room.entity.LoginsItems
import com.stalib.passwordmanager.data.room.entity.OthersItems
import com.stalib.passwordmanager.common.MyTypeConverter


@Database(
    entities = [LoginsItems::class, CardsItems::class, OthersItems::class],
    version = 7,
    exportSchema = false
)
@TypeConverters(MyTypeConverter::class)
abstract class AppsDatabase : RoomDatabase() {

    abstract fun loginsDao(): LoginsDao
    abstract fun cardsDao(): CardsDao
    abstract fun othersDao(): OthersDao
}