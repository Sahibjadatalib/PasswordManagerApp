package com.example.passwordmanager.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.passwordmanager.ui.theme.Theme

@Composable
fun TitleField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit
) {


    val focusManager = LocalFocusManager.current

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(Theme.paddings.medium),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {

        OutlinedTextField(
            modifier = modifier
                .fillMaxSize()
                .background(Theme.colors.background),
            textStyle = Theme.typography.h6,
            value = text,
            placeholder = {
                Text(
                    "Title",
                    style = Theme.typography.h6
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {focusManager.clearFocus()}),
            shape = RoundedCornerShape(8.dp),
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