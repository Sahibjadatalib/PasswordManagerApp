package com.stalib.passwordmanager.domain.models


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.stalib.passwordmanager.presentation.theme.*

data class Category(
    val index: Int,
    val icon: ImageVector,
    val tintColor: Color,
    val title: String
)

val othersCategoryOptions = listOf(
    Category(0,Icons.Filled.Description, GreenA700,"Note"),
    Category(1,Icons.Filled.Computer, Blue700Dark,"Computer"),
    Category(2,Icons.Default.Audiotrack, PinkA200,"Music"),
)



val cardsCategoryOptions = listOf(
    Category(0,Icons.Filled.AttachMoney, GreenA700,"Credit"),
    Category(1,Icons.Filled.AccountBalance, BlueLight900,"Debit"),
    Category(2,Icons.Filled.Person, GreenA700,"Identity"),
    Category(3,Icons.Filled.DirectionsCar, AmberA700,"License"),
    Category(4,Icons.Filled.HealthAndSafety, CyanA700,"Health"),
    Category(5,Icons.Filled.Book, OrangeA700,"Library"),
    Category(6,Icons.Filled.FitnessCenter, RedA700,"Gym"),
    Category(7,Icons.Filled.Train, CyanA700,"Transit"),
    Category(8,Icons.Filled.Favorite, OrangeA700,"Membership"),
    Category(9,Icons.Filled.CardGiftcard, PinkA700,"Gift"),
    Category(10,Icons.Filled.Flag, Color.DarkGray,"Immigration"),
    )

val loginsCategoryOptions = listOf(
    Category(0,Icons.Filled.Person, GreenA700,"Personal"),
    Category(1,Icons.Filled.Work, Blue700Dark,"Work"),
    Category(2,Icons.Default.AttachMoney, GreenA700,"Finance"),
    Category(3,Icons.Filled.ShoppingCart, AmberA700,"Shopping"),
    Category(4,Icons.Filled.Flight, CyanA700,"Travel"),
    Category(5,Icons.Filled.Share, OrangeA700,"Social"),
    Category(6,Icons.Filled.MoreHoriz, Color.DarkGray,"Other"),
)

