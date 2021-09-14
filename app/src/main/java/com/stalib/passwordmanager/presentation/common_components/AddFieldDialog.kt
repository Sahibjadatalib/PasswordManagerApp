package com.stalib.passwordmanager.presentation.common_components


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

