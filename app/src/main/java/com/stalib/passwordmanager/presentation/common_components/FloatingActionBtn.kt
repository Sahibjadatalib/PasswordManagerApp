package com.stalib.passwordmanager.presentation.common_components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import com.stalib.passwordmanager.presentation.theme.Theme

@Composable
fun MyFloatingBtn(
    onClick: () -> Unit
) {

    FloatingActionButton(
        backgroundColor = Theme.colors.primary,
        onClick = { onClick() }
    ) {
        Icon(
            tint = contentColorFor(backgroundColor = Theme.colors.primary),
            imageVector = Icons.Default.Add, contentDescription = ""
        )
    }
}