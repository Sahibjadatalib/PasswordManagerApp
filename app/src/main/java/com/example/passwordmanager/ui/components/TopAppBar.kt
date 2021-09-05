package com.example.passwordmanager.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.passwordmanager.R
import com.example.passwordmanager.ui.theme.AmberA700
import com.example.passwordmanager.ui.theme.Theme

@Composable
fun HomeTopAppBar(
    topAppBarTitle: String,
    onMenuIconClick: () -> Unit,
    switchState: Boolean,
    onSwitchIconClick: (Boolean) -> Unit,
    onSettingsIconClick: () -> Unit
) {

    Surface(
        elevation = 8.dp
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {

                Image(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(id = R.drawable.app_logo_latest),
                    contentDescription = ""
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = topAppBarTitle,
                    style = Theme.typography.h6
                )

            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Switch(
                    checked = switchState,
                    onCheckedChange = { onSwitchIconClick(it) },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = AmberA700,
                        checkedTrackColor = AmberA700.copy(alpha = 0.4f),
                        uncheckedThumbColor = Theme.colors.primary,
                        uncheckedTrackColor = Theme.colors.primary.copy(alpha = 0.4f)
                    )
                )

                Spacer(modifier = Modifier.width(4.dp))

                IconButton(onClick = { onSettingsIconClick() }) {
                    Icon(
                        tint = Theme.colors.primary,
                        imageVector = Icons.Default.Settings, contentDescription = ""
                    )
                }
            }




        }

    }

}

@Composable
fun NewItemTopAppBar(
    topAppBarTitle: String,
    onDoneIconClick: () -> Unit,
    onCancelIconClick: () -> Unit
) {

    Surface(
        elevation = 8.dp
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onCancelIconClick() }) {
                Icon(
                    tint = Color.Red,
                    imageVector = Icons.Default.Close,
                    contentDescription = ""
                )
            }

            Text(
                text = topAppBarTitle,
                fontSize = MaterialTheme.typography.h6.fontSize,
                fontWeight = FontWeight.Bold
            )

            IconButton(onClick = { onDoneIconClick() }) {
                Icon(
                    tint = Color.Green,
                    imageVector = Icons.Default.Done,
                    contentDescription = ""
                )
            }
        }

    }


}


@Composable
fun DetailsTopAppBar(
    topAppBarTitle: String,
    onBackIconClick: () -> Unit,
    onDeleteIconClick: () -> Unit,
    onEditIconClick: () -> Unit
) {

    Surface(
        elevation = Theme.elevation.medium
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Theme.paddings.medium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {

                IconButton(onClick = { onBackIconClick() }) {
                    Icon(
                        tint = Theme.colors.primary,
                        imageVector = Icons.Default.ArrowBackIos,
                        contentDescription = ""
                    )
                }

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    modifier = Modifier,
                    text = topAppBarTitle,
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    fontWeight = FontWeight.Bold
                )
            }



            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ){

                IconButton(onClick = { onEditIconClick() }) {
                    Icon(
                        modifier = Modifier,
                        tint = MaterialTheme.colors.primary,
                        imageVector = Icons.Default.Edit,
                        contentDescription = ""
                    )
                }

                Spacer(modifier = Modifier.width(4.dp))

                IconButton(onClick = { onDeleteIconClick() }) {
                    Icon(
                        modifier = Modifier,
                        tint = Color.Red,
                        imageVector = Icons.Default.Delete,
                        contentDescription = ""
                    )
                }

            }

        }
    }
}


@Composable
fun SettingsTopAppBar(
    onBackBtnClick: () -> Unit
) {

    Surface(
        elevation = Theme.elevation.medium
    ) {

        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ){

            IconButton(onClick = { onBackBtnClick() }) {
                Icon(
                    tint = MaterialTheme.colors.primary,
                    imageVector = Icons.Filled.ArrowBackIos,
                    contentDescription = ""
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                style = Theme.typography.h6,
                text = "Settings"
            )

        }

    }

}