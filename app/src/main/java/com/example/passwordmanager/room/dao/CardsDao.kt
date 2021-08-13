package com.example.passwordmanager.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.passwordmanager.room.entity.CardsItems
import com.example.passwordmanager.room.entity.LoginsItems
import kotlinx.coroutines.flow.Flow

@Dao()
interface CardsDao {

    @Query("SELECT * FROM cardsItems")
    fun getAllEntries(): Flow<List<CardsItems>>

    @Insert
    fun insert(vararg cardsItems: CardsItems)

    @Delete
    fun delete(cardsItems: LoginsItems)

}