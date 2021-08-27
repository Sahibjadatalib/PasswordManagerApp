package com.example.passwordmanager.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.passwordmanager.ui.theme.AmberA200

@Composable
fun HomeTopAppBar(
    topAppBarTitle: String,
    onMenuIconClick: () -> Unit,
    switchState: Boolean,
    onSwitchIconClick: (Boolean)->Unit
) {

    TopAppBar(
        title = { Text(text = topAppBarTitle) },
        backgroundColor = MaterialTheme.colors.background,
        navigationIcon = {
            IconButton(onClick = { onMenuIconClick() }) {
                Icon(
                    tint = MaterialTheme.colors.primary,
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Hamburger icon"
                )
            }
        },
        actions = {

            Switch(
                checked = switchState,
                onCheckedChange = { onSwitchIconClick(it)},
                colors = SwitchDefaults.colors(
                    checkedThumbColor = AmberA200,
                    checkedTrackAlpha = AmberA200.blue,
                    uncheckedThumbColor = MaterialTheme.colors.secondary,
                    uncheckedTrackAlpha = MaterialTheme.colors.secondary.red
                )
            )
            
            Spacer(modifier = Modifier.width(8.dp))


        },
        elevation = 4.dp
    )
}

@Composable
fun NewItemTopAppBar(
    topAppBarTitle: String,
    onDoneIconClick: () -> Unit,
    onCancelIconClick: () -> Unit
) {

    TopAppBar(
        backgroundColor = MaterialTheme.colors.background
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
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

    TopAppBar(
        backgroundColor = MaterialTheme.colors.background
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onBackIconClick() }) {
                Icon(
                    modifier = Modifier.weight(1f),
                    tint = Color.Black,
                    imageVector = Icons.Default.ArrowBackIos,
                    contentDescription = ""
                )
            }

            Text(
                modifier = Modifier.weight(6f),
                text = topAppBarTitle,
                fontSize = MaterialTheme.typography.h6.fontSize,
                fontWeight = FontWeight.Bold
            )

            IconButton(onClick = { onEditIconClick() }) {
                Icon(
                    modifier = Modifier.weight(1f),
                    tint = MaterialTheme.colors.primary,
                    imageVector = Icons.Default.Edit,
                    contentDescription = ""
                )
            }

            IconButton(onClick = { onDeleteIconClick() }) {
                Icon(
                    modifier = Modifier.weight(1f),
                    tint = Color.Red,
                    imageVector = Icons.Default.Delete,
                    contentDescription = ""
                )
            }
        }
    }

}