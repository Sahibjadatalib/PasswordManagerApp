package com.example.passwordmanager.model


import android.media.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.passwordmanager.R
import com.example.passwordmanager.ui.theme.*

data class Category(
    val index: Int,
    val icon: ImageVector,
    val tintColor: Color,
    val title: String
)

val othersCategoryOptions = listOf(
    Category(0,Icons.Filled.Description, LightGreenA400,"Note"),
    Category(1,Icons.Filled.Computer, Blue700Dark,"Computer"),
    Category(2,Icons.Default.Audiotrack, PinkA200,"Music"),
)



val cardsCategoryOptions = listOf(
    Category(0,Icons.Filled.AttachMoney, LightGreenA400,"Credit"),
    Category(1,Icons.Filled.AccountBalance, Blue700Dark,"Debit"),
    Category(2,Icons.Filled.Person, Color.Green,"Identity"),
    Category(3,Icons.Filled.DirectionsCar, AmberA200,"License"),
    Category(4,Icons.Filled.HealthAndSafety, CyanA200,"Health"),
    Category(5,Icons.Filled.Book, DeepOrangeA700,"Library"),
    Category(6,Icons.Filled.FitnessCenter, Color.DarkGray,"Gym"),
    Category(7,Icons.Filled.Train, CyanA200,"Transit"),
    Category(8,Icons.Filled.Favorite, DeepOrangeA700,"Membership"),
    Category(9,Icons.Filled.CardGiftcard, Color.DarkGray,"Gift"),
    Category(10,Icons.Filled.Flag, Color.DarkGray,"Immigration"),

    )

val loginsCategoryOptions = listOf(
    Category(0,Icons.Filled.Person, LightGreenA400,"Personal"),
    Category(1,Icons.Filled.Work, Blue700Dark,"Work"),
    Category(2,Icons.Default.AttachMoney, Color.Green,"Finance"),
    Category(3,Icons.Filled.ShoppingCart, AmberA200,"Shopping"),
    Category(4,Icons.Filled.Flight, CyanA200,"Travel"),
    Category(5,Icons.Filled.Share, DeepOrangeA700,"Social"),
    Category(6,Icons.Filled.MoreHoriz, Color.DarkGray,"Other"),

)

