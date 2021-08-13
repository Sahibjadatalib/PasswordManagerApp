package com.example.passwordmanager.ui.screens.cardsScreen

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.passwordmanager.LeafScreen
import com.example.passwordmanager.MainViewModel
import com.example.passwordmanager.Screen
import com.example.passwordmanager.ui.screens.home.CardsScreen
import com.example.passwordmanager.ui.screens.newItem.NewCardsScreen

fun NavGraphBuilder.addCardsGraph(
    mainViewModel: MainViewModel,
    navController: NavHostController
){

    navigation(
        route = Screen.CardsScreenRoot.route,
        startDestination = LeafScreen.AllCards.route
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
        route = LeafScreen.AllCards.route
    ) {

        CardsScreen(
            mainViewModel = mainViewModel,
            navController = navController
        )

    }
}

private fun NavGraphBuilder.addNewCardsGraph(
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    composable(
        route = LeafScreen.NewCardsItem.route
    ) {

        NewCardsScreen(
            mainViewModel = mainViewModel,
            navController = navController
        )

    }
}