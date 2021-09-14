package com.stalib.passwordmanager.presentation.common_components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.stalib.passwordmanager.presentation.theme.Theme

@Composable
fun PlaceholderComponent(
    icon: ImageVector,
    title: String
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Icon(
            modifier = Modifier.size(64.dp),
            tint = Theme.colors.primary,
            imageVector = icon,
            contentDescription = ""
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = title,
            style = Theme.typography.h5,
            color = Theme.colors.primary
        )

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Click on ",
                style = Theme.typography.h6.copy(fontWeight = FontWeight.Light),
            )

            Icon(
                tint = Theme.colors.primary,
                imageVector = Icons.Default.AddCircle,
                contentDescription = ""
            )

            Text(
                text = " icon to add new items",
                style = Theme.typography.h6.copy(fontWeight = FontWeight.Light),
            )

        }


    }

}