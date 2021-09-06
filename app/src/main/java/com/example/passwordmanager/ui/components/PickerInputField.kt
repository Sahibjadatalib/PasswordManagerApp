package com.example.passwordmanager.ui.components

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.passwordmanager.ui.theme.Theme
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import java.time.format.DateTimeFormatter

@Composable
fun PickerInputField(
    modifier: Modifier = Modifier,
    fieldTitle: String,
    text: String,
    keyboardOptions: KeyboardOptions,
    onTextChange: (String) -> Unit,
    leadingIcon: ImageVector,
    placeholderText: String,
    trailingIcon: ImageVector? = null,
    maxLine: Int = 1,
    singleLine: Boolean = true
) {

    val dialog = remember { MaterialDialog() }
    val focusManager = LocalFocusManager.current

    dialog.build(buttons = {
        positiveButton("OK")
        negativeButton("Cancel")
    }) {
        datepicker { date ->
            val formattedDate = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                date.format(
                    DateTimeFormatter.ofPattern("MM/yy")
                )
            } else {
                TODO("VERSION.SDK_INT < O")
            }

            onTextChange(formattedDate.toString())
        }
    }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.clickable {
                dialog.show()
            }
        ) {
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
                modifier = modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clickable { dialog.show() }
                    .background(MaterialTheme.colors.background),
                leadingIcon = {
                    IconButton(onClick = { dialog.show() }) {
                        Icon(
                            tint = MaterialTheme.colors.primary,
                            imageVector = leadingIcon, contentDescription = ""
                        )
                    }
                },
                placeholder = {
                    Text(placeholderText)
                },
                value = text,
                onValueChange = { onTextChange(it) },
                keyboardOptions = keyboardOptions,
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() },
                    onNext = { focusManager.moveFocus(focusDirection = FocusDirection.Down) },
                    onPrevious = { focusManager.moveFocus(focusDirection = FocusDirection.Up) }
                ),
                maxLines = maxLine,
                shape = RoundedCornerShape(8.dp),
                singleLine = singleLine,
                readOnly = true,
                enabled = false
            )


        }
    }

}