package com.stalib.passwordmanager.ui.screens.settings.components

import androidx.compose.foundation.clickable
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
import com.stalib.passwordmanager.domain.models.Settings
import com.stalib.passwordmanager.domain.models.dangerZoneSettings
import com.stalib.passwordmanager.domain.models.helpsAndAboutSettings
import com.stalib.passwordmanager.domain.models.securitySettings
import com.stalib.passwordmanager.presentation.theme.Theme

@Composable
fun SettingsRow(
    item: Settings,
    onClick: ()->Unit
) {

    val lastOfHelpSection = helpsAndAboutSettings.last()
    val lastOfSecuritySection = securitySettings.last()
    val lastOfDangerSection = dangerZoneSettings.last()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        Surface(
            modifier = Modifier.padding(4.dp),
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
            modifier = Modifier,
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

    }

    if(item != lastOfHelpSection && item != lastOfSecuritySection && item != lastOfDangerSection){
        Divider(color = Color.LightGray, thickness = 1.dp)
    }


}