package com.stalib.passwordmanager.ui.screens.welcomeScreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun SignInputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,

    ) {

    val focusManager = LocalFocusManager.current
    val isVisible = remember { mutableStateOf(false) }

    val passwordVisibility = if(isVisible.value){
        VisualTransformation.None
    }else{
        PasswordVisualTransformation()
    }

    val trailingIcon = if (isVisible.value) {
        Icons.Default.Visibility
    } else {
        Icons.Default.VisibilityOff
    }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        border = BorderStroke(1.dp, MaterialTheme.colors.primary)
    ) {

        OutlinedTextField(
            modifier = modifier,
            value = value,
            onValueChange = { onValueChange(it) },
            placeholder = {
                Text(
                    text = placeholder
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ),
            trailingIcon = {
                IconButton(onClick = {
                    isVisible.value = !isVisible.value

                }
                ) {
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = ""
                    )
                }
            },
            visualTransformation = passwordVisibility,
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            maxLines = 1
        )
    }
}