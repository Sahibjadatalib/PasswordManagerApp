package com.example.passwordmanager.repository

import com.example.passwordmanager.room.dao.CardsDao
import com.example.passwordmanager.room.dao.LoginsDao
import com.example.passwordmanager.room.dao.OthersDao
import com.example.passwordmanager.room.entity.LoginsItems
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomsRepository @Inject constructor(
    private val loginsDao: LoginsDao,
    private val cardsDao: CardsDao,
    private val othersDao: OthersDao
) {

    suspend fun insertLoginsItem(loginsItems: LoginsItems){
        loginsDao.insert(loginsItems)
    }

    val getAllLoginsItems: Flow<List<LoginsItems>> get() = loginsDao.getAllEntries()

    suspend fun deleteLoginsItem(loginsItems: LoginsItems){
        loginsDao.delete(loginsItems)
    }


}