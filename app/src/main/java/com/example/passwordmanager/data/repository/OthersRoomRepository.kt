package com.example.passwordmanager.data.repository

import com.example.passwordmanager.data.room.dao.CardsDao
import com.example.passwordmanager.data.room.dao.OthersDao
import com.example.passwordmanager.data.room.entity.CardsItems
import com.example.passwordmanager.data.room.entity.LoginsItems
import com.example.passwordmanager.data.room.entity.OthersItems
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OthersRoomRepository @Inject constructor(
    private val othersDao: OthersDao
) {

    suspend fun insertItem(othersItems: OthersItems) {
        othersDao.insert(othersItems)
    }

    fun getAllOthersItems(): Flow<List<OthersItems>> {
        return othersDao.getAllEntries()
    }

    fun getAllFavoriteEntries(): Flow<List<OthersItems>> {
        return othersDao.getAllFavoriteEntries()
    }

    fun getItemById(itemId: Int): Flow<OthersItems> {
        return othersDao.getItemById(itemId = itemId)
    }

    fun getSearchedEntries(searchQuery: String): Flow<List<OthersItems>> {
        return othersDao.getSearchedEntries(searchQuery = searchQuery)
    }

    suspend fun delete(itemId: Int) {
        othersDao.delete(itemId = itemId)
    }

    suspend fun updateItem(
        title: String,
        category: Int,
        userName: String,
        password: String,
        macAddress: String,
        description: String,
        itemId: Int
    ) {
        othersDao.update(
            title = title,
            category = category,
            userName = userName,
            password = password,
            macAddress = macAddress,
            description = description,
            itemId = itemId
        )
    }

    suspend fun updateIsFavorite(
        itemId: Int,
        isFavorite: Boolean
    ) {
        othersDao.updateIsFavorite(
            itemId = itemId,
            isFavorite = isFavorite
        )
    }

}