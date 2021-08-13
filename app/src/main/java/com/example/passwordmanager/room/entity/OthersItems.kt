package com.example.passwordmanager.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.passwordmanager.model.Category


@Entity
data class OthersItems(

    @PrimaryKey(autoGenerate = true)
    val itemId: Long,
    val title: String,
    val category: String,
    val userName: String?,
    val passWord: String?,
    val MACAddress: String?,
    val description: String?,

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
