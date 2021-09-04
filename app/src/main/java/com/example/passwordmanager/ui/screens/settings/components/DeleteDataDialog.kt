package com.example.passwordmanager.ui.screens.settings.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passwordmanager.ui.theme.Theme

@Composable
fun DeleteDataDialog(
    onDismiss: ()->Unit,
    onConfirmClick: ()->Unit
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
                        text = "Alert!",
                        style = TextStyle(
                            color = Color.Red,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Black,
                        )
                    )

                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = "Do you really wants to delete all data. After deleting data cannot be recovered again.",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                        )
                    )

                }


            },
            confirmButton = {
                Button(onClick = {
                    onConfirmClick()
                    onDismiss()
                }) {
                    Text(text = "Delete")
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