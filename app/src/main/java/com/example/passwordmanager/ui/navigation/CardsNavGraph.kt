package com.example.passwordmanager.ui.screens.cardsScreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.navigation
import com.example.passwordmanager.*
import com.example.passwordmanager.ui.viewModel.MainViewModel
import com.example.passwordmanager.Screen
import com.example.passwordmanager.ui.screens.home.CardsScreen
import com.example.passwordmanager.ui.screens.loginsScreen.EditLoginsDetails
import com.example.passwordmanager.ui.screens.loginsScreen.LoginsDetailsScreen
import com.example.passwordmanager.ui.screens.newItem.NewCardsScreen

fun NavGraphBuilder.addCardsGraph(
    mainViewModel: MainViewModel,
    navController: NavHostController
){

    navigation(
        route = Screen.CardsScreenRoot.route,
        startDestination = CardsScreen.AllCards.route
    ){

        addAllCardsGraph(mainViewModel,navController)
        addNewCardsGraph(mainViewModel,navController)
        addCardsDetailsGraph(mainViewModel, navController)
        addEditCardsGraph(mainViewModel, navController)

    }
}

private fun NavGraphBuilder.addAllCardsGraph(
    mainViewModel: MainViewModel,
    navController: NavHostController
) {



    composable(
        route = CardsScreen.AllCards.route
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

        val navigateToCardsDetails: (Int)->Unit = {itemId->
            navController.navigate(CardsScreen.CardsDetails.createRoute(itemId.toString())){
                popUpTo(CardsScreen.AllCards.route)
            }
        }

        val navigateToNewItem: ()->Unit = {
            navController.navigate(CardsScreen.NewCardsItem.route){
                popUpTo(CardsScreen.AllCards.route)
            }
        }

        val navigateToCardsEdit: (Int)->Unit = {itemId->
            navController.navigate(CardsScreen.EditCardsDetails.createRoute(itemId.toString())){
                popUpTo(CardsScreen.AllCards.route)
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
            CardsScreen(
                mainViewModel = mainViewModel,
                navController = navController,
                currentRoute = currentRoute,
                navigateToAllLogins = navigateToAllLogins,
                navigateToAllCards = navigateToAllCards,
                navigateToAllOthers = navigateToAllOthers,
                navigateToNewItem = navigateToNewItem,
                navigateToCardsDetails = navigateToCardsDetails,
                navigateToCardsEdit = navigateToCardsEdit,
                navigateToSettings = navigateToSettings,
                popUp = popUp
            )
        }

    }
}

private fun NavGraphBuilder.addNewCardsGraph(
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    composable(
        route = CardsScreen.NewCardsItem.route
    ) {

        val navigateToAllLogins: () -> Unit = {
            navController.navigate(CardsScreen.AllCards.route)
        }

        val popUp: ()->Unit = {
            navController.navigateUp()
        }

        val itemId = it.arguments?.getInt("itemId")

        NewCardsScreen(
            mainViewModel = mainViewModel,
            navController = navController,
            popUp = popUp
        )

    }
}


private fun NavGraphBuilder.addCardsDetailsGraph(
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    composable(
        route = CardsScreen.CardsDetails.route,
        arguments = listOf(
            navArgument("itemId"){type = NavType.IntType}
        )
    ) {backStackEntry->

        val itemId = backStackEntry.arguments?.getInt("itemId")

        val navigateToAllLogins: () -> Unit = {
            navController.navigate(CardsScreen.AllCards.route)
        }

        val navigateToEditScreen: (Int) -> Unit = {id->
            navController.navigate(CardsScreen.EditCardsDetails.createRoute(id.toString()))
        }

        val popUp: () -> Unit = {
            navController.navigateUp()
        }

        CardsDetailsScreen(
            mainViewModel = mainViewModel,
            navigateToAllLogins = navigateToAllLogins,
            navigateToEditScreen = navigateToEditScreen,
            popUp = popUp,
            itemId = itemId ?: 0
        )

    }
}

private fun NavGraphBuilder.addEditCardsGraph(
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    composable(
        route = CardsScreen.EditCardsDetails.route,
        arguments = listOf(
            navArgument("itemId"){
                type = NavType.IntType
            }
        )
    ) {

        val navigateToAllLogins: () -> Unit = {
            navController.navigate(CardsScreen.AllCards.route)
        }

        val popUp: ()->Unit = {
            navController.navigateUp()
        }

        val itemId = it.arguments?.getInt("itemId")

        if (itemId != null) {
            EditCardsDetails(
                mainViewModel = mainViewModel,
                navController = navController,
                navigateToAllLogins = navigateToAllLogins,
                itemId = itemId,
                popUp = popUp
            )
        }


    }
}
