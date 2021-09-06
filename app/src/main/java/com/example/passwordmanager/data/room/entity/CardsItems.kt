package com.example.passwordmanager.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class CardsItems(

    @PrimaryKey(autoGenerate = true)
    val itemId: Int = 0,
    val isFavorite: Boolean = false,
    val title: String,
    val category: Int,
    val cardNumber: String?,
    val cardHolderName: String?,
    val pinNumber: String?,
    val cvv: String,
    val issueDate: String?,
    val expiryDate: String?
)
