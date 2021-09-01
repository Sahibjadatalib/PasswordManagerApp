package com.example.passwordmanager.ui.screens.settings.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passwordmanager.model.Settings

@Composable
fun SettingsSection(
    modifier: Modifier = Modifier,
    header: String,
    content: @Composable ()->Unit
) {

    val expanded = remember { mutableStateOf(true) }
    val rotateAnimation by animateFloatAsState(targetValue = if (expanded.value) 180f else 0f)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
            )
            .padding(top = 16.dp, bottom = 8.dp, start = 8.dp, end = 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp

    ) {

        Column {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .clickable(onClick = { expanded.value = !expanded.value })
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = modifier,
                    text = header,
                    color = MaterialTheme.colors.primary,
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Normal
                )

                IconButton(
                    modifier = modifier
                        .rotate(rotateAnimation),
                    onClick = { expanded.value = !expanded.value }
                ) {
                    Icon(Icons.Default.ExpandMore, "")
                }
            }

            if (expanded.value) {

                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {

                    Divider(color = Color.LightGray, thickness = 1.dp)
                    content()
                }

            }
        }

    }


}


@Composable
private fun RowOptions(
    setting: Settings
) {




}