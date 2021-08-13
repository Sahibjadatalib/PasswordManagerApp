package com.example.passwordmanager.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.passwordmanager.room.entity.LoginsItems
import com.example.passwordmanager.room.entity.OthersItems
import kotlinx.coroutines.flow.Flow

@Dao
interface OthersDao {

    @Query("SELECT * FROM othersItems")
    fun getAllEntries(): Flow<List<OthersItems>>

    @Insert
    fun insert(vararg othersItems: OthersItems)

    @Delete
    fun delete(othersItems: OthersItems)

}