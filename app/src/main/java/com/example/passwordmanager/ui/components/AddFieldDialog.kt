package com.example.passwordmanager.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Language
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.passwordmanager.model.Category
import kotlinx.coroutines.selects.selectUnbiased


//@Composable
//fun AddFieldDialog(
//    modifier: Modifier = Modifier,
//    onConfirmClick: () -> Unit,
//    onDismiss: () -> Unit,
//    selectedField: String,
//    onSelectedFieldChange: (String) -> Unit
//) {
//
//    Surface(
//        modifier = modifier,
//        shape = RoundedCornerShape(8.dp),
//        elevation = 4.dp
//    ) {
//
//        AlertDialog(
//            modifier = modifier.wrapContentSize(),
//            onDismissRequest = { onDismiss() },
//            text = {
//
//                Column {
//
//                    Text(
//                        "New field",
//                        fontSize = MaterialTheme.typography.h5.fontSize,
//                        fontWeight = FontWeight.Black,
//                        modifier = Modifier.padding(8.dp)
//                    )
//
//                    extraFieldsList.forEach {field->
//
//                        FieldsOptionsRow(
//                            fieldIcon = field.fieldIcons,
//                            fieldName = field.fieldName,
//                            isSelected =
//                        ) {
//
//                        }
//
//                    }
//
//
//
//                }
//
//            },
//            backgroundColor = Color.White,
//            confirmButton = {
//                Button(onClick = {
//                    onConfirmClick()
//                    onDismiss()
//                }) {
//                    Text(text = "Confirm")
//                }
//            },
//            dismissButton = {
//                Button(onClick = { onDismiss() }) {
//                    Text(text = "Dismiss")
//                }
//            }
//        )
//
//    }
//
//
//}
//
//
//@Composable
//fun FieldsOptionsRow(
//    modifier: Modifier = Modifier,
//    fieldIcon: ImageVector,
//    fieldName: String,
//    isSelected: Boolean,
//    onClick: () -> Unit
//) {
//
//    Surface(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp),
//        color = if (isSelected) MaterialTheme.colors.primary else Color.White,
//        shape = RoundedCornerShape(16.dp),
//        elevation = 4.dp,
//        border = BorderStroke(1.dp, Color.Blue)
//    ) {
//        Row(
//            modifier = modifier
//                .clickable { onClick() }
//                .padding(8.dp),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.Start
//        ) {
//
//            Surface(
//                shape = CircleShape,
//                modifier = modifier
//                    .wrapContentSize()
//                    .weight(2f)
//            ) {
//                Icon(
//                    modifier = modifier.padding(4.dp),
//                    tint = MaterialTheme.colors.primary,
//                    imageVector = fieldIcon, contentDescription = ""
//                )
//            }
//            Spacer(modifier = modifier.width(16.dp))
//            Text(
//                modifier = modifier.weight(7f),
//                text = fieldName,
//                fontSize = MaterialTheme.typography.body1.fontSize,
//                fontWeight = FontWeight.Normal,
//                fontStyle = FontStyle.Normal
//            )
//
//            if (isSelected) {
//                Icon(
//                    modifier = modifier.weight(1f),
//                    imageVector = Icons.Default.Done, contentDescription = ""
//                )
//            }
//        }
//    }
//
//
//}

