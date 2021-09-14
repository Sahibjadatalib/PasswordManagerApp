package com.stalib.passwordmanager.ui.screens.othersScreen

import androidx.compose.material.ScaffoldState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.navigation
import com.stalib.passwordmanager.*
import com.stalib.passwordmanager.MainViewModel
import com.stalib.passwordmanager.Screen
import com.stalib.passwordmanager.presentation.navigation.MainActions
import com.stalib.passwordmanager.presentation.screens.others.EditOthersDetails
import com.stalib.passwordmanager.presentation.screens.others.OthersScreen
import com.stalib.passwordmanager.presentation.screens.others.NewOthersScreen
import com.stalib.passwordmanager.presentation.screens.others.OthersDetailsScreen

fun NavGraphBuilder.addOthersGraph(
    mainViewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    actions: MainActions
) {

    navigation(
        route = Screen.OthersScreenRoot.route,
        startDestination = OthersScreen.AllOthers.route
    ) {
        addAllOthersGraph(mainViewModel,scaffoldState,actions)
        addNewOthersGraph(mainViewModel,scaffoldState,actions)
        addEditOthersGraph(mainViewModel,scaffoldState,actions)
        addOthersDetailsGraph(mainViewModel,scaffoldState,actions)
    }


}

private fun NavGraphBuilder.addAllOthersGraph(
    mainViewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    actions: MainActions
) {

    composable(
        route = OthersScreen.AllOthers.route
    ) {

        val currentRoute = it.destination.route

        if (currentRoute != null) {
            OthersScreen(
                mainViewModel = mainViewModel,
                currentRoute = currentRoute,
                scaffoldState = scaffoldState,
                actions = actions
            )
        }

    }
}

private fun NavGraphBuilder.addNewOthersGraph(
    mainViewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    actions: MainActions
) {
    composable(
        route = OthersScreen.NewOthersItem.route
    ) {

        NewOthersScreen(
            mainViewModel = mainViewModel,
            scaffoldState = scaffoldState,
            actions = actions
        )


    }
}

private fun NavGraphBuilder.addOthersDetailsGraph(
    mainViewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    actions: MainActions
) {
    composable(
        route = OthersScreen.OthersDetails.route,
        arguments = listOf(
            navArgument("itemId"){type = NavType.IntType}
        )
    ) {backStackEntry->

        val itemId = backStackEntry.arguments?.getInt("itemId")

        OthersDetailsScreen(
            mainViewModel = mainViewModel,
            scaffoldState = scaffoldState,
            actions = actions,
            itemId = itemId ?: 0
        )

    }
}

private fun NavGraphBuilder.addEditOthersGraph(
    mainViewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    actions: MainActions
) {
    composable(
        route = OthersScreen.EditOthersDetails.route,
        arguments = listOf(
            navArgument("itemId"){
                type = NavType.IntType
            }
        )
    ) {

        val itemId = it.arguments?.getInt("itemId")

        if (itemId != null) {
            EditOthersDetails(
                mainViewModel = mainViewModel,
                scaffoldState = scaffoldState,
                actions = actions,
                itemId = itemId
            )
        }


    }
}



