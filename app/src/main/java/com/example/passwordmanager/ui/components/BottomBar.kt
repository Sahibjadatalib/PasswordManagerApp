package com.example.passwordmanager.ui.components

import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.passwordmanager.screensForBottomBar

@Composable
fun MyBottomBar(
    navController: NavController
) {



    BottomAppBar(
        modifier = Modifier.background(Color.White),
        backgroundColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        screensForBottomBar.forEach { screen ->

            BottomNavigationItem(
                icon = { Icon(imageVector = screen.icon, contentDescription = "icon") },
                label = { Text(text = screen.label) },
                selected = currentRoute == screen.route,
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = MaterialTheme.colors.onBackground,
                onClick = {

                    navController.navigate(screen.route){
                        popUpTo(screensForBottomBar.first().route)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}
