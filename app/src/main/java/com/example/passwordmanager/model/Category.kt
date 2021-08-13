package com.example.passwordmanager.model


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.passwordmanager.ui.theme.*

data class Category(
    val icon: ImageVector,
    val tintColor: Color,
    val title: String,
    val isSelected: MutableState<Boolean>
)

val OthersCategoryOptions = listOf(
    Category(Icons.Filled.Description, LightGreenA400,"Note", mutableStateOf(true)),
    Category(Icons.Filled.Computer, Blue700Dark,"Computer", mutableStateOf(false)),
    Category(Icons.Default.Audiotrack, PinkA200,"Music", mutableStateOf(false)),
)

val CardsCategoryOptions = listOf(
    Category(Icons.Filled.AttachMoney, LightGreenA400,"Credit", mutableStateOf(true)),
    Category(Icons.Filled.AccountBalance, Blue700Dark,"Debit", mutableStateOf(false)),
    Category(Icons.Default.PermIdentity, Color.Green,"Identity", mutableStateOf(false)),
    Category(Icons.Filled.DirectionsCar, AmberA200,"License", mutableStateOf(false)),
    Category(Icons.Filled.HealthAndSafety, CyanA200,"Health", mutableStateOf(false)),
    Category(Icons.Filled.Book, DeepOrangeA700,"Library", mutableStateOf(false)),
    Category(Icons.Filled.FitnessCenter, Color.DarkGray,"Gym", mutableStateOf(false)),
    Category(Icons.Filled.Train, CyanA200,"Transit", mutableStateOf(false)),
    Category(Icons.Filled.Favorite, DeepOrangeA700,"Membership", mutableStateOf(false)),
    Category(Icons.Filled.CardGiftcard, Color.DarkGray,"Gift", mutableStateOf(false)),
    Category(Icons.Filled.Flag, Color.DarkGray,"Immigration", mutableStateOf(false)),

    )

val LoginsCategoryOptions = listOf(
    Category(Icons.Filled.Person, LightGreenA400,"Personal", mutableStateOf(true)),
    Category(Icons.Filled.Work, Blue700Dark,"Work", mutableStateOf(false)),
    Category(Icons.Default.AttachMoney, Color.Green,"Finance", mutableStateOf(false)),
    Category(Icons.Filled.ShoppingCart, AmberA200,"Shopping", mutableStateOf(false)),
    Category(Icons.Filled.Flight, CyanA200,"Travel", mutableStateOf(false)),
    Category(Icons.Filled.Share, DeepOrangeA700,"Social", mutableStateOf(false)),
    Category(Icons.Filled.MoreHoriz, Color.DarkGray,"Other", mutableStateOf(false)),

)
