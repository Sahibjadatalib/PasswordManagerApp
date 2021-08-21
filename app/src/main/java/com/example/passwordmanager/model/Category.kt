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

//val OthersCategoryOptions = listOf(
//    Category(Icons.Filled.Description, LightGreenA400,"Note"),
//    Category(Icons.Filled.Computer, Blue700Dark,"Computer"),
//    Category(Icons.Default.Audiotrack, PinkA200,"Music"),
//)
//
//val CardsCategoryOptions = listOf(
//    Category(Icons.Filled.AttachMoney, LightGreenA400,"Credit"),
//    Category(Icons.Filled.AccountBalance, Blue700Dark,"Debit"),
//    Category(Icons.Default.PermIdentity, Color.Green,"Identity"),
//    Category(Icons.Filled.DirectionsCar, AmberA200,"License"),
//    Category(Icons.Filled.HealthAndSafety, CyanA200,"Health"),
//    Category(Icons.Filled.Book, DeepOrangeA700,"Library"),
//    Category(Icons.Filled.FitnessCenter, Color.DarkGray,"Gym"),
//    Category(Icons.Filled.Train, CyanA200,"Transit"),
//    Category(Icons.Filled.Favorite, DeepOrangeA700,"Membership"),
//    Category(Icons.Filled.CardGiftcard, Color.DarkGray,"Gift"),
//    Category(Icons.Filled.Flag, Color.DarkGray,"Immigration"),
//
//    )

val loginsCategoryOptions = listOf(
    Category(0,Icons.Filled.Person, LightGreenA400,"Personal"),
    Category(1,Icons.Filled.Work, Blue700Dark,"Work"),
    Category(2,Icons.Default.AttachMoney, Color.Green,"Finance"),
    Category(3,Icons.Filled.ShoppingCart, AmberA200,"Shopping"),
    Category(4,Icons.Filled.Flight, CyanA200,"Travel"),
    Category(5,Icons.Filled.Share, DeepOrangeA700,"Social"),
    Category(6,Icons.Filled.MoreHoriz, Color.DarkGray,"Other"),

)

//val loginsCategoryOptions = listOf(
//    Category(R.drawable.ic_menu_black_24dp, LightGreenA400, "Personal"),
//    Category(R.drawable.ic_menu_black_24dp, Blue700Dark, "Work"),
//    Category(R.drawable.ic_more_vert_black_24dp, Color.Green, "Finance"),
//    Category(R.drawable.password, AmberA200, "Shopping"),
//    Category(R.drawable.ic_language_black_24dp, CyanA200, "Travel"),
//    Category(R.drawable.ic_search_black_24dp, DeepOrangeA700, "Social"),
//    Category(R.drawable.ic_person_black_24dp, Color.DarkGray, "Other"),
//
//    )

