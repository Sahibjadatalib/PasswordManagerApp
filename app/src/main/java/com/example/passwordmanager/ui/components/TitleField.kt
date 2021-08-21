package com.example.passwordmanager.ui.components

import android.icu.text.CaseMap
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TitleField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 8.dp, start = 8.dp, end = 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {

        OutlinedTextField(
            modifier = modifier
                .border(1.dp, Color.Blue, RoundedCornerShape(8.dp))
                .fillMaxSize()
                .background(Color.White),
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.h5.fontSize,
                fontWeight = FontWeight.Bold
            ),
            value = text,
            placeholder = {
                Text(
                    "Title",
                    fontSize = MaterialTheme.typography.h5.fontSize
                )
            },
            onValueChange = { onTextChange(it) },
            maxLines = 1,
            singleLine = true
        )
    }
}


@Composable
fun TitleTrailingIcon(
    onClick: () -> Unit
) {
    Surface(
        shape = CircleShape,
        elevation = 4.dp,
        color = Color.White,
        modifier = Modifier.padding(8.dp)
    ) {
        IconButton(onClick = { onClick() }) {
            Icon(
                tint = MaterialTheme.colors.secondary,
                imageVector = Icons.Default.AddAPhoto, contentDescription = ""
            )
        }

    }
}