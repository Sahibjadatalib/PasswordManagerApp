package com.example.passwordmanager.ui.components

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.widget.Placeholder
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import java.time.format.DateTimeFormatter

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
    isReadableOnly: Boolean? = false
) {

    val dialog = remember { MaterialDialog() }

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
                if(isReadableOnly == true){
                    dialog.show()
                }
            }
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = modifier
                        .padding(16.dp)
                        .weight(5f),
                    text = fieldTitle,
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    fontWeight = FontWeight.Bold
                )


            }

            if (isReadableOnly != null) {
                OutlinedTextField(
                    modifier = modifier
                        .padding(8.dp)
                        .fillMaxWidth()
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
                    maxLines = 1,
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true,
                    readOnly = isReadableOnly
                )
            }


        }
    }
}