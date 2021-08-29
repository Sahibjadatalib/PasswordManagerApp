package com.example.passwordmanager.ui.screens.welcomeScreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Announcement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passwordmanager.R

@Composable
fun DisclaimerDialog(
    onDismiss: ()->Unit
) {

    Surface(
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        modifier = Modifier
    ) {


        AlertDialog(
            modifier = Modifier
                .wrapContentSize(),
            onDismissRequest = { onDismiss() },
            text = {

                Column{
                    
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        
                        Icon(
                            tint = MaterialTheme.colors.primary,
                            imageVector = Icons.Default.Announcement,
                            contentDescription = "")
                        
                        Spacer(modifier = Modifier.width(8.dp))
                        
                        Text(
                            text = "Important Disclaimer",
                            style = TextStyle.Default.copy(
                                fontSize = MaterialTheme.typography.h6.fontSize,
                                fontWeight = FontWeight.Black,
                                fontFamily = FontFamily.Default
                            ),
                            color = MaterialTheme.colors.primary
                        )
                    }


                    
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        stringResource(id = R.string.disclaimer),
                        style = TextStyle.Default.copy(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily.Default
                        )
                    )
                }
            },
            backgroundColor = Color.White,
            confirmButton = {
                Button(onClick = { onDismiss() }) {
                    Text(text = "Ok")
                }
            },
            dismissButton = {
                Button(onClick = { onDismiss() }) {
                    Text(text = "Dismiss")
                }
            }
        )
    }

}