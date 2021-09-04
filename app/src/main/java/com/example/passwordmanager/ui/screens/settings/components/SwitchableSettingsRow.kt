package com.example.passwordmanager.ui.screens.settings.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passwordmanager.model.Settings
import com.example.passwordmanager.model.generalSettings
import com.example.passwordmanager.ui.theme.Theme

@Composable
fun SwitchableSettingsRow(
    item: Settings,
    switchState: Boolean,
    onSwitchClick: (Boolean)->Unit
) {

    val lastOfGeneral = generalSettings.last()


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        Surface(
            modifier = Modifier
                .padding(4.dp)
                .weight(1f),
            shape = CircleShape,
            color = item.iconColor.copy(alpha = 0.25f)
        ) {
            Icon(
                modifier = Modifier.padding(4.dp),
                tint = item.iconColor,
                imageVector = item.icon, contentDescription = ""
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(6f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = item.title,
                style = Theme.typography.subtitle1
            )
            Spacer(modifier = Modifier.height(4.dp))
            item.subtitle?.let {
                Text(
                    text = it,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light
                    )
                )
            }


        }

        Switch(
            modifier = Modifier.weight(1f),
            checked = switchState,
            onCheckedChange = {onSwitchClick(it)},
            colors = SwitchDefaults.colors(
                uncheckedTrackColor = Theme.colors.primary.copy(alpha = 0.4f),
                uncheckedThumbColor = Theme.colors.primary,
                checkedTrackColor = Theme.colors.primary.copy(alpha = 0.4f),
                checkedThumbColor = Theme.colors.primary
            )
        )



    }

    if(item != lastOfGeneral){
        Divider(color = Color.LightGray, thickness = 1.dp)

    }



}