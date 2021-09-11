package com.example.passwordmanager.domain.repository

import com.example.passwordmanager.data.room.entity.LoginsItems

interface LoginsRoomRepo {

    suspend fun insertLoginsItem(loginsItems: LoginsItems)
    suspend fun getLoginsItems(): List<LoginsItems>
    suspend fun getLoginsItemById(itemId: Int): LoginsItems
    suspend fun getFavoriteItems(): List<LoginsItems>

}