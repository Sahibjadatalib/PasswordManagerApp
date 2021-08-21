package com.example.passwordmanager.data.repository

import com.example.passwordmanager.data.room.dao.CardsDao
import com.example.passwordmanager.data.room.dao.LoginsDao
import com.example.passwordmanager.data.room.dao.OthersDao
import com.example.passwordmanager.data.room.entity.LoginsItems
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomsRepository @Inject constructor(
    private val loginsDao: LoginsDao,
    private val cardsDao: CardsDao,
    private val othersDao: OthersDao
) {

    suspend fun insertLoginsItem(loginsItems: LoginsItems) {
        loginsDao.insert(loginsItems)
    }

//    val getAllLoginsItems: Flow<List<LoginsItems>> get() = loginsDao.getAllEntries()

    fun getAllLoginsItems(): Flow<List<LoginsItems>> {
        return loginsDao.getAllEntries()
    }

    fun getAllFavoriteEntries(): Flow<List<LoginsItems>> {
        return loginsDao.getAllFavoriteEntries()
    }

    fun getItemById(itemId: Int): Flow<LoginsItems> {
        return loginsDao.getItemById(itemId = itemId)
    }

    suspend fun deleteLoginsItem(itemId: Int) {
        loginsDao.delete(itemId = itemId)
    }

    suspend fun updateLoginsItem(
        title: String,
        category: Int,
        userName: String,
        password: String,
        itemId: Int
    ) {
        loginsDao.update(
            title = title,
            category = category,
            userName = userName,
            password = password,
            itemId = itemId
        )
    }

    suspend fun updateIsFavorite(
        itemId: Int,
        isFavorite: Boolean
    ) {
        loginsDao.updateIsFavorite(
            itemId = itemId,
            isFavorite = isFavorite
        )
    }


}