package com.stalib.passwordmanager.presentation.navigation

import androidx.compose.material.ScaffoldState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.navigation
import com.stalib.passwordmanager.CardsScreen
import com.stalib.passwordmanager.MainViewModel
import com.stalib.passwordmanager.Screen
import com.stalib.passwordmanager.presentation.screens.cards.CardsDetailsScreen
import com.stalib.passwordmanager.presentation.screens.cards.CardsScreen
import com.stalib.passwordmanager.presentation.screens.cards.EditCardsDetails
import com.stalib.passwordmanager.presentation.screens.cards.NewCardsScreen

fun NavGraphBuilder.addCardsGraph(
    mainViewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    actions: MainActions
){

    navigation(
        route = Screen.CardsScreenRoot.route,
        startDestination = CardsScreen.AllCards.route
    ){

        addAllCardsGraph(mainViewModel,scaffoldState,actions)
        addNewCardsGraph(mainViewModel,scaffoldState,actions)
        addCardsDetailsGraph(mainViewModel,scaffoldState,actions)
        addEditCardsGraph(mainViewModel,scaffoldState,actions)

    }
}

private fun NavGraphBuilder.addAllCardsGraph(
    mainViewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    actions: MainActions
) {
    composable(
        route = CardsScreen.AllCards.route
    ) {


        val currentRoute = it.destination.route

        if (currentRoute != null) {
            CardsScreen(
                mainViewModel = mainViewModel,
                currentRoute = currentRoute,
                scaffoldState = scaffoldState,
                actions = actions
            )
        }

    }
}

private fun NavGraphBuilder.addNewCardsGraph(
    mainViewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    actions: MainActions
) {
    composable(
        route = CardsScreen.NewCardsItem.route
    ) {


        val itemId = it.arguments?.getInt("itemId")

        NewCardsScreen(
            mainViewModel = mainViewModel,
            scaffoldState = scaffoldState,
            actions = actions
        )

    }
}


private fun NavGraphBuilder.addCardsDetailsGraph(
    mainViewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    actions: MainActions
) {
    composable(
        route = CardsScreen.CardsDetails.route,
        arguments = listOf(
            navArgument("itemId"){type = NavType.IntType}
        )
    ) {backStackEntry->

        val itemId = backStackEntry.arguments?.getInt("itemId")

        CardsDetailsScreen(
            mainViewModel = mainViewModel,
            scaffoldState = scaffoldState,
            actions = actions,
            itemId = itemId ?: 0
        )

    }
}

private fun NavGraphBuilder.addEditCardsGraph(
    mainViewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    actions: MainActions
) {
    composable(
        route = CardsScreen.EditCardsDetails.route,
        arguments = listOf(
            navArgument("itemId"){
                type = NavType.IntType
            }
        )
    ) {

        val itemId = it.arguments?.getInt("itemId")

        if (itemId != null) {
            EditCardsDetails(
                mainViewModel = mainViewModel,
                scaffoldState = scaffoldState,
                actions = actions,
                itemId = itemId,
            )
        }


    }
}