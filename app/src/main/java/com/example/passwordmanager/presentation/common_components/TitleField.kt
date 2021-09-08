package com.example.passwordmanager.presentation.common_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.passwordmanager.presentation.theme.Theme

@Composable
fun TitleField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit
) {

    val focusManager = LocalFocusManager.current

    Surface(
        modifier = modifier.fillMaxWidth().padding(Theme.paddings.medium),
        shape = RoundedCornerShape(8.dp),
        elevation = Theme.elevation.medium
    ) {

        OutlinedTextField(
            modifier = modifier.fillMaxSize().background(Theme.colors.background),
            textStyle = Theme.typography.h6,
            value = text,
            placeholder = { Text("Title", style = Theme.typography.h6) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {focusManager.clearFocus()}),
            shape = RoundedCornerShape(8.dp),
            onValueChange = { onTextChange(it) },
            maxLines = 1,
            singleLine = true
        )
    }
}