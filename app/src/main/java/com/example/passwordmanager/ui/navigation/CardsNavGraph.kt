package com.example.passwordmanager.ui.screens.cardsScreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.passwordmanager.*
import com.example.passwordmanager.ui.viewModel.MainViewModel
import com.example.passwordmanager.Screen
import com.example.passwordmanager.ui.screens.home.CardsScreen
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

        if (currentRoute != null) {
            CardsScreen(
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

private fun NavGraphBuilder.addNewCardsGraph(
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    composable(
        route = CardsScreen.NewCardsItem.route
    ) {

        NewCardsScreen(
            mainViewModel = mainViewModel,
            navController = navController
        )

    }
}