package com.example.passwordmanager.presentation.common_components

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.passwordmanager.CardsScreen
import com.example.passwordmanager.LoginsScreen
import com.example.passwordmanager.OthersScreen
import com.example.passwordmanager.presentation.navigation.MainActions
import com.example.passwordmanager.presentation.theme.Theme

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
            icon = { LoginsScreen.AllLogins.icon?.let { Icon(imageVector = it, contentDescription = "icon") } },
            label = { Text(text = LoginsScreen.AllLogins.label) },
            selected = currentRoute == LoginsScreen.AllLogins.route,
            selectedContentColor = Theme.colors.primary,
            unselectedContentColor = Theme.colors.onBackground.copy(alpha = 0.6f),
            onClick = {actions.navigateToAllLogins()}

        )

        BottomNavigationItem(
            icon = { CardsScreen.AllCards.icon?.let { Icon(imageVector = it, contentDescription = "icon") } },
            label = { Text(text = CardsScreen.AllCards.label) },
            selected = currentRoute == CardsScreen.AllCards.route,
            selectedContentColor = Theme.colors.primary,
            unselectedContentColor = Theme.colors.onBackground.copy(alpha = 0.6f),
            onClick = {actions.navigateToAllCards()}

        )


        BottomNavigationItem(
            icon = { OthersScreen.AllOthers.icon?.let { Icon(imageVector = it, contentDescription = "icon") } },
            label = { Text(text = OthersScreen.AllOthers.label) },
            selected = currentRoute == OthersScreen.AllOthers.route,
            selectedContentColor = Theme.colors.primary,
            unselectedContentColor = Theme.colors.onBackground.copy(alpha = 0.6f),
            onClick = {actions.navigateToAllOthers()}

        )




    }
}
