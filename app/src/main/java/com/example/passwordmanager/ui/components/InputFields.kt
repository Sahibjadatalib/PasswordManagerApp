package com.example.passwordmanager.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    fieldTitle: String,
    editIcon: ImageVector? = null,
    minusIcon: ImageVector? = null,
    onMinusIconClick: ()->Unit,
    text: String,
    keyboardType: KeyboardType,
    onTextChange: (String)->Unit,
    leadingIcon: ImageVector,
    placeholderText: String,
    trailingIcon: ImageVector? = null,
){
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(4.dp),
        elevation = 4.dp
    ) {
        Column {
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
                if(editIcon!=null){
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(modifier = modifier.weight(1f),
                            imageVector = editIcon, contentDescription = "")
                    }
                }

                if(minusIcon!=null){
                    IconButton(onClick = { onMinusIconClick() }) {
                        Icon(
                            tint = Color.Red,
                            modifier = modifier.weight(1f),
                            imageVector = minusIcon, contentDescription = "")
                    }
                }


            }
            OutlinedTextField(
                modifier = modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.background),
                leadingIcon = { Icon(
                    tint = MaterialTheme.colors.primary,
                    imageVector = leadingIcon, contentDescription = "")},
                placeholder = {Text(placeholderText)},
                value = text, 
                onValueChange = {onTextChange(it)},
                keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                maxLines = 1,
                singleLine = true
            )
        }
    }
}