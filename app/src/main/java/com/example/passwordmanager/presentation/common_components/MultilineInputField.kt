package com.example.passwordmanager.presentation.common_components

import androidx.compose.foundation.background
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun MultilineInputField(
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
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Column() {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = modifier
                        .padding(8.dp)
                        .weight(5f),
                    text = fieldTitle,
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    fontWeight = FontWeight.Bold
                )


            }

            OutlinedTextField(
                modifier = modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(MaterialTheme.colors.background),
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