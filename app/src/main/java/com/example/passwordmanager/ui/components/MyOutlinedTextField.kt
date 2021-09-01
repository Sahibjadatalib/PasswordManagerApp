package com.example.passwordmanager.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp

@Composable
fun MyOutlinedTextField(
    leadingIcon: @Composable (()->Unit)? = null,
    placeholderText: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    maxLine: Int = 1,
    singleLine: Boolean = true,
    readOnly: Boolean = false
) {

    val focusManager = LocalFocusManager.current

    Surface(
        modifier = Modifier.padding(4.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,

        ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background),
            leadingIcon = leadingIcon,
            placeholder = { Text(placeholderText) },
            value = value,
            onValueChange = { onValueChange(it) },
            keyboardOptions = keyboardOptions,
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() },
                onNext = { focusManager.moveFocus(focusDirection = FocusDirection.Down) },
                onPrevious = { focusManager.moveFocus(focusDirection = FocusDirection.Up) }
            ),
            maxLines = maxLine,
            shape = RoundedCornerShape(8.dp),
            singleLine = singleLine,
            readOnly = readOnly
        )
    }

}