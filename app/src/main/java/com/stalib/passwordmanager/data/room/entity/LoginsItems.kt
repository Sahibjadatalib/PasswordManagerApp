package com.stalib.passwordmanager.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class LoginsItems(

    @PrimaryKey(autoGenerate = true)
    val itemId: Int = 0,
    val isFavorite: Boolean = false,
    val title: String,
    val category: Int,
    val userName: String? = null,
    val passWord: String? = null
){

}
