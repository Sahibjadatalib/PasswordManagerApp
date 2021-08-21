package com.example.passwordmanager.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector

data class ExtraField(
    val fieldName: String,
    val fieldIcons: ImageVector
)

val extraFieldsList = listOf(
    ExtraField("Website", Icons.Default.Language),
    ExtraField("Email", Icons.Default.AlternateEmail),
    ExtraField("Number", Icons.Default.Pin),
    ExtraField("PIN", Icons.Default.Dialpad),
    ExtraField("Date[DD/MM/YYYY]", Icons.Default.CalendarToday),
    ExtraField("Date[MM/YY]", Icons.Default.CalendarToday),
    ExtraField("Text", Icons.Default.Title),
    ExtraField("MultiLine text", Icons.Default.TextFields),

)