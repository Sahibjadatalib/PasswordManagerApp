package com.example.passwordmanager.ui.screens.settings.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Public
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passwordmanager.R
import android.content.Intent
import androidx.core.content.ContextCompat

import androidx.core.content.ContextCompat.startActivity
import com.example.passwordmanager.presentation.theme.Theme


@Composable
fun AppDetails() {

    Surface(
        modifier = Modifier.fillMaxWidth().padding(Theme.paddings.medium),
        shape = RoundedCornerShape(8.dp),
        elevation = Theme.elevation.medium,
    ) {

        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Image(
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, start = 16.dp, end = 0.dp).size(64.dp),
                painter = painterResource(id = R.drawable.app_logo_latest), contentDescription = "")
            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {

                Text(
                    text = "Password Manager",
                    style = Theme.typography.h6
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "version - 1.0.0",
                    style = Theme.typography.body1
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "All rights reserved",
                    style = Theme.typography.body2.copy(fontWeight = FontWeight.Light)
                )

            }

        }

    }

}