package com.example.passwordmanager.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class OthersItems(

    @PrimaryKey(autoGenerate = true)
    val itemId: Int = 0,
    val isFavorite: Boolean = false,
    val title: String,
    val category: Int,
    val userName: String?,
    val passWord: String?,
    val macAddress: String?,
    val description: String?
)
