package com.example.passwordmanager.presentation.common_components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.passwordmanager.presentation.theme.Theme

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    fieldTitle: String,
    text: String,
    keyboardOptions: KeyboardOptions,
    onTextChange: (String) -> Unit,
    leadingIcon: ImageVector,
    placeholderText: String,
    trailingIcon: ImageVector? = null,
    isReadableOnly: Boolean = false,
    maxLine: Int = 1,
    singleLine: Boolean = true
) {

    val focusManager = LocalFocusManager.current

    Surface(
        modifier = modifier.fillMaxWidth().padding(Theme.paddings.medium),
        shape = Theme.shapes.medium,
        elevation = Theme.elevation.medium
    ) {
        Column() {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = modifier.padding(8.dp).weight(5f),
                    text = fieldTitle,
                    style = Theme.typography.subtitle1
                )


            }

            OutlinedTextField(
                modifier = modifier.padding(Theme.paddings.medium).fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        tint = MaterialTheme.colors.primary,
                        imageVector = leadingIcon, contentDescription = ""
                    )
                },
                placeholder = { Text(placeholderText) },
                value = text,
                onValueChange = { onTextChange(it) },
                keyboardOptions = keyboardOptions,
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() },
                    onNext = {focusManager.moveFocus(focusDirection = FocusDirection.Down)},
                    onPrevious = {focusManager.moveFocus(focusDirection = FocusDirection.Up)}
                ),
                maxLines = maxLine,
                shape = RoundedCornerShape(8.dp),
                singleLine = singleLine,
                readOnly = isReadableOnly
            )


        }
    }
}