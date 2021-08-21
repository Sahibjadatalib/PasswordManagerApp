package com.example.passwordmanager.ui.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun MyFloatingBtn(
    onClick: () -> Unit
) {

    FloatingActionButton(
        onClick = { onClick() }
    ) {
        Icon(Icons.Default.Add, "")
    }
}