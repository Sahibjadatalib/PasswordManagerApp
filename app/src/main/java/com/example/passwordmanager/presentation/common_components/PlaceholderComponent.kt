package com.example.passwordmanager.presentation.common_components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.passwordmanager.presentation.theme.Theme

@Composable
fun PlaceholderComponent(
    title: String,
    description: String,
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

        Text(
            text = title,
            style = Theme.typography.h5,
            color = Theme.colors.primary
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = description,
            style = Theme.typography.h6.copy(fontWeight = FontWeight.Light),
        )


    }

}