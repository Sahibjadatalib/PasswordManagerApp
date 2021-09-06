package com.example.passwordmanager.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.passwordmanager.ui.theme.Theme

@Composable
fun ItemDetailRow(
    icon: ImageVector,
    title: String,
    text: String
) {

    Row(
        modifier = Modifier.padding(Theme.paddings.medium),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Surface(
            shape = CircleShape,
            color = Theme.colors.primary.copy(alpha = 0.4f)
        ) {
            Icon(
                tint = Theme.colors.primary,
                modifier = Modifier.size(32.dp).padding(8.dp),
                imageVector = icon, contentDescription = ""
            )
        }


        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = title,
                style = Theme.typography.subtitle1
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = text,
                style = Theme.typography.body1
            )
        }

    }

}