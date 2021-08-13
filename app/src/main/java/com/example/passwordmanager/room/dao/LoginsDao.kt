package com.example.passwordmanager.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.passwordmanager.room.entity.LoginsItems
import kotlinx.coroutines.flow.Flow

@Dao
interface LoginsDao {

    @Query("SELECT * FROM loginsItems")
    fun getAllEntries(): Flow<List<LoginsItems>>

    @Insert
    suspend fun insert(loginsItems: LoginsItems)

    @Delete
    suspend fun delete(loginsItems: LoginsItems)

}