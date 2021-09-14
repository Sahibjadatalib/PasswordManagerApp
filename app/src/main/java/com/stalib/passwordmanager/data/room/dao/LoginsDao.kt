package com.stalib.passwordmanager.data.room.dao

import androidx.room.*
import com.stalib.passwordmanager.data.room.entity.LoginsItems
import kotlinx.coroutines.flow.Flow

@Dao
interface LoginsDao {

    @Query("SELECT * FROM loginsItems ORDER BY title ASC")
    fun getAllEntries(): Flow<List<LoginsItems>>

    @Query("SELECT * FROM loginsItems WHERE isFavorite = 1")
    fun getAllFavoriteEntries(): Flow<List<LoginsItems>>

    @Query("SELECT * FROM loginsItems WHERE itemId =:itemId")
    fun getItemById(itemId: Int): Flow<LoginsItems>

    @Query("SELECT * FROM loginsItems WHERE title LIKE '%'|| :searchQuery ||'%'")
    fun getSearchedEntries(searchQuery: String): Flow<List<LoginsItems>>

    @Insert
    suspend fun insert(vararg loginsItems: LoginsItems)

    @Query("DELETE FROM loginsItems WHERE itemId =:itemId")
    suspend fun delete(itemId: Int)


    @Query(
        "UPDATE loginsItems SET " +
                "title =:title," +
                "category =:category," +
                "userName =:userName," +
                "password =:password" +
                " WHERE itemId=:itemId"
    )
    suspend fun update(
        title: String,
        category: Int,
        userName: String,
        password: String,
        itemId: Int
    )

    @Query("UPDATE loginsItems SET isFavorite =:isFavorite WHERE itemId =:itemId")
    suspend fun updateIsFavorite(isFavorite: Boolean, itemId: Int)

    @Query("DELETE from loginsItems")
    suspend fun deleteAllLogins()

}