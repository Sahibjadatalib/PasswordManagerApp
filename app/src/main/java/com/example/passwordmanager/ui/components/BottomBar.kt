package com.example.passwordmanager.ui.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.passwordmanager.CardsScreen
import com.example.passwordmanager.LoginsScreen
import com.example.passwordmanager.OthersScreen
import com.example.passwordmanager.ui.navigation.MainActions
import com.example.passwordmanager.ui.theme.Theme

@Composable
fun MyBottomBar(
    currentRoute: String,
    actions: MainActions
) {

    BottomAppBar(
        modifier = Modifier,
        backgroundColor = MaterialTheme.colors.background
    ) {
//        val navBackStackEntry by navController.currentBackStackEntryAsState()
//        val currentRoute = navBackStackEntry?.destination?.route

        BottomNavigationItem(
            icon = { Icon(imageVector = LoginsScreen.AllLogins.icon, contentDescription = "icon") },
            label = { Text(text = LoginsScreen.AllLogins.label) },
            selected = currentRoute == LoginsScreen.AllLogins.route,
            selectedContentColor = Theme.colors.primary,
            unselectedContentColor = Theme.colors.onBackground.copy(alpha = 0.6f),
            onClick = {actions.navigateToAllLogins()}

        )

        BottomNavigationItem(
            icon = { Icon(imageVector = CardsScreen.AllCards.icon, contentDescription = "icon") },
            label = { Text(text = CardsScreen.AllCards.label) },
            selected = currentRoute == CardsScreen.AllCards.route,
            selectedContentColor = Theme.colors.primary,
            unselectedContentColor = Theme.colors.onBackground.copy(alpha = 0.6f),
            onClick = {actions.navigateToAllCards()}

        )


        BottomNavigationItem(
            icon = { Icon(imageVector = OthersScreen.AllOthers.icon, contentDescription = "icon") },
            label = { Text(text = OthersScreen.AllOthers.label) },
            selected = currentRoute == OthersScreen.AllOthers.route,
            selectedContentColor = Theme.colors.primary,
            unselectedContentColor = Theme.colors.onBackground.copy(alpha = 0.6f),
            onClick = {actions.navigateToAllOthers()}

        )




    }
}
