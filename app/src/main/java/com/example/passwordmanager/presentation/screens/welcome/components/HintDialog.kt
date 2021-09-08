package com.example.passwordmanager.ui.screens.welcomeScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
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
import com.example.passwordmanager.presentation.theme.Theme

@Composable
fun HintDialog(
    onDismiss: ()->Unit,
    hint: String
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
                        text = "Hint!",
                        style = TextStyle(
                            color = Color.Green,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Black,
                        )
                    )

                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = hint,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                        )
                    )

                }


            },
            buttons = {}

        )

    }

}