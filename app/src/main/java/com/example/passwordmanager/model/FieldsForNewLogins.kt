package com.example.passwordmanager.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType

data class FieldsForNewLogins(
    val fieldName: String,
    val fieldText: MutableState<String>,
    val fieldTextType: KeyboardType,
    val fieldLeadingIcon: ImageVector,
    val fieldPlaceholder: String,
    val isPresentOnScreen: MutableState<Boolean>
)

val fieldsForNewLogins = listOf(

    FieldsForNewLogins(
        "Username",
        mutableStateOf(""),
        KeyboardType.Text,
        Icons.Default.Person,
        "username",
        mutableStateOf(true)
    ),

    FieldsForNewLogins(
        "Password",
        mutableStateOf(""),
        KeyboardType.Password,
        Icons.Default.VpnKey,
        "password",
        mutableStateOf(true)
    ),

)