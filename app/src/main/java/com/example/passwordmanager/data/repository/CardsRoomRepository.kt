package com.example.passwordmanager.data.repository

import com.example.passwordmanager.data.room.dao.CardsDao
import com.example.passwordmanager.data.room.entity.CardsItems
import com.example.passwordmanager.data.room.entity.LoginsItems
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CardsRoomRepository @Inject constructor(
    private val cardsDao: CardsDao
) {

    suspend fun insertItem(cardsItems: CardsItems) {
        cardsDao.insert(cardsItems)
    }

    fun getAllCardsItems(): Flow<List<CardsItems>> {
        return cardsDao.getAllEntries()
    }

    fun getAllFavoriteEntries(): Flow<List<CardsItems>> {
        return cardsDao.getAllFavoriteEntries()
    }

    fun getItemById(itemId: Int): Flow<CardsItems> {
        return cardsDao.getItemById(itemId = itemId)
    }

    fun getSearchedEntries(searchQuery: String): Flow<List<CardsItems>> {
        return cardsDao.getSearchedEntries(searchQuery = searchQuery)
    }

    suspend fun delete(itemId: Int) {
        cardsDao.delete(itemId = itemId)
    }

    suspend fun updateItem(
        title: String,
        category: Int,
        cardNumber: String,
        cardHolderName: String,
        pinNumber: String,
        cvv: String,
        issueDate: String,
        expiryDate: String,
        itemId: Int
    ) {
        cardsDao.update(
            title = title,
            category = category,
            cardNumber = cardNumber,
            cardHolderName = cardHolderName,
            pinNumber = pinNumber,
            cvv = cvv,
            issueDate = issueDate,
            expiryDate = expiryDate,
            itemId = itemId
        )
    }

    suspend fun updateIsFavorite(
        itemId: Int,
        isFavorite: Boolean
    ) {
        cardsDao.updateIsFavorite(
            itemId = itemId,
            isFavorite = isFavorite
        )
    }

    suspend fun deleteAllCards(){
        cardsDao.deleteAllCards()
    }

}