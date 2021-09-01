package com.example.passwordmanager.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.passwordmanager.data.room.entity.CardsItems
import com.example.passwordmanager.data.room.entity.LoginsItems
import kotlinx.coroutines.flow.Flow

@Dao()
interface CardsDao {

    @Query("SELECT * FROM cardsItems ORDER BY title ASC")
    fun getAllEntries(): Flow<List<CardsItems>>

    @Query("SELECT * FROM cardsItems WHERE isFavorite = 1")
    fun getAllFavoriteEntries(): Flow<List<CardsItems>>

    @Query("SELECT * FROM cardsItems WHERE itemId =:itemId")
    fun getItemById(itemId: Int): Flow<CardsItems>

    @Query("SELECT * FROM cardsItems WHERE title LIKE '%'|| :searchQuery ||'%'")
    fun getSearchedEntries(searchQuery: String): Flow<List<CardsItems>>

    @Insert
    suspend fun insert(vararg cardsItems: CardsItems)

    @Query("DELETE FROM cardsItems WHERE itemId =:itemId")
    suspend fun delete(itemId: Int)

    @Query(
        "UPDATE cardsItems SET " +
                "title =:title," +
                "category =:category," +
                "cardNumber =:cardNumber," +
                "cardHolderName =:cardHolderName," +
                "pinNumber =:pinNumber,"+
                "cvv =:cvv,"+
                "issueDate =:issueDate,"+
                "expiryDate =:expiryDate"+
                " WHERE itemId=:itemId"
    )
    suspend fun update(
        title: String,
        category: Int,
        cardNumber: String,
        cardHolderName: String,
        pinNumber: String,
        cvv: String,
        issueDate: String,
        expiryDate: String,
        itemId: Int
    )

    @Query("UPDATE cardsItems SET isFavorite =:isFavorite WHERE itemId =:itemId")
    suspend fun updateIsFavorite(isFavorite: Boolean, itemId: Int)

    @Query("DELETE from cardsItems")
    suspend fun deleteAllCards()
}