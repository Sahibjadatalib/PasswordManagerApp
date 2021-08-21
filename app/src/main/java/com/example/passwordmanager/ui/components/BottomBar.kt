package com.example.passwordmanager.ui.components

import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.passwordmanager.CardsScreen
import com.example.passwordmanager.LoginsScreen
import com.example.passwordmanager.OthersScreen

@Composable
fun MyBottomBar(
    navController: NavController,
    currentRoute: String,
    navigateToAllLogins: ()->Unit,
    navigateToAllCards: ()->Unit,
    navigateToAllOthers: ()->Unit,
) {

    BottomAppBar(
        modifier = Modifier.background(Color.White),
        backgroundColor = Color.White
    ) {
//        val navBackStackEntry by navController.currentBackStackEntryAsState()
//        val currentRoute = navBackStackEntry?.destination?.route

        BottomNavigationItem(
            icon = { Icon(imageVector = LoginsScreen.AllLogins.icon, contentDescription = "icon") },
            label = { Text(text = LoginsScreen.AllLogins.label) },
            selected = currentRoute == LoginsScreen.AllLogins.route,
            selectedContentColor = MaterialTheme.colors.primary,
            unselectedContentColor = MaterialTheme.colors.onBackground,
            onClick = {navigateToAllLogins()}

        )

        BottomNavigationItem(
            icon = { Icon(imageVector = CardsScreen.AllCards.icon, contentDescription = "icon") },
            label = { Text(text = CardsScreen.AllCards.label) },
            selected = currentRoute == CardsScreen.AllCards.route,
            selectedContentColor = MaterialTheme.colors.primary,
            unselectedContentColor = MaterialTheme.colors.onBackground,
            onClick = {navigateToAllCards()}

        )


        BottomNavigationItem(
            icon = { Icon(imageVector = OthersScreen.AllOthers.icon, contentDescription = "icon") },
            label = { Text(text = OthersScreen.AllOthers.label) },
            selected = currentRoute == OthersScreen.AllOthers.route,
            selectedContentColor = MaterialTheme.colors.primary,
            unselectedContentColor = MaterialTheme.colors.onBackground,
            onClick = {navigateToAllOthers()}

        )




    }
}
