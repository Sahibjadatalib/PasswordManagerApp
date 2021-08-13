package com.example.passwordmanager.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun AddIconBtn(
    modifier: Modifier = Modifier,
    onAddImageIconClick: ()->Unit
){

    Surface(
        modifier = modifier
            .padding(16.dp)
            .clickable { onAddImageIconClick() }
            .height(128.dp)
            .width(128.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        border = BorderStroke(2.dp, Color.Blue)
    ) {

        Icon(
            tint = Color.Blue,
            modifier = modifier.padding(32.dp),
            imageVector = Icons.Default.AddPhotoAlternate, contentDescription = "")

    }
}