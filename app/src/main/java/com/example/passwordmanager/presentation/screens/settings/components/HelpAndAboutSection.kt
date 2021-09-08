package com.example.passwordmanager.presentation.screens.settings.components

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.passwordmanager.domain.models.Settings
import com.example.passwordmanager.presentation.theme.Theme
import com.example.passwordmanager.ui.screens.settings.components.SettingsRow

@Composable
fun HelpAndAboutSection(
    header: String,
    list: List<Settings>,
    onContactUsClick: ()->Unit,
    onSentSuggestion: ()->Unit,
    onReportBug: ()->Unit,
    onPrivacy: ()->Unit,
    onRateApp: ()->Unit
) {

    val expanded = remember { mutableStateOf(true) }
    val rotateAnimation by animateFloatAsState(targetValue = if (expanded.value) 180f else 0f)

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
            )
            .padding(Theme.paddings.medium),
        shape = RoundedCornerShape(8.dp),
        elevation = Theme.elevation.medium
    ) {

        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { expanded.value = !expanded.value })
                    .padding(Theme.paddings.medium),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = header,
                    style = Theme.typography.h6
                )

                IconButton(
                    modifier = Modifier.rotate(rotateAnimation),
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
                    SettingsRow(
                        item = list[0],
                        onClick = { onContactUsClick() }
                    )
                    SettingsRow(
                        item = list[1],
                        onClick = { onSentSuggestion() }
                    )
                    SettingsRow(
                        item = list[2],
                        onClick = { onReportBug() }
                    )
                    SettingsRow(
                        item = list[3],
                        onClick = { onPrivacy() }
                    )
                    SettingsRow(
                        item = list[4],
                        onClick = { onRateApp() }
                    )
                }

            }
        }

    }

}