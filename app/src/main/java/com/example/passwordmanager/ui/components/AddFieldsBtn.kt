package com.example.passwordmanager.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AddFieldsBtn(
    modifier: Modifier = Modifier,
    onClick: ()->Unit
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp)
            .padding(8.dp)
            .border(1.dp, Color.Blue, RoundedCornerShape(4.dp))
            .clickable { onClick() },
        shape = RoundedCornerShape(4.dp),
        elevation = 4.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                tint = MaterialTheme.colors.primary,
                imageVector = Icons.Default.AddCircleOutline, contentDescription = ""
            )
            Text(
                text = "ADD FIELD",
                color = MaterialTheme.colors.primary
            )
        }
    }
}