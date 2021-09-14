package com.stalib.passwordmanager.ui.screens.settings.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stalib.passwordmanager.presentation.common_components.MyOutlinedTextField
import com.stalib.passwordmanager.presentation.theme.Theme

@Composable
fun ChangePasswordDialog(
    onDismiss: () -> Unit,
    currentPassword: String,
    onCurrentPasswordChange: (String) -> Unit,
    newPassword: String,
    onNewPasswordChange: (String) -> Unit,
    confirmNewPassword: String,
    onConfirmNewPassword: (String) -> Unit,
    onSaveClick: () -> Unit
) {

    val focusManager = LocalFocusManager.current

    Surface(
        elevation = Theme.elevation.large
    ) {

        AlertDialog(
            modifier = Modifier,
            onDismissRequest = {
                onDismiss()
            },
            shape = RoundedCornerShape(8.dp),
            text = {

                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {

                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = "Change your master password",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Black,
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    MyOutlinedTextField(
                        placeholderText = "current password",
                        value = currentPassword,
                        onValueChange = { onCurrentPasswordChange(it) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Next
                        )
                    )


                    MyOutlinedTextField(
                        placeholderText = "new password",
                        value = newPassword,
                        onValueChange = { onNewPasswordChange(it) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Next
                        )
                    )


                    MyOutlinedTextField(
                        placeholderText = "confirm new password",
                        value = confirmNewPassword,
                        onValueChange = { onConfirmNewPassword(it) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        )
                    )

                }


            },
            confirmButton = {
                Button(onClick = { onSaveClick() }) {
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