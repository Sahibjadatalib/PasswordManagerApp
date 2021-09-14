package com.stalib.passwordmanager.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.stalib.passwordmanager.data.room.entity.OthersItems
import kotlinx.coroutines.flow.Flow

@Dao
interface OthersDao {

    @Query("SELECT * FROM othersItems ORDER BY title ASC")
    fun getAllEntries(): Flow<List<OthersItems>>

    @Query("SELECT * FROM othersItems WHERE isFavorite = 1")
    fun getAllFavoriteEntries(): Flow<List<OthersItems>>

    @Query("SELECT * FROM othersItems WHERE itemId =:itemId")
    fun getItemById(itemId: Int): Flow<OthersItems>

    @Query("SELECT * FROM othersItems WHERE title LIKE '%'|| :searchQuery ||'%'")
    fun getSearchedEntries(searchQuery: String): Flow<List<OthersItems>>

    @Insert
    suspend fun insert(vararg othersItems: OthersItems)

    @Query("DELETE FROM othersItems WHERE itemId =:itemId")
    suspend fun delete(itemId: Int)

    @Query(
        "UPDATE othersItems SET " +
                "title =:title," +
                "category =:category," +
                "userName =:userName," +
                "password =:password," +
                "macAddress =:macAddress," +
                "description =:description" +
                " WHERE itemId=:itemId"
    )
    suspend fun update(
        title: String,
        category: Int,
        userName: String,
        password: String,
        macAddress: String,
        description: String,
        itemId: Int
    )

    @Query("UPDATE othersItems SET isFavorite =:isFavorite WHERE itemId =:itemId")
    suspend fun updateIsFavorite(isFavorite: Boolean, itemId: Int)

    @Query("DELETE from othersItems")
    suspend fun deleteAllOthers()

}