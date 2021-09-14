package com.stalib.passwordmanager.ui.screens.welcomeScreen.components

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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.stalib.passwordmanager.presentation.theme.Theme

@Composable
fun HintDialog(
    onDismiss: ()->Unit,
    hint: String
) {

    val focusManager = LocalFocusManager.current

    Surface(
        modifier = Modifier,
        elevation = Theme.elevation.large,
        shape = RoundedCornerShape(8.dp)
    ) {

        AlertDialog(
            modifier = Modifier,
            onDismissRequest = {
                onDismiss()
            },
            text = {

                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {

                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = "Hint!",
                        style = Theme.typography.subtitle1.copy(fontWeight = FontWeight.Black),
                        color = Theme.colors.primary
                    )

                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = hint,
                        style = Theme.typography.body1
                    )

                }


            },
            buttons = {}

        )

    }

}