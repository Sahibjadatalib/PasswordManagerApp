package com.example.passwordmanager.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.passwordmanager.model.Category

@Entity()
data class CardsItems(

    @PrimaryKey(autoGenerate = true)
    val itemId: Long,
    val title: String,
    val category: String,
    val cardNumber: Long?,
    val cardHolderName: String?,
    val pinNumber: Long?,
    val cvv: Long?,
    val issueDate: String?,
    val expiryDate: String?,

    val userNameExtra: String?,
    val passWordExtra: String,
    val webSite: String?,
    val email: String?,
    val number: Long?,
    val pin: Long?,
    val dateDDMMYYYY: String?,
    val dateMMYY: String?,
    val text: String?,
    val multiLineText: String?
)
