package com.example.passwordmanager.room.entity

import android.graphics.Bitmap
import androidx.annotation.NonNull
import androidx.compose.ui.graphics.ImageBitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.passwordmanager.model.Category
import java.util.*

@Entity()
data class LoginsItems(

    @PrimaryKey(autoGenerate = true)
    @NonNull
    val itemId: Long? = null,
    val itemIcon: String? = null,
    val title: String,
    val category: String,
    val userName: String? = null,
    val passWord: String? = null,

    val webSite: String? = null,
    val email: String? = null,
    val number: Long? = null,
    val pin: Long? = null,
    val dateDDMMYYYY: String? = null,
    val dateMMYY: String? = null,
    val text: String? = null,
    val multiLineText: String? = null

)
