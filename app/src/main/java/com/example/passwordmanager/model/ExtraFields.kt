package com.example.passwordmanager.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Pin
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType

data class ExtraField(
    val fieldName: String,
    val fieldText: MutableState<String>,
    val fieldTextType: KeyboardType,
    val fieldLeadingIcon: ImageVector,
    val minusIcon: ImageVector,
    val fieldPlaceholder: String,
    val isPresentOnScreen: MutableState<Boolean>
)

val extraFields = listOf(

    ExtraField(
        "Website",
        mutableStateOf(""),
        KeyboardType.Text,
        Icons.Default.Language,
        Icons.Default.RemoveCircleOutline,
        "www.stalib.com",
        mutableStateOf(false)
    ),

    ExtraField(
        "Email",
        mutableStateOf(""),
        KeyboardType.Email,
        Icons.Default.AlternateEmail,
        Icons.Default.RemoveCircleOutline,
        "stalib@gmail.com",
        mutableStateOf(false)
    ),

    ExtraField(
        "Number",
        mutableStateOf(""),
        KeyboardType.Number,
        Icons.Outlined.Pin,
        Icons.Default.RemoveCircleOutline,
        "123...",
        mutableStateOf(false)
    ),

    ExtraField(
        "PIN",
        mutableStateOf(""),
        KeyboardType.Number,
        Icons.Default.Dialpad,
        Icons.Default.RemoveCircleOutline,
        "7011",
        mutableStateOf(false)
    ),

    ExtraField(
        "Date[DD/MM/YYYY]",
        mutableStateOf(""),
        KeyboardType.Text,
        Icons.Default.CalendarToday,
        Icons.Default.RemoveCircleOutline,
        "22/07/1999",
        mutableStateOf(false)
    ),

    ExtraField(
        "Date[MM/YY]",
        mutableStateOf(""),
        KeyboardType.Text,
        Icons.Default.CalendarToday,
        Icons.Default.RemoveCircleOutline,
        "07/99",
        mutableStateOf(false)
    ),

    ExtraField(
        "Text",
        mutableStateOf(""),
        KeyboardType.Text,
        Icons.Default.Title,
        Icons.Default.RemoveCircleOutline,
        "Hi there",
        mutableStateOf(false)
    ),

    ExtraField(
        "MultiLine text",
        mutableStateOf(""),
        KeyboardType.Text,
        Icons.Default.TextFields,
        Icons.Default.RemoveCircleOutline,
        "I am feeling lucky...",
        mutableStateOf(false)
    ),

    )