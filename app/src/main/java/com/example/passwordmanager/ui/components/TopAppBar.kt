package com.example.passwordmanager.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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

@Composable
fun HomeTopAppBar(
    topAppBarTitle: String,
    onMenuIconClick: ()->Unit,
    onSortIconClick: () -> Unit,
    onSearchIconClick: () -> Unit
){

    TopAppBar(
        title = { Text(text = topAppBarTitle) },
        backgroundColor = MaterialTheme.colors.background,
        navigationIcon = {
            IconButton(onClick = {onMenuIconClick()}) {
                Icon(
                    tint = MaterialTheme.colors.primary,
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Hamburger icon"
                )
            }
        },
        actions = {

            IconButton(onClick = { onSortIconClick() }) {
                Icon(
                    tint = MaterialTheme.colors.primary,
                    imageVector = Icons.Default.Sort,
                    contentDescription = "Sort icon"
                )
            }

            IconButton(onClick = { onSearchIconClick() }) {
                Icon(
                    tint = MaterialTheme.colors.primary,
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search icon"
                )
            }


        },
        elevation = 5.dp
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

            IconButton(onClick = { onDoneIconClick()}) {
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
fun IconsLibraryTopBar(

){

}