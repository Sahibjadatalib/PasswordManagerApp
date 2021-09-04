package com.example.passwordmanager.ui.screens.loginsScreen

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.navigation
import com.example.passwordmanager.*
import com.example.passwordmanager.ui.viewModel.MainViewModel
import com.example.passwordmanager.Screen
import com.example.passwordmanager.ui.navigation.MainActions
import com.example.passwordmanager.ui.screens.NewLoginsScreen
import com.example.passwordmanager.ui.screens.home.LoginsScreen


fun NavGraphBuilder.addLoginsGraph(
    mainViewModel: MainViewModel,
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    actions: MainActions
) {

    navigation(
        route = Screen.LoginsScreenRoot.route,
        startDestination = LoginsScreen.AllLogins.route
    ) {

        addAllLoginsGraph(mainViewModel, navController)
        addNewLoginsGraph(mainViewModel, navController)
        addLoginsDetailsGraph(mainViewModel, navController)
        addEditLoginsGraph(mainViewModel, navController)

    }


}

private fun NavGraphBuilder.addAllLoginsGraph(
    mainViewModel: MainViewModel,
    navController: NavHostController
) {

    composable(
        route = LoginsScreen.AllLogins.route
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

        val navigateToLoginsDetails: (Int)->Unit = {itemId->
            navController.navigate(LoginsScreen.LoginsDetails.createRoute(itemId.toString())){
                popUpTo(LoginsScreen.AllLogins.route)
            }
        }

        val navigateToNewItem: ()->Unit = {
            navController.navigate(LoginsScreen.NewLoginsItem.route){
                popUpTo(LoginsScreen.AllLogins.route)
            }
        }

        val navigateToLoginsEdit: (Int)->Unit = {itemId->
            navController.navigate(LoginsScreen.EditLoginsDetails.createRoute(itemId.toString())){
                popUpTo(LoginsScreen.AllLogins.route)
            }
        }

        val navigateToSettings: ()->Unit = {
            navController.navigate(SettingsScreen.Settings.route){
                popUpTo(SettingsScreen.Settings.route){
                    inclusive = true
                }
            }
        }

        val popUp: ()->Unit = {
            navController.navigateUp()
        }

        if (currentRoute != null) {
            LoginsScreen(
                mainViewModel = mainViewModel,
                navController = navController,
                currentRoute = currentRoute,
                navigateToAllLogins = navigateToAllLogins,
                navigateToAllCards = navigateToAllCards,
                navigateToAllOthers = navigateToAllOthers,
                navigateToLoginsDetails = navigateToLoginsDetails,
                navigateToNewItem = navigateToNewItem,
                navigateToLoginsEdit = navigateToLoginsEdit,
                navigateToSettings = navigateToSettings,
                popUp = popUp
            )
        }

    }
}

private fun NavGraphBuilder.addNewLoginsGraph(
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    composable(
        route = LoginsScreen.NewLoginsItem.route

    ) {

        val navigateToAllLogins: () -> Unit = {
            navController.navigate(LoginsScreen.AllLogins.route)
        }

        val popUp: ()->Unit = {
            navController.navigateUp()
        }

        val itemId = it.arguments?.getInt("itemId")

        if (itemId != null) {
            NewLoginsScreen(
                mainViewModel = mainViewModel,
                navController = navController,
                navigateToAllLogins = navigateToAllLogins,
                popUp = popUp

            )
        }


    }
}


private fun NavGraphBuilder.addLoginsDetailsGraph(
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    composable(
        route = LoginsScreen.LoginsDetails.route,
        arguments = listOf(
            navArgument("itemId"){type = NavType.IntType}
        )
    ) {backStackEntry->

        val itemId = backStackEntry.arguments?.getInt("itemId")

        val navigateToAllLogins: () -> Unit = {
            navController.navigate(LoginsScreen.AllLogins.route)
        }

        val navigateToEditScreen: (Int) -> Unit = {id->
            navController.navigate(LoginsScreen.EditLoginsDetails.createRoute(id.toString()))
        }

        val popUp: () -> Unit = {
            navController.navigateUp()
        }

            LoginsDetailsScreen(
                mainViewModel = mainViewModel,
                navigateToAllLogins = navigateToAllLogins,
                navigateToEditScreen = navigateToEditScreen,
                popUp = popUp,
                itemId = itemId ?: 0
            )

    }
}

private fun NavGraphBuilder.addEditLoginsGraph(
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    composable(
        route = LoginsScreen.EditLoginsDetails.route,
        arguments = listOf(
            navArgument("itemId"){
                type = NavType.IntType
            }
        )
    ) {

        val navigateToAllLogins: () -> Unit = {
            navController.navigate(LoginsScreen.AllLogins.route)
        }

        val popUp: ()->Unit = {
            navController.navigateUp()
        }

        val itemId = it.arguments?.getInt("itemId")

        if (itemId != null) {
            EditLoginsDetails(
                mainViewModel = mainViewModel,
                navController = navController,
                navigateToAllLogins = navigateToAllLogins,
                itemId = itemId,
                popUp = popUp
            )
        }


    }
}




