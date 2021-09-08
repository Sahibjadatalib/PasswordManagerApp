package com.example.passwordmanager.ui.screens.settings.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passwordmanager.presentation.common_components.MyOutlinedTextField
import com.example.passwordmanager.presentation.theme.Theme

@Composable
fun ChangeHintDialog(
    onDismiss: () -> Unit,
    hint: String,
    onchangeHint: (String) -> Unit,
    currentHint: String,
    onSaveClick: () -> Unit
) {

    Surface(
        elevation = Theme.elevation.large
    ) {

        AlertDialog(
            onDismissRequest = {
                onDismiss()
            },
            shape = RoundedCornerShape(8.dp),
            text = {

                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ) {

                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = "Change password hint",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Black,
                        )
                    )

                    MyOutlinedTextField(
                        placeholderText = "hint",
                        value = hint,
                        onValueChange = { onchangeHint(it) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        )
                    )

                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = "current hint: $currentHint",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.ExtraLight,
                        )
                    )

                }

            },
            confirmButton = {
                Button(onClick = {
                    onSaveClick()
                }
                ) {
                    Text(text = "Save")
                }
            },
            dismissButton = {
                Button(onClick = { onDismiss() }) {
                    Text(text = "Cancel")
                }
            }

        )

    }

}