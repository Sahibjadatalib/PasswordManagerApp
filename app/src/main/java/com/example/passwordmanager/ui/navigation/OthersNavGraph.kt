package com.example.passwordmanager.ui.screens.othersScreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.navigation
import com.example.passwordmanager.*
import com.example.passwordmanager.ui.viewModel.MainViewModel
import com.example.passwordmanager.Screen
import com.example.passwordmanager.ui.screens.home.OthersScreen
import com.example.passwordmanager.ui.screens.loginsScreen.EditLoginsDetails
import com.example.passwordmanager.ui.screens.loginsScreen.LoginsDetailsScreen
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
        addEditOthersGraph(mainViewModel, navController)
        addOthersDetailsGraph(mainViewModel, navController)
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


        val navigateToOthersDetails: (Int)->Unit = {itemId->
            navController.navigate(OthersScreen.OthersDetails.createRoute(itemId.toString())){
                popUpTo(OthersScreen.AllOthers.route)
            }
        }

        val navigateToNewItem: ()->Unit = {
            navController.navigate(OthersScreen.NewOthersItem.route){
                popUpTo(OthersScreen.AllOthers.route)
            }
        }

        val navigateToOthersEdit: (Int)->Unit = {itemId->
            navController.navigate(OthersScreen.EditOthersDetails.createRoute(itemId.toString())){
                popUpTo(OthersScreen.AllOthers.route)
            }
        }

        val popUp: ()->Unit = {
            navController.navigateUp()
        }

        if (currentRoute != null) {
            OthersScreen(
                mainViewModel = mainViewModel,
                navController = navController,
                currentRoute = currentRoute,
                navigateToAllLogins = navigateToAllLogins,
                navigateToAllCards = navigateToAllCards,
                navigateToAllOthers = navigateToAllOthers,
                navigateToOthersDetails = navigateToOthersDetails,
                navigateToNewItem = navigateToNewItem,
                navigateToOthersEdit = navigateToOthersEdit,
                popUp = popUp
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

        val popUp: ()->Unit = {
            navController.navigateUp()
        }

        NewOthersScreen(
            mainViewModel = mainViewModel,
            navController = navController,
            popUp = popUp
        )


    }
}

private fun NavGraphBuilder.addOthersDetailsGraph(
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    composable(
        route = OthersScreen.OthersDetails.route,
        arguments = listOf(
            navArgument("itemId"){type = NavType.IntType}
        )
    ) {backStackEntry->

        val itemId = backStackEntry.arguments?.getInt("itemId")

        val navigateToAllOthers: () -> Unit = {
            navController.navigate(OthersScreen.AllOthers.route)
        }

        val navigateToEditScreen: (Int) -> Unit = {id->
            navController.navigate(OthersScreen.EditOthersDetails.createRoute(id.toString()))
        }

        val popUp: () -> Unit = {
            navController.navigateUp()
        }

        OthersDetailsScreen(
            mainViewModel = mainViewModel,
            navigateToAllOthers = navigateToAllOthers,
            navigateToEditScreen = navigateToEditScreen,
            popUp = popUp,
            itemId = itemId ?: 0
        )

    }
}

private fun NavGraphBuilder.addEditOthersGraph(
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    composable(
        route = OthersScreen.EditOthersDetails.route,
        arguments = listOf(
            navArgument("itemId"){
                type = NavType.IntType
            }
        )
    ) {

        val navigateToAllOthers: () -> Unit = {
            navController.navigate(OthersScreen.AllOthers.route)
        }

        val popUp: ()->Unit = {
            navController.navigateUp()
        }

        val itemId = it.arguments?.getInt("itemId")

        if (itemId != null) {
            EditOthersDetails(
                mainViewModel = mainViewModel,
                navController = navController,
                navigateToAllOthers = navigateToAllOthers,
                itemId = itemId,
                popUp = popUp
            )
        }


    }
}



