package com.stalib.passwordmanager.presentation.common_components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.stalib.passwordmanager.presentation.theme.Theme

@Composable
fun SearchBar(
    text: String,
    onTextChange: (String)->Unit,
){

    val focusManager = LocalFocusManager.current

    Surface(
        modifier = Modifier.fillMaxWidth().padding(Theme.paddings.medium),
        elevation = Theme.elevation.medium,
        shape = RoundedCornerShape(32.dp),
        border = BorderStroke(1.dp,MaterialTheme.colors.primary)
    ) {

        OutlinedTextField(
            value = text,
            onValueChange = { onTextChange(it) },
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
            placeholder = { Text("Search") },
            shape = RoundedCornerShape(32.dp),
            singleLine = true,
            maxLines = 1,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text, imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {focusManager.clearFocus()})

        )
    }

}