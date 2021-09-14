package com.stalib.passwordmanager.presentation.common_components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ExpandableCard(
    modifier: Modifier = Modifier

) {

    var expended by remember { mutableStateOf(false) }
    val rotateAnimation by animateFloatAsState(targetValue = if (expended) 180f else 0f)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
            )
            .padding(top = 16.dp,bottom = 8.dp,start = 8.dp,end = 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp

    ) {

        Column {
            Row(
                modifier = modifier
                    .padding(8.dp)
                    .clickable(onClick = {expended = !expended}),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    modifier = modifier.weight(6f),
                    text = "Category",
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Normal
                )
                IconButton(
                    modifier = modifier
                        .rotate(rotateAnimation)
                        .weight(1f),
                    onClick = { expended = !expended }
                ) {
                    Icon(Icons.Default.ExpandMore,"")
                }
            }
            
            if (expended){
                Text(
                    modifier = modifier.padding(8.dp),
                    text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                            "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s," +
                            " when an unknown printer took a galley of type and scrambled it to make a type specimen book. " +
                            "It has survived not only five centuries, but also the leap into electronic typesetting, " +
                            "remaining essentially unchanged." +
                            " It was popularised in the 1960s with the release of Letraset sheets containing " +
                            "Lorem Ipsum passages, and more recently with desktop publishing software like Aldus " +
                            "PageMaker including versions of Lorem Ipsum.\n" +
                            "\n",
                    fontSize = MaterialTheme.typography.body1.fontSize,
                    fontWeight = FontWeight.Normal,

                )
            }
        }
    }
}