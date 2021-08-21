package com.example.passwordmanager.ui.screens.othersScreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.passwordmanager.*
import com.example.passwordmanager.ui.viewModel.MainViewModel
import com.example.passwordmanager.Screen
import com.example.passwordmanager.ui.screens.home.OthersScreen
import com.example.passwordmanager.ui.screens.newItem.NewOthersScreen

fun NavGraphBuilder.addOthersGraph(
    mainViewModel: MainViewModel,
    navController: NavHostController
) {

    navigation(
        route = Screen.OthersScreenRoot.route,
        startDestination = OthersScreen.AllOthers.route
    ) {
        addAllOthersGraph(mainViewModel, navController)
        addNewOthersGraph(mainViewModel, navController)
    }


}

private fun NavGraphBuilder.addAllOthersGraph(
    mainViewModel: MainViewModel,
    navController: NavHostController
) {

    composable(
        route = OthersScreen.AllOthers.route
    ) {

        val currentRoute = it.destination.route

        val navigateToAllLogins: ()->Unit = {
            navController.navigate(LoginsScreen.AllLogins.route){
                popUpTo(LoginsScreen.AllLogins.route)
                launchSingleTop = true
            }
        }

        val navigateToAllCards: ()->Unit = {
            navController.navigate(CardsScreen.AllCards.route){
                popUpTo(LoginsScreen.AllLogins.route)
                launchSingleTop = true
            }
        }

        val navigateToAllOthers: ()->Unit = {
            navController.navigate(OthersScreen.AllOthers.route){
                popUpTo(LoginsScreen.AllLogins.route)
                launchSingleTop = true
            }
        }

        if (currentRoute != null) {
            OthersScreen(
                mainViewModel = mainViewModel,
                navController = navController,
                currentRoute = currentRoute,
                navigateToAllLogins = navigateToAllLogins,
                navigateToAllCards = navigateToAllCards,
                navigateToAllOthers = navigateToAllOthers,
            )
        }

    }
}

private fun NavGraphBuilder.addNewOthersGraph(
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    composable(
        route = OthersScreen.NewOthersItem.route
    ) {

        NewOthersScreen(
            mainViewModel = mainViewModel,
            navController = navController
        )


    }
}



